package uk.dangrew.gnocchi.grid.model;

import java.util.Iterator;

import uk.dangrew.gnocchi.grid.square.Square;

public class BottomUpGridIterator implements Iterator< GridEntry >{

   private final GridModel grid;
   
   private int w;
   private int h;
   
   public BottomUpGridIterator( GridModel grid ) {
      this.grid = grid;
      this.w = 0;
      this.h = 0;
   }//End Constructor

   @Override public boolean hasNext() {
      return w < grid.width() && h < grid.height();
   }//End Method

   @Override public GridEntry next() {
      Square next = grid.at( w, h );
      GridPosition position = new GridPosition( w, h );
      
      w++;
      if ( w >= grid.width() ) {
         w = 0;
         h++;
      }
      return new GridEntry( next, position );
   }//End Method

}//End Class
