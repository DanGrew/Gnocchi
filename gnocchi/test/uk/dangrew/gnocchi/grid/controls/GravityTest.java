package uk.dangrew.gnocchi.grid.controls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;

public class GravityTest {

   private GridModel grid;
   private Feeder feeder;
   private Gravity systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 10, 10 );
      feeder = new Feeder( grid );
      systemUnderTest = new Gravity( grid );
   }//End Method

   @Test public void shouldIgnorNothingToPullDown(){
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0 );
   }//End Method
   
   @Test public void shouldPullSingleDown(){
      feeder.feed( 0 );
      assertThatObjectsInColumn( 0, 9 );
      
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 0 );
   }//End Method
   
   @Test public void shouldPullMultipleDown(){
      feeder.feed( 0 );
      grid.set( new Object(), 0, 8 );
      assertThatObjectsInColumn( 0, 9, 8 );
      
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 0, 1 );
   }//End Method
   
   @Test public void shouldPullMultipleDownToFillMultipleGaps(){
      for ( int h = 0; h < grid.height(); h++ ) {
         grid.set( new Object(), 0, h );
      }
      
      grid.set( null, 0, 3 );
      grid.set( null, 0, 7 );
      
      systemUnderTest.pullDown( 0 );
      
      assertThatObjectsInColumn( 0, 0, 1, 2, 3, 4, 5, 6, 7 );
   }//End Method
   
   private void assertThatObjectsInColumn( int column, Integer... rows ) {
      List< Integer > expectedPresentRows = Arrays.asList( rows );
      for ( int h = 0; h < grid.height(); h++ ) {
         if ( expectedPresentRows.contains( h ) ) {
            assertThat( grid.at( column, h ), is( notNullValue() ) );
         } else {
            assertThat( grid.at( column, h ), is( nullValue() ) );
         }
      }
   }//End Method

}//End Class
