package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class MassColourMatcherTest {

   private GridModel model;
   private MassColourMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      model = new GridModel( 3, 10, 10 );
      for( int w = 0; w < 10; w++ ) {
         for( int h = 0; h< 10; h++ ) {
            model.set( Square.typedSquare( SquareRegularType.Primary ), w, h );
         }
      }
      
      systemUnderTest = new MassColourMatcher();
   }//End Method

   @Test public void shouldMatchAllOfSameColour() {
      Square s1 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s1, 0, 0 );
      Square s2 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s2, 3, 2 );
      Square s3 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s3, 7, 8 );
      Square s4 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s4, 6, 6 );
      Square s5 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s5, 1, 5 );
      Square s6 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s6, 1, 6 );
      Square s7 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s7, 2, 9 );
      
      model.set( null, 3, 6 );
      model.set( null, 8, 8 );
      
      assertThat( systemUnderTest.match( model, 0, 0 ), containsInAnyOrder(  
               s1, s2, s3, s4, s5, s6, s7
       ) );
   }//End Method
   
   @Test public void shouldNotMatchBonuses() {
      Square s1 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s1, 0, 0 );
      Square s2 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s2, 3, 2 );
      Square s3 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s3, 7, 8 );
      Square s4 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s4, 6, 6 );
      Square s5 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s5, 1, 5 );
      Square s6 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s6, 1, 6 );
      Square s7 = Square.typedSquare( SquareRegularType.Secondary );
      model.set( s7, 2, 9 );
      
      s2.setType( SquareBonusType.BombBomb );
      
      assertThat( systemUnderTest.match( model, 0, 0 ), containsInAnyOrder(  
               s1, s3, s4, s5, s6, s7
       ) );
   }//End Method
   
   @Test public void shouldIgnoreNullMatch() {
      model.set( null, 0, 0 );
      assertThat( systemUnderTest.match( model, 0, 0 ), is( new ArrayList<>() ) );
   }//End Method

}//End Class
