package uk.dangrew.gnocchi.grid.model;

import java.util.Iterator;

public class ColumnGridIterator implements Iterator< GridEntry >{

   private final GridModel grid;
   private final int w;
   
   private int h;
   
   public ColumnGridIterator( GridModel grid, int w ) {
      this.grid = grid;
      this.w = w;
      this.h = 0;
   }//End Constructor

   @Override public boolean hasNext() {
      return h < grid.height();
   }//End Method

   @Override public GridEntry next() {
      Object next = grid.at( w, h );
      GridPosition position = new GridPosition( w, h );
      
      h++;
      return new GridEntry( next, position );
   }//End Method

}//End Class
