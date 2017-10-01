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

public class ColumnMatcherTest {

   private Game game;
   private ColumnMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      game = new Game( mock( InputDriver.class ), new GameBuilder() );
      game.fill();
      
      systemUnderTest = new ColumnMatcher();
   }//End Method

   @Test public void shouldMatchAllInColumn() {
      List< Square > matches = systemUnderTest.match( game.model(), 4, 6 );
      assertThat( matches, hasSize( game.model().height() ) );
      
      for ( int h = 0; h < game.model().height(); h++ ) {
         assertThat( matches.contains( game.model().at( 4, h ) ), is( true ) );
      }
   }//End Method
   
   @Test public void shouldIgnoreNullEntries() {
      game.model().set( null, 4, 0 );
      
      List< Square > matches = systemUnderTest.match( game.model(), 4, 6 );
      assertThat( matches, hasSize( game.model().height() - 1 ) );
      
      for ( int h = 1; h < game.model().width(); h++ ) {
         assertThat( matches.contains( game.model().at( 4, h ) ), is( true ) );
      }
   }//End Method

}//End Class
