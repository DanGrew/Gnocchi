package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.kode.launch.TestApplication;

public class BombMatcherTest {

   private Game game;
   private BombMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      game = new Game( mock( InputDriver.class ), new GameBuilder() );
      game.fill();
      
      systemUnderTest = new BombMatcher();
   }//End Method

   @Test public void shouldMatchImmediateSurroundings() {
      assertThat( systemUnderTest.match( game.model(), 5, 5 ), containsInAnyOrder( 
               game.model().at( 3, 3 ),
               game.model().at( 4, 3 ),
               game.model().at( 5, 3 ),
               game.model().at( 6, 3 ),
               game.model().at( 7, 3 ),
               game.model().at( 3, 4 ),
               game.model().at( 4, 4 ),
               game.model().at( 5, 4 ),
               game.model().at( 6, 4 ),
               game.model().at( 7, 4 ),
               game.model().at( 3, 5 ),
               game.model().at( 4, 5 ),
               game.model().at( 5, 5 ),
               game.model().at( 6, 5 ),
               game.model().at( 7, 5 ),
               game.model().at( 3, 6 ), 
               game.model().at( 4, 6 ), 
               game.model().at( 5, 6 ), 
               game.model().at( 6, 6 ), 
               game.model().at( 7, 6 ),
               game.model().at( 3, 7 ),
               game.model().at( 4, 7 ),
               game.model().at( 5, 7 ),
               game.model().at( 6, 7 ),
               game.model().at( 7, 7 ) 
      ) );
   }//End Method
   
   @Test public void shouldMatchInBottomLeftCorner() {
      assertThat( systemUnderTest.match( game.model(), 0, 0 ), containsInAnyOrder( 
               game.model().at( 0, 0 ),
               game.model().at( 0, 1 ),
               game.model().at( 0, 2 ),
               game.model().at( 1, 0 ),
               game.model().at( 1, 1 ),
               game.model().at( 1, 2 ),
               game.model().at( 2, 0 ),
               game.model().at( 2, 1 ),
               game.model().at( 2, 2 )
      ) );
   }//End Method
   
   @Test public void shouldMatchInTopRightCorner() {
      assertThat( systemUnderTest.match( game.model(), 9, 9 ), containsInAnyOrder( 
               game.model().at( 9, 9 ),
               game.model().at( 9, 8 ),
               game.model().at( 9, 7 ),
               game.model().at( 8, 9 ),
               game.model().at( 8, 8 ),
               game.model().at( 8, 7 ),
               game.model().at( 7, 9 ),
               game.model().at( 7, 8 ),
               game.model().at( 7, 7 )
      ) );
   }//End Method

}//End Class
