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
      assertThat( systemUnderTest.colourProperty().get(), is( systemUnderTest.colour() ) );
      systemUnderTest.setColour( Color.RED );
      assertThat( systemUnderTest.colour(), is( Color.RED ) );
      assertThat( systemUnderTest.colourProperty().get(), is( systemUnderTest.colour() ) );
   }//End Method
   
   @Test public void shouldMatchAnotherSquare(){
      assertThat( systemUnderTest.matches( Square.colouredSquare( Color.ORANGE ) ), is( true ) );
   }//End Method
   
   @Test public void shouldNotMatchAnotherSquare(){
      assertThat( systemUnderTest.matches( Square.colouredSquare( Color.RED ) ), is( false ) );
   }//End Method
   
   @Test public void shouldNotMatchFromBonusSquare(){
      systemUnderTest.setType( SquareBonusType.BombBlast );
      assertThat( systemUnderTest.matches( Square.colouredSquare( Color.ORANGE ) ), is( false ) );
   }//End Method
   
   @Test public void shouldNotMatchToBonusSquare(){
      Square square = Square.colouredSquare( Color.ORANGE );
      square.setType( SquareBonusType.BombBomb );
      assertThat( systemUnderTest.matches( square ), is( false ) );
   }//End Method
   
   @Test public void shouldChangePosition(){
      assertThat( systemUnderTest.position(), is( new GridPosition( 0, 1 ) ) );
      systemUnderTest.moveTo( new GridPosition( 4, 9 ) );
      assertThat( systemUnderTest.position(), is( new GridPosition( 4, 9 ) ) );
   }//End Method
   
   @Test public void shouldProvideSquareType(){
      assertThat( systemUnderTest.type(), is( SquareRegularType.Regular ) );
      assertThat( systemUnderTest.typeProperty().get(), is( SquareRegularType.Regular ) );
      systemUnderTest.setType( SquareBonusType.HorizontalBlast );
      assertThat( systemUnderTest.type(), is( SquareBonusType.HorizontalBlast ) );
      assertThat( systemUnderTest.typeProperty().get(), is( SquareBonusType.HorizontalBlast ) );
   }//End Method
   
   @Test public void shouldProvideMatcher(){
      assertThat( systemUnderTest.typeMatcher(), is( SquareRegularType.Regular.properties().matcher() ) );
      systemUnderTest.setType( SquareBonusType.CrossBlast );
      assertThat( systemUnderTest.typeMatcher(), is( SquareBonusType.CrossBlast.properties().matcher() ) );
   }//End Method

}//End Class
