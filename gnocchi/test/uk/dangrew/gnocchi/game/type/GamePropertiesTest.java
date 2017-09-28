package uk.dangrew.gnocchi.game.type;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GamePropertiesTest {

   private GameProperties systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GameProperties();
      systemUnderTest.moveUsed( 25 );
   }//End Method

   @Test public void shouldProvideNumberOfMoves(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      systemUnderTest.increaseMoves( 20 );
      assertThat( systemUnderTest.movesRemaining().get(), is( 20 ) );
      
      systemUnderTest.moveUsed();
      assertThat( systemUnderTest.movesRemaining().get(), is( 19 ) );
      
      systemUnderTest.moveUsed( 10 );
      assertThat( systemUnderTest.movesRemaining().get(), is( 9 ) );
   }//End Method
   
   @Test public void shouldProvideBuilderForProperties(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      assertThat( systemUnderTest.withMoves( 34 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.movesRemaining().get(), is( 34 ) );
   }//End Method

}//End Class
