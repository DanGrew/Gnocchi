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
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class ColoursGtLogicTest {

   private Square r1;
   private Square r2;
   private Square r3;
   private Square b1;
   private Square b2;
   private Square g1;
   
   private ColoursGtProperties properties;
   private Logic systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      r1 = Square.typedSquare( SquareRegularType.Primary );
      r2 = Square.typedSquare( SquareRegularType.Primary );
      r3 = Square.typedSquare( SquareRegularType.Primary );
      b1 = Square.typedSquare( SquareRegularType.Secondary );
      b2 = Square.typedSquare( SquareRegularType.Secondary );
      g1 = Square.typedSquare( SquareRegularType.Tertiary );
      
      properties = new ColoursGtProperties();
      properties.decreaseRemaining( SquareRegularType.Primary, 100 );
      properties.decreaseRemaining( SquareRegularType.Secondary, 100 );
      properties.moveUsed( 25 );
      
      properties.increaseRemaining( SquareRegularType.Primary, 5 );
      properties.increaseRemaining( SquareRegularType.Secondary, 5 );
      properties.increaseMoves( 5 );
      
      systemUnderTest = new ColoursGtLogic( properties );
   }//End Method

   @Test public void shouldProgressEachPop(){
      systemUnderTest.popAll( Arrays.asList( r1, r2, r3 ) );
      assertThat( properties.remainingFor( SquareRegularType.Primary ), is( 2 ) );
      assertThat( properties.remainingFor( SquareRegularType.Secondary ), is( 5 ) );
      assertThat( properties.remainingFor( SquareRegularType.Tertiary ), is( 0 ) );
   }//End Method
   
   @Test public void shouldPopMultiColouredMatch(){
      systemUnderTest.popAll( Arrays.asList( r1, r2, r3, b1, b2 ) );
      assertThat( properties.remainingFor( SquareRegularType.Primary ), is( 2 ) );
      assertThat( properties.remainingFor( SquareRegularType.Secondary ), is( 3 ) );
      assertThat( properties.remainingFor( SquareRegularType.Tertiary ), is( 0 ) );
   }//End Method
   
   @Test public void shouldIgnoreProgressOnNonTarget(){
      systemUnderTest.popAll( Arrays.asList( r1, r2, g1 ) );
      assertThat( properties.remainingFor( SquareRegularType.Primary ), is( 3 ) );
      assertThat( properties.remainingFor( SquareRegularType.Secondary ), is( 5 ) );
      assertThat( properties.remainingFor( SquareRegularType.Tertiary ), is( 0 ) );
   }//End Method
   
   @Test public void shouldFailWhenOutOfMovesAndAnyColourTargetNotMet(){
      properties.increaseRemaining( SquareRegularType.Primary, 10 );
      properties.moveUsed();
      properties.moveUsed();
      properties.moveUsed();
      properties.moveUsed();
      properties.moveUsed();
      assertThat( systemUnderTest.determineGameState(), is( GameState.Failure ) );
   }//End Method
   
   @Test public void shouldSucceedWhenMovesLeftAndAllColourTargetsMet(){
      properties.increaseMoves( 5 );
      properties.decreaseRemaining( SquareRegularType.Primary, 5 );
      properties.decreaseRemaining( SquareRegularType.Secondary, 5 );
      
      assertThat( systemUnderTest.determineGameState(), is( GameState.Success ) );
   }//End Method
   
   @Test public void shouldSucceedWhenNoMovesLeftButAllColourTargetsMet(){
      properties.increaseMoves( 2 );
      properties.moveUsed();
      properties.moveUsed();
      
      properties.decreaseRemaining( SquareRegularType.Primary, 5 );
      properties.decreaseRemaining( SquareRegularType.Secondary, 5 );
      
      
      assertThat( systemUnderTest.determineGameState(), is( GameState.Success ) );
   }//End Method
   
   @Test public void shouldBeActiveWhenMovesLeftAndTargetsNotMet(){
      properties.increaseMoves( 5 );
      
      assertThat( systemUnderTest.determineGameState(), is( GameState.Active ) );
   }//End Method
   
}//End Class
