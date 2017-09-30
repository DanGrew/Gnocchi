package uk.dangrew.gnocchi.framework.animation;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.animation.FadeTransition;
import uk.dangrew.gnocchi.framework.AnimationSynchronizer;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.animation.FadeTransitionFactory;
import uk.dangrew.gnocchi.ui.square.SquareWidget;
import uk.dangrew.kode.launch.TestApplication;

public class BlastAnimationTest {

   private Game game;
   private List< Square > squares;
   private SquareWidget widget1;
   private SquareWidget widget2;

   @Captor private ArgumentCaptor< Runnable > completionCaptor;
   @Mock private AnimationSynchronizer synchronizer;
   
   private FadeTransition transition1;
   private FadeTransition transition2;
   @Mock private FadeTransitionFactory fadeFactory;
   private BlastAnimation systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      game = new Game( mock( InputDriver.class ), new GameBuilder() );
      game.fill();
      
      squares = Arrays.asList( game.model().at( 0, 0 ), game.model().at( 0, 1 ) );
      widget1 = game.gridUi().widgetFor( squares.get( 0 ) );
      widget2 = game.gridUi().widgetFor( squares.get( 1 ) );
      
      transition1 = new FadeTransition();
      when( fadeFactory.create( widget1 ) ).thenReturn( transition1 );
      transition2 = new FadeTransition();
      when( fadeFactory.create( widget2 ) ).thenReturn( transition2 );
      
      systemUnderTest = new BlastAnimation( fadeFactory, synchronizer, game, squares );
   }//End Method

   @Test public void shouldSetOnCompletionForSynchronizer() {
      verify( synchronizer ).setOnCompletion( completionCaptor.capture() );
      
      Runnable runnable = mock( Runnable.class ); 
      systemUnderTest.setOnCompletion( runnable );
      completionCaptor.getValue().run();
      verify( runnable ).run();
   }//End Method
   
   @Test public void shouldCreateFadePerSquare(){
      systemUnderTest.animate();
      
      InOrder order = inOrder( synchronizer );
      order.verify( synchronizer ).waitFor( transition1 );
      order.verify( synchronizer ).waitFor( transition2 );
      order.verify( synchronizer ).playAll();
   }//End Method
   
   @Test public void shouldAddDelayToTransitions(){
      systemUnderTest.animate();
      assertThat( transition1.getDelay().toMillis(), is( 0.0 ) );
      assertThat( transition2.getDelay().toMillis(), is( 50.0 ) );
   }//End Method
   
   @Test public void shouldClearWidgetsOnCompletion(){
      assertThat( game.gridUi().widgetFor( squares.get( 0 ) ), is( widget1 ) );
      assertThat( game.gridUi().widgetFor( squares.get( 1 ) ), is( widget2 ) );
      
      systemUnderTest.setOnCompletion( () -> {} );
      systemUnderTest.animate();
      verify( synchronizer ).setOnCompletion( completionCaptor.capture() );
      completionCaptor.getValue().run();
      
      assertThat( game.gridUi().widgetFor( squares.get( 0 ) ), is( not( widget1 ) ) );
      assertThat( game.gridUi().widgetFor( squares.get( 1 ) ), is( not( widget2 ) ) );
   }//End Method


}//End Class
