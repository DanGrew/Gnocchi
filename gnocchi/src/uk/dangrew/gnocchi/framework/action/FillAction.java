package uk.dangrew.gnocchi.framework.action;

import uk.dangrew.gnocchi.framework.Action;
import uk.dangrew.gnocchi.game.Game;

public class FillAction implements Action {

   private final Game game;
   
   public FillAction( Game game ) {
      this.game = game;
   }//End Constructor
   
   @Override public void execute() {
      this.game.fill();
   }//End Method

}//End Class
