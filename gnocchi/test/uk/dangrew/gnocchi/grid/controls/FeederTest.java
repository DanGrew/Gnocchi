package uk.dangrew.gnocchi.grid.controls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;

public class FeederTest {

   private GridModel grid;
   private Feeder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 10, 10 );
      systemUnderTest = new Feeder( grid );
   }//End Method

   @Test public void shouldFeedColumnWhenEmpty() {
      assertThat( grid.at( 4, 9 ), is( nullValue() ) );
      assertThat( systemUnderTest.feed( 4 ), is ( notNullValue() ) );
      assertThat( grid.at( 4, 9 ), is( notNullValue() ) );
   }//End Method
   
   @Test public void shouldNotFeedColumnWhenFull() {
      shouldFeedColumnWhenEmpty();
      
      Object object = grid.at( 4, 9 );
      assertThat( systemUnderTest.feed( 4 ), is ( nullValue() ) );
      assertThat( grid.at( 4, 9 ), is( object ) );
   }//End Method

}//End Class
