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
      
      for ( int h = 0; h < filledSquares.size(); h++ ) {
         Square next = grid.at( w, filledSquares.get( h ) );
         grid.set( next, w, h );
      }
   }//End Method

}//End Class
