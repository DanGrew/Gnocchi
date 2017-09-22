package uk.dangrew.gnocchi.ui.animator;

import java.util.List;

import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.animation.PopAnimation;

public class PopAnimator {

   private final FloodFill floodFill;
   private final PopAnimation animation;
   
   private Game game;
   
   public PopAnimator() {
      this( new PopAnimation(), new FloodFill() );
   }//End Constructor
   
   PopAnimator( PopAnimation animation, FloodFill floodFill ) {
      this.animation = animation;
      this.floodFill = floodFill;
   }//End Constructor
   
   public void hook( Game game ) {
      this.game = game;
      this.animation.hook( game );
   }//End Method

   public void pop( Square object ) {
      List< Square > flood = floodFill.flood( game.model(), object.position().w, object.position().h );
      flood.forEach( o -> game.model().remove( o ) );
      flood.forEach( o -> animation.animate( o ) );
   }//End Method
   
}//End Class
