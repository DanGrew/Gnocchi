package uk.dangrew.gnocchi.grid.model;

import uk.dangrew.gnocchi.grid.square.Square;

public class GridEntry {
   
   public final Square object;
   public final GridPosition position;

   public GridEntry( Square object, int w, int h ) {
      this( object, new GridPosition( w, h ) );
   }//End Constructor
   
   public GridEntry( Square object, GridPosition position ) {
      this.object = object;
      this.position = position;
   }//End Constructor
   
}//End Class
