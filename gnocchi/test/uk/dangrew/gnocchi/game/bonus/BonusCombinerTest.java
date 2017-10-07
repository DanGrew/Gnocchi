package uk.dangrew.gnocchi.game.bonus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusCombinerTest {

   private BonusCombiner systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new BonusCombiner();
   }//End Method

   @Test public void shouldNotCombineNonCombinationTypes() {
      assertCombination( SquareType.Regular, SquareType.CrossBlast, null );
      assertCombination( SquareType.MassBomb, SquareType.CrossBlast, null ); 
   }//End Method
   
   @Test public void shouldCombine() {
      assertCombination( SquareType.HorizontalBlast, SquareType.HorizontalBlast, SquareType.CrossBlast );
      assertCombination( SquareType.HorizontalBlast, SquareType.VerticalBlast, SquareType.CrossBlast );
      assertCombination( SquareType.HorizontalBlast, SquareType.CrossBlast, SquareType.BombBlast );
      assertCombination( SquareType.HorizontalBlast, SquareType.BombBlast, SquareType.BombHorizontal );
      assertCombination( SquareType.HorizontalBlast, SquareType.MassBlast, SquareType.MassHorizontal );
      
      assertCombination( SquareType.VerticalBlast, SquareType.HorizontalBlast, SquareType.CrossBlast );
      assertCombination( SquareType.VerticalBlast, SquareType.VerticalBlast, SquareType.CrossBlast );
      assertCombination( SquareType.VerticalBlast, SquareType.CrossBlast, SquareType.BombBlast );
      assertCombination( SquareType.VerticalBlast, SquareType.BombBlast, SquareType.BombVertical );
      assertCombination( SquareType.VerticalBlast, SquareType.MassBlast, SquareType.MassVertical );
      
      assertCombination( SquareType.CrossBlast, SquareType.HorizontalBlast, SquareType.BombBlast );
      assertCombination( SquareType.CrossBlast, SquareType.VerticalBlast, SquareType.BombBlast );
      assertCombination( SquareType.CrossBlast, SquareType.CrossBlast, SquareType.BombBlast );
      assertCombination( SquareType.CrossBlast, SquareType.BombBlast, SquareType.BombCross );
      assertCombination( SquareType.CrossBlast, SquareType.MassBlast, SquareType.MassCross );
      
      assertCombination( SquareType.BombBlast, SquareType.HorizontalBlast, SquareType.BombHorizontal );
      assertCombination( SquareType.BombBlast, SquareType.VerticalBlast, SquareType.BombVertical );
      assertCombination( SquareType.BombBlast, SquareType.CrossBlast, SquareType.BombCross );
      assertCombination( SquareType.BombBlast, SquareType.BombBlast, SquareType.BombBomb );
      assertCombination( SquareType.BombBlast, SquareType.MassBlast, SquareType.MassBomb );
      
      assertCombination( SquareType.MassBlast, SquareType.HorizontalBlast, SquareType.MassHorizontal );
      assertCombination( SquareType.MassBlast, SquareType.VerticalBlast, SquareType.MassVertical );
      assertCombination( SquareType.MassBlast, SquareType.CrossBlast, SquareType.MassCross );
      assertCombination( SquareType.MassBlast, SquareType.BombBlast, SquareType.MassBomb );
      assertCombination( SquareType.MassBlast, SquareType.MassBlast, SquareType.MassMass );
   }//End Method
   
   private void assertCombination( SquareType type1, SquareType type2, SquareType result ) {
      assertThat( systemUnderTest.determineCombination( 
               type1, type2 
      ), is( result ) );
      assertThat( systemUnderTest.determineCombination( 
               type2, type1
      ), is( result ) );
   }//End Method

}//End Class
