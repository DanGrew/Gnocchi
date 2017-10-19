package uk.dangrew.gnocchi.game.bonus;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class BonusDetectorTest {

   private Square source;
   private List< Square > matches;
   private BonusDetector systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      source = Square.randomSquare();
      matches = new ArrayList<>();
      systemUnderTest = new BonusDetector();
   }//End Method
   
   @Test public void shouldProvideNoBonusIfAlreadyBonusType(){
      shouldDetermineCrossBlastFromEqualWidthHeightPop();
      source.setType( SquareBonusType.CrossBlast );
      assertThat( systemUnderTest.detectBonus( source, matches ), is( nullValue() ) );
      source.setType( SquareBonusType.HorizontalBlast );
      assertThat( systemUnderTest.detectBonus( source, matches ), is( nullValue() ) );
      source.setType( SquareBonusType.VerticalBlast );
      assertThat( systemUnderTest.detectBonus( source, matches ), is( nullValue() ) );
      source.setType( SquareRegularType.Regular );
      assertThat( systemUnderTest.detectBonus( source, matches ), is( notNullValue() ) );
   }//End Method
   
   @Test public void shouldProvideNoBonusForLessThanThreshold(){
      assertThat( systemUnderTest.detectBonus( source, matches ), is( nullValue() ) );
      
      for ( int i = 1; i < BonusDetector.LINE_THRESHOLD; i++ ) {
         matches.add( Square.randomSquare() );
         assertThat( systemUnderTest.detectBonus( source, matches ), is( nullValue() ) );   
      }
   }//End Method

   @Test public void shouldDetermineHorizontalBlastFromHighWidthPop() {
      matches.add( new Square( new GridPosition( 3, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 5, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 6, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 2 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 3, 1 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 3, 2 ), Color.RED ) );
      
      assertThat( systemUnderTest.detectBonus( source, matches ), is( SquareBonusType.HorizontalBlast ) );
   }//End Method
   
   @Test public void shouldDetermineHorizontalBlastFromHighHeightPop() {
      matches.add( new Square( new GridPosition( 3, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 3, 6 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 3, 7 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 3, 8 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 2 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 1 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 5, 2 ), Color.RED ) );
      
      assertThat( systemUnderTest.detectBonus( source, matches ), is( SquareBonusType.VerticalBlast ) );
   }//End Method
   
   @Test public void shouldDetermineCrossBlastFromEqualWidthHeightPop() {
      matches.add( new Square( new GridPosition( 4, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 3, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 2, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 5, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 6, 4 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 3 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 2 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 6 ), Color.RED ) );
      matches.add( new Square( new GridPosition( 4, 5 ), Color.RED ) );
      
      assertThat( systemUnderTest.detectBonus( source, matches ), is( SquareBonusType.CrossBlast ) );
   }//End Method
   
   @Test public void shouldDetermineBombBlastIfMoreThanThreshold(){
      for ( int i = 0; i < BonusDetector.BOMB_THRESHOLD; i++ ) {
         matches.add( Square.randomSquare() );
      }
      assertThat( systemUnderTest.detectBonus( source, matches ), is( SquareBonusType.BombBlast ) );
   }//End Method
   
   @Test public void shouldDetermineMassMatchIfMoreThanThreshold(){
      for ( int i = 0; i < BonusDetector.MASS_THRESHOLD; i++ ) {
         matches.add( Square.randomSquare() );
      }
      assertThat( systemUnderTest.detectBonus( source, matches ), is( SquareBonusType.MassBlast ) );
   }//End Method

}//End Class
