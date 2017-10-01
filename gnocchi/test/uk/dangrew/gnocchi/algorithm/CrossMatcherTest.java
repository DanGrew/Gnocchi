package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.kode.launch.TestApplication;

public class CrossMatcherTest {

   private Game game;
   private CrossMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      game = new Game( mock( InputDriver.class ), new GameBuilder() );
      game.fill();
      
      systemUnderTest = new CrossMatcher();
   }//End Method

   @Test public void shouldMatchAllInColumnAndRow() {
      List< Square > matches = systemUnderTest.match( game.model(), 4, 6 );
      assertThat( matches, hasSize( game.model().height() + game.model().width() - 1 ) );
      
      for ( int w = 0; w < game.model().width(); w++ ) {
         for ( int h = 0; h < game.model().height(); h++ ) {
            assertThat( matches.contains( game.model().at( w, h ) ), is( w == 4 || h == 6 ) );
         }
      }
   }//End Method
   
   @Test public void shouldIgnoreNullEntries() {
      game.model().set( null, 4, 0 );
      game.model().set( null, 0, 6 );
      
      List< Square > matches = systemUnderTest.match( game.model(), 4, 6 );
      assertThat( matches, hasSize( game.model().height() + game.model().width() - 3 ) );
      
      for ( int w = 0; w < game.model().width(); w++ ) {
         for ( int h = 0; h < game.model().height(); h++ ) {
            Square square = game.model().at( w, h );
            if ( square == null ) {
               continue;
            }
            assertThat( matches.contains( square ), is( w == 4 || h == 6 ) );
         }
      }
   }//End Method

}//End Class
