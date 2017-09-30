package uk.dangrew.gnocchi.framework.animation;

import java.util.List;

import javafx.animation.FadeTransition;
import uk.dangrew.gnocchi.framework.AnimationImpl;
import uk.dangrew.gnocchi.framework.AnimationSynchronizer;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.animation.FadeTransitionFactory;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class PopAnimation extends AnimationImpl {

   private final Game game;
   private final List< Square > connectedSquares;
   
   private final FadeTransitionFactory fadeFactory;
   private final AnimationSynchronizer synchronizer;
   
   public PopAnimation( Game game, List< Square > connectedSquares ) {
      this( new FadeTransitionFactory(), new AnimationSynchronizer(), game, connectedSquares );
   }//End Constructor
   
   public PopAnimation( FadeTransitionFactory fadeFactory, AnimationSynchronizer synchronizer, Game game, List< Square > connectedSquares ) {
      this.game = game;
      this.fadeFactory = fadeFactory;
      this.connectedSquares = connectedSquares;
      this.synchronizer = synchronizer;
      this.synchronizer.setOnCompletion( this::complete );
   }//End Constructor
   
   @Override public void animate() {
      for ( Square square : connectedSquares ) {
         SquareWidget widget = game.gridUi().widgetFor( square );
         FadeTransition fade = fadeFactory.create( widget );
         synchronizer.waitFor( fade );
      }
      synchronizer.playAll();
   }//End Method
   
   private void complete(){
      for ( Square square : connectedSquares ) {
         game.gridUi().removeWidget( square );
      }
      onCompletion().run();
   }//End Method

}//End Class
