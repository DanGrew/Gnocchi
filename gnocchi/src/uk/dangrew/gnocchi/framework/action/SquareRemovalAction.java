package uk.dangrew.gnocchi.framework.action;

import java.util.List;

import uk.dangrew.gnocchi.framework.Action;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;

public class SquareRemovalAction implements Action {

   private final Game game;
   private final List< Square > squaresPopped;
   
   public SquareRemovalAction( Game game, List< Square > squaresPopped ) {
      this.game = game;
      this.squaresPopped = squaresPopped;
   }//End Constructor
   
   @Override public void execute() {
      this.squaresPopped.forEach( s -> game.model().remove( s ) );
      this.game.logic().popAll( squaresPopped );
      this.game.properties().moveUsed();
   }//End Method

}//End Class
