package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class Gravity {

   private final GridModel grid;
   
   public Gravity( GridModel grid ) {
      this.grid = grid;
   }//End Class
   
   public void pullDown( int w ) {
      for ( int h = 0; h < grid.height(); h++ ) {
         if ( grid.at( w, h ) == null ) {
            moveNextAboveDown( w, h );
         }
      }
   }//End Method

   private void moveNextAboveDown( int w, int h ) {
      Square above = null;
      for ( int i = h; i < grid.height(); i++ ) {
         above = grid.at( w, i + 1 ); 
         if ( above != null ) {
            break;
         }
      }
      if ( above == null ) {
         return;
      }
      grid.set( above, w, h );
   }//End Method

}//End Class
