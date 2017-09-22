package uk.dangrew.gnocchi.ui.animation;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;

public class PopAnimation {
   
   private Game game;
   
   public void hook( Game game ) {
      this.game = game;
   }//End Class
   
   public void animate( Square object ) {
      game.ui().removeWidget( object );
   }//End Method

}//End Class
