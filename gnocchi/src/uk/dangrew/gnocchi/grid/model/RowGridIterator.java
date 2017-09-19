package uk.dangrew.gnocchi.grid.model;

import java.util.Iterator;

import uk.dangrew.gnocchi.grid.square.Square;

public class RowGridIterator implements Iterator< GridEntry >{

   private final GridModel grid;
   private final int h;
   
   private int w;
   
   public RowGridIterator( GridModel grid, int h ) {
      this.grid = grid;
      this.w = 0;
      this.h = h;
   }//End Constructor

   @Override public boolean hasNext() {
      return w < grid.width();
   }//End Method

   @Override public GridEntry next() {
      Square next = grid.at( w, h );
      GridPosition position = new GridPosition( w, h );
      
      w++;
      return new GridEntry( next, position );
   }//End Method

}//End Class
