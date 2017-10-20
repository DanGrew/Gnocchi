package uk.dangrew.gnocchi.game.type.colours;

import java.util.Collection;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.game.mechanics.GameState;
import uk.dangrew.gnocchi.game.mechanics.Logic;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class ColoursGtLogic implements Logic {
   
   private final ColoursGtProperties properties;

   public ColoursGtLogic( ColoursGtProperties properties ) {
      this.properties = properties;
   }//End Constructor
   
   @Override public void pop( Square object ) {
      properties.decreaseRemaining( object.type(), 1 );
   }//End Method
   
   @Override public void popAll( Collection< Square > objects ) {
      objects.forEach( this::pop );
   }//End Method
   
   @Override public GameState determineGameState() {
      boolean targetsComplete = true;
      
      for ( SquareType target : properties.targetTypes() ) {
         if ( properties.remainingFor( target ) > 0 ) {
            targetsComplete = false;
            break;
         }
      }
      
      if ( targetsComplete ) {
         return GameState.Success;
      } else if ( properties.movesRemaining().get() <= 0 ) {
         return GameState.Failure;
      } else {
         return GameState.Active;
      }
   }//End Method

}//End Class
