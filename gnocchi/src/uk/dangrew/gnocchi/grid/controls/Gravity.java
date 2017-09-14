package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.grid.GridModel;

public class Gravity {

   private final GridModel grid;
   
   public Gravity( GridModel grid ) {
      this.grid = grid;
   }//End Class

   public boolean pull( int w, int h ) {
      Object object = grid.at( w, h );
      if ( object == null ) {
         return false;
      }
      
      if ( grid.at( w, h + 1 ) != null ) {
         return false;
      }
      
      for ( int i = h + 1; i < grid.height(); i++ ) {
         if ( grid.at( w, i ) != null ) {
            break;
         }
         
         grid.set( null, w, i - 1 );
         grid.set( object, w, i );
      }
      return true;
   }//End Method

}//End Class
