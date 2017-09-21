package uk.dangrew.gnocchi.grid.controls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class FillerTest {

   private GridModel grid;
   private Filler systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 4, 10, 10 );
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
      Square o1 = new Square( Color.RED );
      Square o2 = new Square( Color.RED );
      grid.set( o1, 4, 0 );
      grid.set( o2, 7, 4 );
      
      systemUnderTest.fill();
      
      assertThat( grid.at( 4, 0 ), is( o1 ) );
      assertThat( grid.at( 7, 0 ), is( o2 ) );
   }//End Method
   
   @Test public void shouldFillGap(){
      systemUnderTest.fill();
      grid.set( null, 0, 0 );
      
      Object o3 = grid.at( 0, 3 );
      Object o2 = grid.at( 0, 2 );
      Object o1 = grid.at( 0, 1 );
      
      systemUnderTest.fill();
      assertThat( grid.at( 0, 3 ), is( notNullValue() ) );
      assertThat( grid.at( 0, 2 ), is( o3 ) );
      assertThat( grid.at( 0, 1 ), is( o2 ) );
      assertThat( grid.at( 0, 0 ), is( o1 ) );
   }//End Method

}//End Class
