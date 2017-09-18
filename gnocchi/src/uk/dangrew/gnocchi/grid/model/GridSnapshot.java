package uk.dangrew.gnocchi.grid.model;

import java.util.HashMap;
import java.util.Map;

public class GridSnapshot {

   private final Map< Object, GridPosition > existingObjects;
   private final Map< GridPosition, Object > existingPositions;
   
   GridSnapshot( GridModel grid ) {
      this.existingObjects = new HashMap<>();
      this.existingPositions = new HashMap<>();
      
      grid.bottomUpIterator().forEachRemaining( e -> {
         existingObjects.put( e.object, e.position );
         existingPositions.put( e.position, e.object );
      } );
   }//End Constructor

   public Object at( int w, int h ) {
      return existingPositions.get( new GridPosition( w, h ) );
   }//End Method

   public GridPosition of( Object object ) {
      return existingObjects.get( object );
   }//End Method

}//End Class
