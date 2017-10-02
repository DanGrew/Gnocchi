package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class MassColourMatcherTest {

   private GridModel model;
   private MassColourMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      model = new GridModel( 3, 10, 10 );
      for( int w = 0; w < 10; w++ ) {
         for( int h = 0; h< 10; h++ ) {
            model.set( Square.colouredSquare( Color.RED ), w, h );
         }
      }
      
      systemUnderTest = new MassColourMatcher();
   }//End Method

   @Test public void shouldMatchAllOfSameColour() {
      Square s1 = Square.colouredSquare( Color.BLACK );
      model.set( s1, 0, 0 );
      Square s2 = Square.colouredSquare( Color.BLACK );
      model.set( s2, 3, 2 );
      Square s3 = Square.colouredSquare( Color.BLACK );
      model.set( s3, 7, 8 );
      Square s4 = Square.colouredSquare( Color.BLACK );
      model.set( s4, 6, 6 );
      Square s5 = Square.colouredSquare( Color.BLACK );
      model.set( s5, 1, 5 );
      Square s6 = Square.colouredSquare( Color.BLACK );
      model.set( s6, 1, 6 );
      Square s7 = Square.colouredSquare( Color.BLACK );
      model.set( s7, 2, 9 );
      
      model.set( null, 3, 6 );
      model.set( null, 8, 8 );
      
      assertThat( systemUnderTest.match( model, 0, 0 ), containsInAnyOrder(  
               s1, s2, s3, s4, s5, s6, s7
       ) );
   }//End Method
   
   @Test public void shouldIgnoreNullMatch() {
      model.set( null, 0, 0 );
      assertThat( systemUnderTest.match( model, 0, 0 ), is( new ArrayList<>() ) );
   }//End Method

}//End Class
