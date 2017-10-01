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

public class RowMatcherTest {

   private Game game;
   private RowMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      game = new Game( mock( InputDriver.class ), new GameBuilder() );
      game.fill();
      
      systemUnderTest = new RowMatcher();
   }//End Method

   @Test public void shouldMatchAllInRow() {
      List< Square > matches = systemUnderTest.match( game.model(), 4, 6 );
      assertThat( matches, hasSize( game.model().width() ) );
      
      for ( int w = 0; w < game.model().width(); w++ ) {
         assertThat( matches.contains( game.model().at( w, 6 ) ), is( true ) );
      }
   }//End Method
   
   @Test public void shouldIgnoreNullEntries() {
      game.model().set( null, 0, 6 );
      
      List< Square > matches = systemUnderTest.match( game.model(), 4, 6 );
      assertThat( matches, hasSize( game.model().width() - 1 ) );
      
      for ( int w = 1; w < game.model().width(); w++ ) {
         assertThat( matches.contains( game.model().at( w, 6 ) ), is( true ) );
      }
   }//End Method

}//End Class
