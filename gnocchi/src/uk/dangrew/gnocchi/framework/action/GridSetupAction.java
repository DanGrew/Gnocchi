package uk.dangrew.gnocchi.framework.action;

import uk.dangrew.gnocchi.framework.Action;
import uk.dangrew.gnocchi.game.Game;

public class GridSetupAction implements Action {

   private final Game game;
   
   public GridSetupAction( Game game ) {
      this.game = game;
   }//End Constructor
   
   @Override public void execute() {
      this.game.model().reset();
   }//End Method

}//End Class
