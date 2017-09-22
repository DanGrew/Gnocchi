package uk.dangrew.gnocchi.ui.animator;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.ui.animation.GravityAnimation;

public class GravityAnimator {

   private final GravityAnimation animation;
   
   private Game game;
   
   public GravityAnimator() {
      this( new GravityAnimation() );
   }//End Constructor
   
   GravityAnimator( GravityAnimation animation ) {
      this.animation = animation;
   }//End Constructor
   
   public void hook( Game game ) {
      this.game = game;
      this.animation.hook( game );
   }//End Method

   public void fillGrid() {
      GridSnapshot snapshot = game.snapshot();
      game.fill();
      animation.animate( snapshot );
   }//End Method

}//End Class
