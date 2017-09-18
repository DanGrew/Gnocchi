package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.grid.model.GridModel;

public class Feeder {
   
   private final GridModel grid;

   public Feeder( GridModel grid ) {
      this.grid = grid;
   }//End Constructor

   public Object feed( int w ) {
      if ( grid.at( w, grid.lastHeightIndex() ) != null ) {
         return null;
      }
      Object object = new Object();
      grid.set( object, w, grid.lastHeightIndex() );
      return object;
   }//End Method

}//End Class
