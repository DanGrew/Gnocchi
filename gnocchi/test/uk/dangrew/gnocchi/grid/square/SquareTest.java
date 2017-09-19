package uk.dangrew.gnocchi.grid.square;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridPosition;

public class SquareTest {

   private Square systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new Square( new GridPosition( 0, 1 ), Color.ORANGE );
   }//End Method

   @Test public void shouldProvideColour() {
      assertThat( systemUnderTest.colour(), is( Color.ORANGE ) );
   }//End Method
   
   @Test public void shouldMatchAnotherSquare(){
      assertThat( systemUnderTest.matches( Square.colouredSquare( Color.ORANGE ) ), is( true ) );
   }//End Method
   
   @Test public void shouldNotMatchAnotherSquare(){
      assertThat( systemUnderTest.matches( Square.colouredSquare( Color.RED ) ), is( false ) );
   }//End Method
   
   @Test public void shouldChangePosition(){
      assertThat( systemUnderTest.position(), is( new GridPosition( 0, 1 ) ) );
      systemUnderTest.moveTo( new GridPosition( 4, 9 ) );
      assertThat( systemUnderTest.position(), is( new GridPosition( 4, 9 ) ) );
   }//End Method

}//End Class
