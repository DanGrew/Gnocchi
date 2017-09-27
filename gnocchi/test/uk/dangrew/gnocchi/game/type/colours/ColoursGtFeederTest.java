package uk.dangrew.gnocchi.game.type.colours;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.mechanics.Feeder;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtFeeder;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.mechanics.ColorGenerator;

public class ColoursGtFeederTest {

   @Mock private ColorGenerator colours;
   private GridModel grid;
   private Feeder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      when( colours.next( 4 ) ).thenReturn( Color.RED );
      
      grid = new GridModel( 4, 10, 10 );
      systemUnderTest = new ColoursGtFeeder( colours, grid );
   }//End Method

   @Test public void shouldFeedColumnWhenEmpty() {
      assertThat( grid.at( 4, 9 ), is( nullValue() ) );
      assertThat( systemUnderTest.feed( 4 ), is ( notNullValue() ) );
      assertThat( grid.at( 4, 9 ), is( notNullValue() ) );
      assertThat( grid.at( 4, 9 ).colour(), is( Color.RED ) );
   }//End Method
   
   @Test public void shouldNotFeedColumnWhenFull() {
      shouldFeedColumnWhenEmpty();
      
      Object object = grid.at( 4, 9 );
      assertThat( systemUnderTest.feed( 4 ), is ( nullValue() ) );
      assertThat( grid.at( 4, 9 ), is( object ) );
   }//End Method

}//End Class
