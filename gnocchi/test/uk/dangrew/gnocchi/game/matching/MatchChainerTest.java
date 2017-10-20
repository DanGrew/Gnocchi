package uk.dangrew.gnocchi.game.matching;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class MatchChainerTest {

   private GridModel model;
   private MatchChainer systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      model = new GridModel( 3, 10, 10 );
      for ( int w = 0; w < 10; w++ ) {
         for ( int h = 0; h < 10; h++ ) {
            model.set( Square.typedSquare( SquareRegularType.Primary ), w, h );
         }
      }
      
      systemUnderTest = new MatchChainer();
   }//End Method

   @Test public void shouldRecursivelyMatchSquaresThatMatch() {
      model.at( 4, 0 ).setType( SquareBonusType.VerticalBlast );
      model.at( 4, 9 ).setType( SquareBonusType.HorizontalBlast );
      
      List< Square > expectedMatches = Arrays.asList(
               model.at( 4, 0 ),
               model.at( 4, 1 ),
               model.at( 4, 2 ),
               model.at( 4, 3 ),
               model.at( 4, 4 ),
               model.at( 4, 5 ),
               model.at( 4, 6 ),
               model.at( 4, 7 ),
               model.at( 4, 8 ),
               model.at( 4, 9 ),
               model.at( 0, 9 ),
               model.at( 1, 9 ),
               model.at( 2, 9 ),
               model.at( 3, 9 ),
               model.at( 5, 9 ),
               model.at( 6, 9 ),
               model.at( 7, 9 ),
               model.at( 8, 9 ),
               model.at( 9, 9 )
      );
      assertThat( systemUnderTest.match( model, 4, 0 ), is( expectedMatches ) );
   }//End Method
   
   @Test public void shouldIgnoreNullMatch(){
      model.set( null, 0, 0 );
      assertThat( systemUnderTest.match( model, 0, 0 ), is( empty() ) );
   }//End Method
   
}//End Class
