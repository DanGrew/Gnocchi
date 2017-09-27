package uk.dangrew.gnocchi.ui.animator;

import java.util.List;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.animation.PopAnimation;

public class PopAnimator {

   private final PopAnimation animation;
   
   private Game game;
   
   public PopAnimator() {
      this( new PopAnimation() );
   }//End Constructor
   
   PopAnimator( PopAnimation animation ) {
      this.animation = animation;
   }//End Constructor
   
   public void hook( Game game ) {
      this.game = game;
      this.animation.hook( game );
   }//End Method

   public void pop( Square object ) {
      List< Square > flood = game.pop( object );
      flood.forEach( animation::animate );
   }//End Method
   
}//End Class
