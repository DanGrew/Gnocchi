package uk.dangrew.gnocchi.game.type.colours;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.game.mechanics.GameState;
import uk.dangrew.gnocchi.game.mechanics.Logic;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class ColoursGtLogicTest {

   private Square r1;
   private Square r2;
   private Square r3;
   private Square b1;
   private Square b2;
   private Square g1;
   
   @Mock private GridModel model;
   @Mock private FloodFill floodFill;
   
   private ColoursGtProperties properties;
   private Logic systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      r1 = Square.colouredSquare( Color.AQUA );
      r2 = Square.colouredSquare( Color.AQUA );
      r3 = Square.colouredSquare( Color.AQUA );
      b1 = Square.colouredSquare( Color.BLUE );
      b2 = Square.colouredSquare( Color.BLUE );
      g1 = Square.colouredSquare( Color.GREEN );
      
      properties = new ColoursGtProperties();
      properties.decreaseRemaining( Color.RED, 100 );
      properties.decreaseRemaining( Color.LIGHTSKYBLUE, 100 );
      properties.moveUsed( 25 );
      
      properties.increaseRemaining( Color.AQUA, 5 );
      properties.increaseRemaining( Color.BLUE, 5 );
      properties.increaseMoves( 5 );
      
      systemUnderTest = new ColoursGtLogic( properties, model, floodFill );
   }//End Method

   @Test public void shouldUseMoveForEverySuccessfulPop(){
      when( floodFill.flood( model, r1.position().w, r1.position().h ) )
         .thenReturn( Arrays.asList( r1 ) )
         .thenReturn( Arrays.asList( r1, r2 ) )
         .thenReturn( Arrays.asList( r1, r2, r3 ) )
         .thenReturn( Arrays.asList( r1, r2, g1 ) );
      
      systemUnderTest.pop( r1 );
      assertThat( properties.movesRemaining().get(), is( 5 ) );
      systemUnderTest.pop( r1 );
      assertThat( properties.movesRemaining().get(), is( 5 ) );
      systemUnderTest.pop( r1 );
      assertThat( properties.movesRemaining().get(), is( 4 ) );
      systemUnderTest.pop( r1 );
      assertThat( properties.movesRemaining().get(), is( 3 ) );
   }//End Method
   
   @Test public void shouldPopColoursMatchesFromGrid() {
      when( floodFill.flood( model, r1.position().w, r1.position().h ) ).thenReturn( Arrays.asList( r1, r2, r3 ) );
      assertThat( systemUnderTest.pop( r1 ), is( Arrays.asList( r1, r2, r3 ) ) );
      verify( model ).remove( r1 );
      verify( model ).remove( r2 );
      verify( model ).remove( r3 );
   }//End Method
   
   @Test public void shouldNotPopTwoOrLess(){
      when( floodFill.flood( model, r1.position().w, r1.position().h ) )
         .thenReturn( Arrays.asList( r1 ) )
         .thenReturn( Arrays.asList( r1, r2 ) );
      assertThat( systemUnderTest.pop( r1 ), is( Arrays.asList() ) );
      assertThat( systemUnderTest.pop( r1 ), is( Arrays.asList() ) );
      verify( model, never() ).remove( Mockito.any() );
   }//End Method
   
   @Test public void shouldProgressEachPop(){
      when( floodFill.flood( model, r1.position().w, r1.position().h ) ).thenReturn( Arrays.asList( r1, r2, r3 ) );
      assertThat( systemUnderTest.pop( r1 ), is( Arrays.asList( r1, r2, r3 ) ) );
      assertThat( properties.remainingFor( Color.AQUA ), is( 2 ) );
      assertThat( properties.remainingFor( Color.BLUE ), is( 5 ) );
      assertThat( properties.remainingFor( Color.GREEN ), is( 0 ) );
   }//End Method
   
   @Test public void shouldPopMultiColouredMatch(){
      when( floodFill.flood( model, r1.position().w, r1.position().h ) )
         .thenReturn( Arrays.asList( r1, r2, r3, b1, b2 ) );
      assertThat( systemUnderTest.pop( r1 ), is( Arrays.asList( r1, r2, r3, b1, b2 ) ) );
      assertThat( properties.remainingFor( Color.AQUA ), is( 2 ) );
      assertThat( properties.remainingFor( Color.BLUE ), is( 3 ) );
      assertThat( properties.remainingFor( Color.GREEN ), is( 0 ) );
   }//End Method
   
   @Test public void shouldIgnoreProgressOnNonTarget(){
      when( floodFill.flood( model, r1.position().w, r1.position().h ) )
         .thenReturn( Arrays.asList( r1, r2, g1 ) );
      assertThat( systemUnderTest.pop( r1 ), is( Arrays.asList( r1, r2, g1 ) ) );
      assertThat( properties.remainingFor( Color.AQUA ), is( 3 ) );
      assertThat( properties.remainingFor( Color.BLUE ), is( 5 ) );
      assertThat( properties.remainingFor( Color.GREEN ), is( 0 ) );
   }//End Method
   
   @Test public void shouldFailWhenOutOfMovesAndAnyColourTargetNotMet(){
      properties.increaseRemaining( Color.AQUA, 10 );
      properties.moveUsed();
      properties.moveUsed();
      properties.moveUsed();
      properties.moveUsed();
      properties.moveUsed();
      assertThat( systemUnderTest.determineGameState(), is( GameState.Failure ) );
   }//End Method
   
   @Test public void shouldSucceedWhenMovesLeftAndAllColourTargetsMet(){
      properties.increaseMoves( 5 );
      properties.decreaseRemaining( Color.AQUA, 5 );
      properties.decreaseRemaining( Color.BLUE, 5 );
      
      assertThat( systemUnderTest.determineGameState(), is( GameState.Success ) );
   }//End Method
   
   @Test public void shouldSucceedWhenNoMovesLeftButAllColourTargetsMet(){
      properties.increaseMoves( 2 );
      properties.moveUsed();
      properties.moveUsed();
      
      properties.decreaseRemaining( Color.AQUA, 5 );
      properties.decreaseRemaining( Color.BLUE, 5 );
      
      
      assertThat( systemUnderTest.determineGameState(), is( GameState.Success ) );
   }//End Method
   
   @Test public void shouldBeActiveWhenMovesLeftAndTargetsNotMet(){
      properties.increaseMoves( 5 );
      
      assertThat( systemUnderTest.determineGameState(), is( GameState.Active ) );
   }//End Method
   
}//End Class
