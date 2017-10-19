package uk.dangrew.gnocchi.game.bonus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusCombinerTest {

   private BonusCombiner systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new BonusCombiner();
   }//End Method

   @Test public void shouldNotCombineNonCombinationTypes() {
      assertCombination( SquareRegularType.Regular, SquareBonusType.CrossBlast, null );
      assertCombination( SquareBonusType.MassBomb, SquareBonusType.CrossBlast, null ); 
   }//End Method
   
   @Test public void shouldCombine() {
      assertCombination( SquareBonusType.HorizontalBlast, SquareBonusType.HorizontalBlast, SquareBonusType.CrossBlast );
      assertCombination( SquareBonusType.HorizontalBlast, SquareBonusType.VerticalBlast, SquareBonusType.CrossBlast );
      assertCombination( SquareBonusType.HorizontalBlast, SquareBonusType.CrossBlast, SquareBonusType.BombBlast );
      assertCombination( SquareBonusType.HorizontalBlast, SquareBonusType.BombBlast, SquareBonusType.BombHorizontal );
      assertCombination( SquareBonusType.HorizontalBlast, SquareBonusType.MassBlast, SquareBonusType.MassHorizontal );
      
      assertCombination( SquareBonusType.VerticalBlast, SquareBonusType.HorizontalBlast, SquareBonusType.CrossBlast );
      assertCombination( SquareBonusType.VerticalBlast, SquareBonusType.VerticalBlast, SquareBonusType.CrossBlast );
      assertCombination( SquareBonusType.VerticalBlast, SquareBonusType.CrossBlast, SquareBonusType.BombBlast );
      assertCombination( SquareBonusType.VerticalBlast, SquareBonusType.BombBlast, SquareBonusType.BombVertical );
      assertCombination( SquareBonusType.VerticalBlast, SquareBonusType.MassBlast, SquareBonusType.MassVertical );
      
      assertCombination( SquareBonusType.CrossBlast, SquareBonusType.HorizontalBlast, SquareBonusType.BombBlast );
      assertCombination( SquareBonusType.CrossBlast, SquareBonusType.VerticalBlast, SquareBonusType.BombBlast );
      assertCombination( SquareBonusType.CrossBlast, SquareBonusType.CrossBlast, SquareBonusType.BombBlast );
      assertCombination( SquareBonusType.CrossBlast, SquareBonusType.BombBlast, SquareBonusType.BombCross );
      assertCombination( SquareBonusType.CrossBlast, SquareBonusType.MassBlast, SquareBonusType.MassCross );
      
      assertCombination( SquareBonusType.BombBlast, SquareBonusType.HorizontalBlast, SquareBonusType.BombHorizontal );
      assertCombination( SquareBonusType.BombBlast, SquareBonusType.VerticalBlast, SquareBonusType.BombVertical );
      assertCombination( SquareBonusType.BombBlast, SquareBonusType.CrossBlast, SquareBonusType.BombCross );
      assertCombination( SquareBonusType.BombBlast, SquareBonusType.BombBlast, SquareBonusType.BombBomb );
      assertCombination( SquareBonusType.BombBlast, SquareBonusType.MassBlast, SquareBonusType.MassBomb );
      
      assertCombination( SquareBonusType.MassBlast, SquareBonusType.HorizontalBlast, SquareBonusType.MassHorizontal );
      assertCombination( SquareBonusType.MassBlast, SquareBonusType.VerticalBlast, SquareBonusType.MassVertical );
      assertCombination( SquareBonusType.MassBlast, SquareBonusType.CrossBlast, SquareBonusType.MassCross );
      assertCombination( SquareBonusType.MassBlast, SquareBonusType.BombBlast, SquareBonusType.MassBomb );
      assertCombination( SquareBonusType.MassBlast, SquareBonusType.MassBlast, SquareBonusType.MassMass );
   }//End Method
   
   private void assertCombination( SquareType type1, SquareType type2, SquareBonusType result ) {
      assertThat( systemUnderTest.determineCombination( 
               type1, type2 
      ), is( result ) );
      assertThat( systemUnderTest.determineCombination( 
               type2, type1
      ), is( result ) );
   }//End Method

}//End Class
