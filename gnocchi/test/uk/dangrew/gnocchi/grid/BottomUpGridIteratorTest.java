package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.BottomUpGridIterator;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class BottomUpGridIteratorTest {

   private GridModel grid;
   private Square o00;
   private Square o01;
   private Square o02;
   private Square o10;
   private Square o11;
   private Square o12;
   
   private BottomUpGridIterator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 3, 2, 3 );
      grid.set( o00 = new Square( SquareRegularType.Primary ), 0, 0 );
      grid.set( o01 = new Square( SquareRegularType.Primary ), 0, 1 );
      grid.set( o02 = new Square( SquareRegularType.Primary ), 0, 2 );
      grid.set( o10 = new Square( SquareRegularType.Primary ), 1, 0 );
      grid.set( o11 = new Square( SquareRegularType.Primary ), 1, 1 );
      grid.set( o12 = new Square( SquareRegularType.Primary ), 1, 2 );
      
      systemUnderTest = new BottomUpGridIterator( grid );
   }//End Method

   @Test public void shouldIterateLeftToRightBottomToTop() {
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o00 ) );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o10 ) );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o01 ) );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o11 ) );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o02 ) );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o12 ) );
      
      assertThat( systemUnderTest.hasNext(), is( false ) );
      assertThat( systemUnderTest.next().object, is( nullValue() ) );
   }//End Method
   
   @Test public void shoudlIterateOverWhenNull(){
      grid.set( null, 0, 0 );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( nullValue() ) );
      
      assertThat( systemUnderTest.hasNext(), is( true ) );
      assertThat( systemUnderTest.next().object, is( o10 ) );
   }//End Method

}//End Class
