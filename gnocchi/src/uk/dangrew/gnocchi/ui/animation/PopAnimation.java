package uk.dangrew.gnocchi.ui.animation;

import java.util.List;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;

public class PopAnimation {
   
   private Game game;
   
   public void hook( Game game ) {
      this.game = game;
   }//End Class
   
   public void animate( List< Square > objects ) {
      //no animation yet
   }//End Method
   
}//End Class
