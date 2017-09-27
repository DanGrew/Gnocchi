package uk.dangrew.gnocchi.game.type.colours;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.game.mechanics.GameState;
import uk.dangrew.gnocchi.game.mechanics.Logic;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class ColoursGtLogic implements Logic {
   
   private final GridModel model;
   private final FloodFill floodFill;
   private final ColoursGtProperties properties;

   public ColoursGtLogic( ColoursGtProperties properties, GridModel model ) {
      this( properties, model, new FloodFill() );
   }//End Constructor
   
   ColoursGtLogic( ColoursGtProperties properties, GridModel model, FloodFill floodFill ) {
      this.model = model;
      this.floodFill = floodFill;
      this.properties = properties;
   }//End Constructor
   
   @Override public List< Square > pop( Square object ) {
      List< Square > flood = floodFill.flood( model, object.position().w, object.position().h );
      
      if ( flood.size() < 3 ) {
         return new ArrayList<>();
      }
      for ( Square square : flood ) {
         model.remove( square );
         properties.decreaseRemaining( square.colour(), 1 );
      }
      properties.moveUsed();
      return flood;
   }//End Method
   
   @Override public GameState determineGameState() {
      boolean targetsComplete = true;
      
      for ( Color target : properties.targetColours() ) {
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
