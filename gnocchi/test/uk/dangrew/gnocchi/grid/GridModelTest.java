package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridPosition;

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
      assertThat( systemUnderTest.of( o ), is( new GridPosition( 5, 6 ) ) );
   }//End Method
   
   @Test public void shouldMoveObjectWhenPositionSet(){
      Object object = new Object();
      systemUnderTest.set( object, 4, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 4, 5 ) ) );
      
      systemUnderTest.set( object, 3, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( 3, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 3, 5 ) ) );
   }//End Method
   
   @Test public void shouldRemoveObject(){
      Object object = new Object();
      systemUnderTest.set( object, 4, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 4, 5 ) ) );
      
      systemUnderTest.remove( object );
      assertThat( systemUnderTest.at( 4, 5 ), is( nullValue() ) );
      assertThat( systemUnderTest.of( object ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldIgnoreRemoveOnObjectOutside(){
      Object object = new Object();
      systemUnderTest.remove( object );
   }//End Method
   
   @Test public void shouldDetermineEmptyLocation(){
      Object object = new Object();
      
      assertThat( systemUnderTest.isEmpty( 5, 6 ), is( true ) );
      systemUnderTest.set( object, 5, 6 );
      assertThat( systemUnderTest.isEmpty( 5, 6 ), is( false ) );
      
      systemUnderTest.set( object, 3, 2 );
      assertThat( systemUnderTest.isEmpty( 5, 6 ), is( true ) );
      assertThat( systemUnderTest.isEmpty( 3, 2 ), is( false ) );
   }//End Method
   
   @Test public void shouldProvideIndexConvenience(){
      assertThat( systemUnderTest.lastWidthIndex(), is( systemUnderTest.width() - 1 ) );
      assertThat( systemUnderTest.lastHeightIndex(), is( systemUnderTest.height() - 1 ) );
   }//End Method
   
   @Test public void shouldProvideIterators(){
      fail();
   }//End Method

   @Test public void shouldProvideSnapshot(){
      fail();
   }//End Method
}//End Method
