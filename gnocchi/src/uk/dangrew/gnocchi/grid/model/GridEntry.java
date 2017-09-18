package uk.dangrew.gnocchi.grid.model;

public class GridEntry {
   
   public final Object object;
   public final GridPosition position;

   public GridEntry( Object object, int w, int h ) {
      this( object, new GridPosition( w, h ) );
   }//End Constructor
   
   public GridEntry( Object object, GridPosition position ) {
      this.object = object;
      this.position = position;
   }//End Constructor
   
}//End Class
