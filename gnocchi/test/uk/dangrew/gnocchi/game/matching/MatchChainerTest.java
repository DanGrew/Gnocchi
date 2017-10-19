package uk.dangrew.gnocchi.game.matching;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;

public class MatchChainerTest {

   private GridModel model;
   private MatchChainer systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      model = new GridModel( 3, 10, 10 );
      for ( int w = 0; w < 10; w++ ) {
         for ( int h = 0; h < 10; h++ ) {
            model.set( Square.colouredSquare( Color.RED ), w, h );
         }
      }
      
      systemUnderTest = new MatchChainer();
   }//End Method

   @Test public void shouldRecursivelyMatchSquaresThatMatch() {
      model.set( Square.colouredSquare( Color.BLUE ), 0, 0 );
      model.set( Square.colouredSquare( Color.BLUE ), 1, 0 );
      model.set( Square.colouredSquare( Color.BLUE ), 2, 0 );
      model.set( Square.colouredSquare( Color.BLUE ), 3, 0 );
      model.set( Square.colouredSquare( Color.BLUE ), 4, 0 );
      model.at( 4, 0 ).setType( SquareBonusType.VerticalBlast );
      
      List< Square > expectedMatches = Arrays.asList(
               model.at( 0, 0 ),
               model.at( 1, 0 ),
               model.at( 2, 0 ),
               model.at( 3, 0 ),
               model.at( 4, 0 ),
               model.at( 4, 1 ),
               model.at( 4, 2 ),
               model.at( 4, 3 ),
               model.at( 4, 4 ),
               model.at( 4, 5 ),
               model.at( 4, 6 ),
               model.at( 4, 7 ),
               model.at( 4, 8 ),
               model.at( 4, 9 )
      );
      assertThat( systemUnderTest.match( model, 0, 0 ), is( expectedMatches ) );
   }//End Method
   
   @Test public void shouldIgnoreNullMatch(){
      model.set( null, 0, 0 );
      assertThat( systemUnderTest.match( model, 0, 0 ), is( empty() ) );
   }//End Method
   
}//End Class
