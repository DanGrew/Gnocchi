package uk.dangrew.gnocchi.grid.controls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.GridModel;

public class FillerTest {

   private GridModel grid;
   private Filler systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 10, 10 );
      systemUnderTest = new Filler( grid );
   }//End Method

   @Test public void shouldFillGrid() {
      for ( int i = 0; i < 10; i++ ) {
         for ( int j = 0; j < 10; j++ ) {
            assertThat( grid.at( i, j ), is( nullValue() ) );
         }   
      }
      
      systemUnderTest.fill();
      
      for ( int i = 0; i < 10; i++ ) {
         for ( int j = 0; j < 10; j++ ) {
            assertThat( grid.at( i, j ), is( notNullValue() ) );
         }   
      }
   }//End Method
   
   @Test public void shouldOnlyFillUpToExistingElements(){
      Object o1 = new Object();
      Object o2 = new Object();
      grid.set( o1, 4, 0 );
      grid.set( o2, 7, 4 );
      
      systemUnderTest.fill();
      
      for ( int i = 0; i < 10; i++ ) {
         assertThat( grid.at( 0, i ), is( notNullValue() ) );
         assertThat( grid.at( 1, i ), is( notNullValue() ) );
         assertThat( grid.at( 2, i ), is( notNullValue() ) );
         assertThat( grid.at( 3, i ), is( notNullValue() ) );
         assertThat( grid.at( 5, i ), is( notNullValue() ) );
         assertThat( grid.at( 6, i ), is( notNullValue() ) );
         assertThat( grid.at( 8, i ), is( notNullValue() ) );
         assertThat( grid.at( 9, i ), is( notNullValue() ) );
      }
      
      for ( int i = 1; i < 10; i++ ) {
         assertThat( grid.at( 4, i ), is( nullValue() ) );
      }
      assertThat( grid.at( 4, 0 ), is( o1 ) );
      
      for ( int i = 5; i < 10; i++ ) {
         assertThat( grid.at( 7, i ), is( nullValue() ) );
      }
      assertThat( grid.at( 7, 4 ), is( o2 ) );
      for ( int i = 0; i < 5; i++ ) {
         assertThat( grid.at( 7, i ), is( notNullValue() ) );
      }
   }//End Method

}//End Class
