package uk.dangrew.gnocchi.framework.animation;

import javafx.animation.PathTransition;
import uk.dangrew.gnocchi.framework.AnimationImpl;
import uk.dangrew.gnocchi.framework.AnimationSynchronizer;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.animation.FallingPathTransitionFactory;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class GravityAnimation extends AnimationImpl {

   private final Game game;
   private final GridSnapshot snapshot;
   private final AnimationSynchronizer synchronizer;
   
   public GravityAnimation( Game game ) {
      this.game = game;
      this.snapshot = game.snapshot();
      this.synchronizer = new AnimationSynchronizer();
      this.synchronizer.setOnCompletion( this::complete );
   }//End Constructor
   
   @Override public void animate() {
      constructAnimations();
      synchronizer.playAll();
   }//End Method
   
   private void complete(){
      onCompletion().run();
   }//End Method
   
   private void constructAnimations(){
      for ( int w = 0; w < game.model().width(); w++ ) {
         for ( int h = 0; h < game.model().height(); h++ ) {
            Square square = game.model().at( w, h );
            animate( square );
         }
      }
   }//End Method
   
   private void animate( Square current ) {
      GridPosition from = snapshot.of( current );
      if ( from == null ) {
         from = new GridPosition( current.position().w, game.model().height() );
      }
      GridPosition to = current.position();
      if ( from.equals( to ) ) {
         return;
      }
      
      SquareWidget widget = game.gridUi().widgetFor( current );
      PathTransition animation = constructAnimation( widget, from, to );
      synchronizer.waitFor( animation );
   }//End Method
   
   private PathTransition constructAnimation( SquareWidget widget, GridPosition from, GridPosition to ){
      return new FallingPathTransitionFactory().create( 
               widget, from, to, game.model().width(), game.model().height() 
      );
   }//End Method

}//End Class
