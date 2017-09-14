package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class GridModelTest {
   
   private static final int WIDTH = 10;
   private static final int HEIGHT = 20;

   private GridModel systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GridModel( WIDTH, HEIGHT );
   }//End Method

   @Test public void shouldProvideDimensions(){
      assertThat( systemUnderTest.width(), is( WIDTH ) );
      assertThat( systemUnderTest.height(), is( HEIGHT ) );
   }//End Method
   
   @Test public void shouldBeEmptyInitially() {
      for ( int i = 0; i < WIDTH; i++ ) {
         for ( int j = 0; j < HEIGHT; j++ ) {
            assertThat( systemUnderTest.at( i, j ), is( nullValue() ) );
         }   
      }
   }//End Method
   
   @Test public void shouldSupportRequestsOutsideBoundaries() {
      assertThat( systemUnderTest.at( 34958734, 12373 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( -34958734, -12373 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( -34958734, 12373 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( 34958734, -12373 ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldPutObjectsInGrid(){
      assertThat( systemUnderTest.at( 5, 6 ), is( nullValue() ) );
      Object o = new Object();
      systemUnderTest.set( o, 5, 6 );
      assertThat( systemUnderTest.at( 5, 6 ), is( o ) );
   }//End Method

}//End Method
