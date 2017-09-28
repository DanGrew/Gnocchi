package uk.dangrew.gnocchi.ui.animator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtFeeder;
import uk.dangrew.gnocchi.grid.controls.Filler;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.animation.PopAnimation;
import uk.dangrew.kode.launch.TestApplication;

public class ColourMatchPopAnimatorTest {

   private Game game;
   @Mock private FloodFill floodFill;
   @Mock private PopAnimation popAnimation;
   @Mock private GravityAnimator gravityAnimator;
   private ColourMatchPopAnimator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      game = new Game( mock( InputDriver.class ), new GameBuilder() );
      new Filler( game.model(), new ColoursGtFeeder( game.model() ) ).fill();
      
      systemUnderTest = new ColourMatchPopAnimator( popAnimation, gravityAnimator, floodFill );
      systemUnderTest.hook( game );
   }//End Method

   @Test public void shouldPopColoursMatchesFromGridAndFill() {
      Square square = game.model().at( 4, 5 );
      Square match1 = game.model().at( 2, 3 );
      Square match2 = game.model().at( 1, 3 );
      Square match3 = game.model().at( 2, 2 );
      
      List< Square > matches = Arrays.asList( match1, match2, match3 );
      when( floodFill.flood( game.model(), square.position().w, square.position().h ) ).thenReturn( matches );
      assertThat( systemUnderTest.pop( square ), is( true ) );
      assertThat( game.model().isEmpty( 2, 3 ), is( true ) );
      assertThat( game.model().isEmpty( 1, 3 ), is( true ) );
      assertThat( game.model().isEmpty( 2, 2 ), is( true ) );
      
      verify( gravityAnimator ).fillGrid();
   }//End Method
   
   @Test public void shouldNotPopTwoOrLess(){
      Square square = game.model().at( 4, 5 );
      Square match1 = game.model().at( 2, 3 );
      Square match2 = game.model().at( 1, 3 );
      
      List< Square > matches = Arrays.asList( match1, match2 );
      when( floodFill.flood( game.model(), square.position().w, square.position().h ) )
         .thenReturn( matches )
         .thenReturn( Arrays.asList( match1 ) );
      assertThat( systemUnderTest.pop( square ), is( false ) );
      assertThat( systemUnderTest.pop( square ), is( false ) );
      
      assertThat( game.model().isEmpty( 2, 3 ), is( false ) );
      assertThat( game.model().isEmpty( 1, 3 ), is( false ) );
   }//End Method

}//End Class
