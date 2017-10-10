package uk.dangrew.gnocchi.game.bonus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.square.SquarePopType;

public class BonusCombinerTest {

   private BonusCombiner systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new BonusCombiner();
   }//End Method

   @Test public void shouldNotCombineNonCombinationTypes() {
      assertCombination( SquarePopType.Regular, SquarePopType.CrossBlast, null );
      assertCombination( SquarePopType.MassBomb, SquarePopType.CrossBlast, null ); 
   }//End Method
   
   @Test public void shouldCombine() {
      assertCombination( SquarePopType.HorizontalBlast, SquarePopType.HorizontalBlast, SquarePopType.CrossBlast );
      assertCombination( SquarePopType.HorizontalBlast, SquarePopType.VerticalBlast, SquarePopType.CrossBlast );
      assertCombination( SquarePopType.HorizontalBlast, SquarePopType.CrossBlast, SquarePopType.BombBlast );
      assertCombination( SquarePopType.HorizontalBlast, SquarePopType.BombBlast, SquarePopType.BombHorizontal );
      assertCombination( SquarePopType.HorizontalBlast, SquarePopType.MassBlast, SquarePopType.MassHorizontal );
      
      assertCombination( SquarePopType.VerticalBlast, SquarePopType.HorizontalBlast, SquarePopType.CrossBlast );
      assertCombination( SquarePopType.VerticalBlast, SquarePopType.VerticalBlast, SquarePopType.CrossBlast );
      assertCombination( SquarePopType.VerticalBlast, SquarePopType.CrossBlast, SquarePopType.BombBlast );
      assertCombination( SquarePopType.VerticalBlast, SquarePopType.BombBlast, SquarePopType.BombVertical );
      assertCombination( SquarePopType.VerticalBlast, SquarePopType.MassBlast, SquarePopType.MassVertical );
      
      assertCombination( SquarePopType.CrossBlast, SquarePopType.HorizontalBlast, SquarePopType.BombBlast );
      assertCombination( SquarePopType.CrossBlast, SquarePopType.VerticalBlast, SquarePopType.BombBlast );
      assertCombination( SquarePopType.CrossBlast, SquarePopType.CrossBlast, SquarePopType.BombBlast );
      assertCombination( SquarePopType.CrossBlast, SquarePopType.BombBlast, SquarePopType.BombCross );
      assertCombination( SquarePopType.CrossBlast, SquarePopType.MassBlast, SquarePopType.MassCross );
      
      assertCombination( SquarePopType.BombBlast, SquarePopType.HorizontalBlast, SquarePopType.BombHorizontal );
      assertCombination( SquarePopType.BombBlast, SquarePopType.VerticalBlast, SquarePopType.BombVertical );
      assertCombination( SquarePopType.BombBlast, SquarePopType.CrossBlast, SquarePopType.BombCross );
      assertCombination( SquarePopType.BombBlast, SquarePopType.BombBlast, SquarePopType.BombBomb );
      assertCombination( SquarePopType.BombBlast, SquarePopType.MassBlast, SquarePopType.MassBomb );
      
      assertCombination( SquarePopType.MassBlast, SquarePopType.HorizontalBlast, SquarePopType.MassHorizontal );
      assertCombination( SquarePopType.MassBlast, SquarePopType.VerticalBlast, SquarePopType.MassVertical );
      assertCombination( SquarePopType.MassBlast, SquarePopType.CrossBlast, SquarePopType.MassCross );
      assertCombination( SquarePopType.MassBlast, SquarePopType.BombBlast, SquarePopType.MassBomb );
      assertCombination( SquarePopType.MassBlast, SquarePopType.MassBlast, SquarePopType.MassMass );
   }//End Method
   
   private void assertCombination( SquarePopType type1, SquarePopType type2, SquarePopType result ) {
      assertThat( systemUnderTest.determineCombination( 
               type1, type2 
      ), is( result ) );
      assertThat( systemUnderTest.determineCombination( 
               type2, type1
      ), is( result ) );
   }//End Method

}//End Class
