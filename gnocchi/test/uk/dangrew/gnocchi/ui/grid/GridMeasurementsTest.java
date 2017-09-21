package uk.dangrew.gnocchi.ui.grid;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class GridMeasurementsTest {

   private GridMeasurements systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GridMeasurements( 800, 600 );
   }//End Method

   @Test public void shouldProvideDimensionsOfGrid() {
      assertThat( systemUnderTest.gridPixelWidth(), is( 800 ) );
      assertThat( systemUnderTest.gridPixelHeight(), is( 600 ) );
   }//End Method

   @Test public void shouldProvideSquareDimension(){
      assertThat( systemUnderTest.calculateSquareDimension( 10, 10 ), is( 54.0 ) );
      assertThat( systemUnderTest.calculateSquareDimension( 20, 20 ), is( 27.0 ) );
      assertThat( systemUnderTest.calculateSquareDimension( 20, 1 ), is( 28.8 ) );
   }//End Method
   
   @Test public void shouldProvideSquareArea(){
      assertThat( systemUnderTest.calculateSquareArea( 10, 10 ), is( 60.0 ) );
      assertThat( systemUnderTest.calculateSquareArea( 20, 20 ), is( 30.0 ) );
      assertThat( systemUnderTest.calculateSquareArea( 20, 1 ), is( 32.0 ) );
   }//End Method
   
   @Test public void shouldProvideOffset(){
      assertThat( systemUnderTest.calculateWidthOffset( 10, 10 ), is( 130.0 ) );
      assertThat( systemUnderTest.calculateWidthOffset( 20, 20 ), is( 115.0 ) );
      assertThat( systemUnderTest.calculateWidthOffset( 20, 1 ), is( 96.0 ) );
   }//End Method
   
   @Test public void shouldCreateScaledSquareWidget(){
      Square object = Square.randomSquare();
      
      SquareWidget widget = systemUnderTest.constructSquareWidget( object, 10, 10 );
      assertThat( widget.getX(), is( GridMeasurements.OFFSCREEN ) );
      assertThat( widget.getY(), is( GridMeasurements.OFFSCREEN ) );
      assertThat( widget.getWidth(), is( systemUnderTest.calculateSquareDimension( 10, 10 ) ) );
      assertThat( widget.getHeight(), is( systemUnderTest.calculateSquareDimension( 10, 10 ) ) );
      assertThat( widget.association(), is( object ) );
   }//End Method
   
   @Test public void shouldProvideGravitySpeed(){
      assertThat( systemUnderTest.calculateGravity( 10 ), is( 50.0 ) );
      assertThat( systemUnderTest.calculateGravity( 20 ), is( 25.0 ) );
   }//End Method
}//End Class
