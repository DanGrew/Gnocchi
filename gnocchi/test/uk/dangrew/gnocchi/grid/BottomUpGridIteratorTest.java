package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.BottomUpGridIterator;
import uk.dangrew.gnocchi.grid.model.GridModel;

public class BottomUpGridIteratorTest {

   private GridModel grid;
   private Object o00;
   private Object o01;
   private Object o02;
   private Object o10;
   private Object o11;
   private Object o12;
   
   private BottomUpGridIterator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 2, 3 );
      grid.set( o00 = new Object(), 0, 0 );
      grid.set( o01 = new Object(), 0, 1 );
      grid.set( o02 = new Object(), 0, 2 );
      grid.set( o10 = new Object(), 1, 0 );
      grid.set( o11 = new Object(), 1, 1 );
      grid.set( o12 = new Object(), 1, 2 );
      
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
