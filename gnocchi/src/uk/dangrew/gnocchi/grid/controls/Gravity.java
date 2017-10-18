package uk.dangrew.gnocchi.grid.controls;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class Gravity {

   private final GridModel grid;
   
   public Gravity( GridModel grid ) {
      this.grid = grid;
   }//End Class
   
   public void pullDown( int w ) {
      List< Integer > filledSquares = new ArrayList<>(); 
      
      for ( int h = 0; h < grid.height(); h++ ) {
         if ( grid.at( w, h ) != null ) {
            filledSquares.add( h );
         }
      }
      
      int lastFilledPosition = -1;
      for ( int h = 0; h < filledSquares.size(); h++ ) {
         
         lastFilledPosition++;
         int squarePosition = filledSquares.get( h );
         
         Square next = grid.at( w, squarePosition );
         if ( !next.type().properties().isMoveable() ) {
            lastFilledPosition = squarePosition;
         }
         grid.set( next, w, lastFilledPosition );
      }
   }//End Method

}//End Class
