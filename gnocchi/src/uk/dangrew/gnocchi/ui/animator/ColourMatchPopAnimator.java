package uk.dangrew.gnocchi.ui.animator;

import java.util.List;

import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.mechanics.PopBehaviour;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.animation.PopAnimation;

public class ColourMatchPopAnimator implements PopBehaviour {

   private final FloodFill floodFill;
   private final GravityAnimator gravityAnimator;
   private final PopAnimation animation;
   
   private Game game;
   
   public ColourMatchPopAnimator() {
      this( new PopAnimation(), new GravityAnimator(), new FloodFill() );
   }//End Constructor
   
   ColourMatchPopAnimator( PopAnimation animation, GravityAnimator gravityAnimator, FloodFill floodFill ) {
      this.animation = animation;
      this.floodFill = floodFill;
      this.gravityAnimator = gravityAnimator;
   }//End Constructor
   
   public void hook( Game game ) {
      this.game = game;
      this.animation.hook( game );
      this.gravityAnimator.hook( game );
   }//End Method

   @Override public boolean pop( Square object ) {
      List< Square > flood = floodFill.flood( game.model(), object.position().w, object.position().h );
      
      if ( flood.size() < 3 ) {
         return false;
      }
      for ( Square square : flood ) {
         game.model().remove( square );
      }
      game.logic().popAll( flood );
      animation.animate( flood );
      flood.forEach( o -> game.gridUi().removeWidget( o ) );
      
      gravityAnimator.fillGrid();
      return true;
   }//End Method
   
}//End Class
