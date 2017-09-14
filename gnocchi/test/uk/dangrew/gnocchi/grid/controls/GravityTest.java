package uk.dangrew.gnocchi.grid.controls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.GridModel;

public class GravityTest {

   private GridModel grid;
   private Feeder feeder;
   private Gravity systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 10, 10 );
      feeder = new Feeder( grid );
      systemUnderTest = new Gravity( grid );
   }//End Method

   @Test public void shouldPullObjectToBottom() {
      feeder.feed( 3 );
      Object object = grid.at( 3, 0 );
      
      assertObjectInPosition( object, 0, true );
      assertThat( systemUnderTest.pull( 3, 0 ), is ( true ) );
      
      assertObjectInPosition( object, 9, true );
   }//End Method
   
   @Test public void shouldPullObjectToNextHighest() {
      feeder.feed( 3 );
      Object first = grid.at( 3, 0 );
      assertThat( systemUnderTest.pull( 3, 0 ), is ( true ) );
      
      feeder.feed( 3 );
      Object second = grid.at( 3, 0 );
      assertThat( first, is( not( second ) ) );
      
      assertThat( systemUnderTest.pull( 3, 0 ), is ( true ) );
      assertObjectInPosition( first, 9, false );
      assertObjectInPosition( second, 8, false );
   }//End Method
   
   @Test public void shouldNotChangeObjectPositionWhenNowhereToPull() {
      for ( int i = 0; i < 10; i++ ) {
         feeder.feed( 3 );
         systemUnderTest.pull( 3, 0 );
      }
      
      for ( int i = 0; i < 10; i++ ) {
         assertThat( grid.at( 3, i ), is( notNullValue() ) );
      }
      
      Object object = grid.at( 3, 0 );
      assertThat( systemUnderTest.pull( 3, 0 ), is ( false ) );
      assertThat( grid.at( 3, 0 ), is( object ) );
   }//End Method
   
   private void assertObjectInPosition( Object object, int h, boolean emptyElsewhere ) {
      for ( int i = 0; i < 10; i++ ) {
         if ( i == h ) {
            assertThat( grid.at( 3, i ), is( notNullValue() ) );   
         } else if ( emptyElsewhere ){
            assertThat( grid.at( 3, i ), is( nullValue() ) );
         } else {
            assertThat( grid.at( 3, i ), is( not( object ) ) );
         }
      }
   }//End Method
   
   @Test public void shouldRejectPositionOutsideGrid(){
      fail();
   }//End Method

}//End Class
