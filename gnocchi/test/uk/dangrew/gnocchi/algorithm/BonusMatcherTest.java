package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusMatcherTest {

   private GridModel model;
   private BonusMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      model = new GridModel( 4, 10, 10 );
      for ( int w = 0; w < 10; w++ ) {
         for ( int h = 0; h < 10; h++ ) {
            model.set( Square.randomSquare(), w, h );
         }  
      }
      systemUnderTest = new BonusMatcher();
   }//End Method

   @Test public void shouldIgnoreNullSource() {
      model.set( null, 5, 5 );
      assertThat( systemUnderTest.match( model, 5, 5 ), is( new ArrayList<>() ) );
   }//End Method
   
   @Test public void shouldIgnoreNoneBonus() {
      model.at( 4, 4 ).setType( SquareType.HorizontalBlast );
      model.at( 4, 5 ).setType( SquareType.VerticalBlast );
      model.at( 4, 6 ).setType( SquareType.MassHorizontal );
      model.at( 5, 4 ).setType( SquareType.BombBlast );
      model.at( 5, 6 ).setType( SquareType.BombBlast );
      model.at( 6, 4 ).setType( SquareType.BombBlast );
      model.at( 6, 5 ).setType( SquareType.BombBlast );
      model.at( 6, 6 ).setType( SquareType.BombBlast );
      
      assertThat( systemUnderTest.match( model, 5, 5 ), is( new ArrayList<>() ) );
   }//End Method
   
   @Test public void shouldNotMatchAnyNonBonuses() {
      model.at( 5, 5 ).setType( SquareType.BombBlast );
      assertThat( systemUnderTest.match( model, 5, 5 ), is( new ArrayList<>() ) );
   }//End Method
   
   @Test public void shouldMatchSurroundingBonuses() {
      model.at( 5, 5 ).setType( SquareType.HorizontalBlast );
      
      model.at( 4, 4 ).setType( SquareType.HorizontalBlast );
      model.at( 4, 5 ).setType( SquareType.VerticalBlast );
      model.at( 4, 6 ).setType( SquareType.VerticalBlast );
      model.at( 5, 4 ).setType( SquareType.HorizontalBlast );
      model.at( 5, 6 ).setType( SquareType.HorizontalBlast );
      model.at( 6, 4 ).setType( SquareType.HorizontalBlast );
      model.at( 6, 5 ).setType( SquareType.HorizontalBlast );
      model.at( 6, 6 ).setType( SquareType.HorizontalBlast );
      
      assertThat( systemUnderTest.match( model, 5, 5 ), containsInAnyOrder( 
               model.at( 4, 4 ),
               model.at( 4, 5 ),
               model.at( 4, 6 ),
               model.at( 5, 4 ),
               model.at( 5, 6 ),
               model.at( 6, 4 ),
               model.at( 6, 5 ),
               model.at( 6, 6 )
      ) );
   }//End Method
   
   @Test public void shouldMatchComboEnabledBonuses() {
      model.at( 5, 5 ).setType( SquareType.HorizontalBlast );
      
      model.at( 4, 4 ).setType( SquareType.HorizontalBlast );
      model.at( 4, 5 ).setType( SquareType.BombBomb );
      
      assertThat( systemUnderTest.match( model, 5, 5 ), containsInAnyOrder( 
               model.at( 4, 4 )
      ) );
   }//End Method
   
   @Test public void shouldNotMatchNonComboEnabled() {
      model.at( 5, 5 ).setType( SquareType.BombBomb );
      
      model.at( 4, 4 ).setType( SquareType.HorizontalBlast );
      model.at( 4, 5 ).setType( SquareType.VerticalBlast );
      
      assertThat( systemUnderTest.match( model, 5, 5 ), is( empty() ) );
   }//End Method

}//End Class
