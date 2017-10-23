package uk.dangrew.gnocchi.game.type.colours;

import uk.dangrew.gnocchi.game.type.GameInformation;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class ColoursGtPropertyInformationProvider {
   
   public void configure( ColoursGtProperties properties, GameInformation information ) {
      for ( SquareType type : properties.targetTypes() ) {
         information.putProperty( "Target for " + type.name(), Integer.toString( properties.remainingFor( type ) ) );
      }
   }//End Constructor

}//End Class
