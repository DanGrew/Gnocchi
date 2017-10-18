package uk.dangrew.gnocchi.ui.square;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.beans.value.ChangeListener;
import uk.dangrew.gnocchi.algorithm.BonusMatcher;
import uk.dangrew.gnocchi.game.matching.MatchChainer;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareObstacleType;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.utility.mouse.TestMouseEvent;

public class HighlightModelTest {

   @Mock private InputDriver inputDriver;
   @Mock private GridModel model;
   private SquareWidget widget1;
   private SquareWidget widget2;
   private SquareWidget widget3;
   
   @Mock private BonusMatcher bonusMatcher;
   @Mock private MatchChainer squareMatcher;
   private HighlightModel systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      widget1 = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );
      widget2 = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );
      widget3 = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );
      systemUnderTest = new HighlightModel( model, inputDriver, squareMatcher, bonusMatcher );
      systemUnderTest.monitor( widget1 );
      systemUnderTest.monitor( widget2 );
      systemUnderTest.monitor( widget3 );
   }//End Method

   @Test public void shouldHighlightClickedSquare() {
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      assertThat( systemUnderTest.isSelected( widget2 ), is( false ) );
      assertThat( systemUnderTest.isSelected( widget3 ), is( false ) );
      assertThat( systemUnderTest.selected().get(), is( widget1 ) );
   }//End Method
   
   @Test public void shouldHighlightOnMouseMovementSquare() {
      when( squareMatcher.match( model, widget1.association().position().w, widget1.association().position().h ) )
         .thenReturn( Arrays.asList( widget1.association(), widget2.association(), widget3.association() ) );
      
      widget1.getOnMouseEntered().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isMatchingHighlighted( widget1 ), is( true ) );
      assertThat( systemUnderTest.isMatchingHighlighted( widget2 ), is( true ) );
      assertThat( systemUnderTest.isMatchingHighlighted( widget3 ), is( true ) );
      
      assertThat( systemUnderTest.highlighted().get(), is( widget1 ) );
      assertThat( systemUnderTest.matchingHighlighted(), containsInAnyOrder( widget1, widget2, widget3 ) );
      
      widget1.getOnMouseExited().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isMatchingHighlighted( widget1 ), is( false ) );
      assertThat( systemUnderTest.isMatchingHighlighted( widget2 ), is( false ) );
      assertThat( systemUnderTest.isMatchingHighlighted( widget3 ), is( false ) );
      
      assertThat( systemUnderTest.highlighted().get(), is( nullValue() ) );
      assertThat( systemUnderTest.matchingHighlighted(), is( empty() ) );
   }//End Method
   
   @Test public void shouldHighlightSquareMatches() {
      when( squareMatcher.match( model, widget1.association().position().w, widget1.association().position().h ) )
         .thenReturn( Arrays.asList( widget1.association(), widget2.association(), widget3.association() ) );
      
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      assertThat( systemUnderTest.isMatchingSelection( widget1 ), is( true ) );
      assertThat( systemUnderTest.isMatchingSelection( widget2 ), is( true ) );
      assertThat( systemUnderTest.isMatchingSelection( widget3 ), is( true ) );
      
      assertThat( systemUnderTest.matchingSelection(), containsInAnyOrder( widget1, widget2, widget3 ) );
   }//End Method
   
   @Test public void shouldDeselectPreviousSelection(){
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      
      widget2.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget2 ), is( true ) );
   }//End Method
   
   @Test public void shouldHighlightCombinations() {
      when( bonusMatcher.match( model, widget1.association().position().w, widget1.association().position().h ) )
         .thenReturn( Arrays.asList( widget2.association(), widget3.association() ) );
   
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      assertThat( systemUnderTest.isBonusForSelection( widget1 ), is( false ) );
      assertThat( systemUnderTest.isBonusForSelection( widget2 ), is( true ) );
      assertThat( systemUnderTest.isBonusForSelection( widget3 ), is( true ) );
      
      assertThat( systemUnderTest.bonusForSelection(), containsInAnyOrder( widget2, widget3 ) );
   }//End Method
   
   @Test public void shouldRemoveListenersOnRemove(){
      SquareWidget widget = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );

      systemUnderTest.monitor( widget );
      assertThat( widget.getOnMouseClicked(), is( notNullValue() ) );
      assertThat( widget.getOnMouseEntered(), is( notNullValue() ) );
      assertThat( widget.getOnMouseExited(), is( notNullValue() ) );
      
      systemUnderTest.remove( widget.association() );
      assertThat( widget.getOnMouseClicked(), is( nullValue() ) );
      assertThat( widget.getOnMouseEntered(), is( nullValue() ) );
      assertThat( widget.getOnMouseExited(), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldPopIfClickedSelected(){
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      verify( inputDriver ).pop( widget1 );
      assertThat( systemUnderTest.isSelected( widget1 ), is( false ) );
   }//End Method

   @Test public void shouldCombineIfBonusClicked(){
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      
      systemUnderTest.bonusForSelection().add( widget2 );
      widget2.getOnMouseClicked().handle( new TestMouseEvent() );
      verify( inputDriver ).combine( widget1, widget2 );
      
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
   }//End Method
   
   @Test public void shouldProvideMatchingWhenSelected(){
      when( squareMatcher.match( model, widget1.association().position().w, widget1.association().position().h ) )
         .thenReturn( Arrays.asList( widget1.association() ) );

      List< SquareWidget > matches = new ArrayList<>();
      ChangeListener< SquareWidget > selectionListener = ( s, o, n ) -> matches.addAll( systemUnderTest.matchingSelection() );
      systemUnderTest.selected().addListener( selectionListener );
      
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( matches.isEmpty(), is( false ) );
   }//End Method
   
   @Test public void shouldProvideMatchingWhenHighlighted(){
      when( squareMatcher.match( model, widget1.association().position().w, widget1.association().position().h ) )
         .thenReturn( Arrays.asList( widget1.association() ) );
   
      List< SquareWidget > matches = new ArrayList<>();
      ChangeListener< SquareWidget > highlightListener = ( s, o, n ) -> matches.addAll( systemUnderTest.matchingHighlighted() );
      systemUnderTest.highlighted().addListener( highlightListener );
      
      widget1.getOnMouseEntered().handle( new TestMouseEvent() );
      assertThat( matches.isEmpty(), is( false ) );
   }//End Method
   
   @Test public void shouldNotHighlightNonPoppable(){
      widget1.getOnMouseEntered().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isHighlighted( widget1 ), is( true ) );
      
      widget1.getOnMouseExited().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isHighlighted( widget1 ), is( false ) );
      
      widget1.association().setType( SquareObstacleType.FixedIndestructible );
      widget1.getOnMouseEntered().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isHighlighted( widget1 ), is( false ) );
   }//End Method
   
   @Test public void shouldNotSelectNonPoppable(){
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( true ) );
      
      widget2.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isSelected( widget1 ), is( false ) );
      
      widget1.association().setType( SquareObstacleType.FixedIndestructible );
      widget1.getOnMouseClicked().handle( new TestMouseEvent() );
      assertThat( systemUnderTest.isHighlighted( widget1 ), is( false ) );
   }//End Method
   
}//End Class
