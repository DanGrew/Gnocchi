package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;

public class FloodFill {

   private final GridModel grid;
   
   public FloodFill( GridModel grid ) {
      this.grid = grid;
   }//End Constructor

   public List< Square > flood( int w, int h ) {
      Set< GridPosition > searched = new HashSet<>();
      List< Square > found = new ArrayList<>();
      
      Square source = grid.at( w, h );
      if ( source != null ) {
         GridPosition start = new GridPosition( w, h );
         search( start, start, searched, found );
      }
      return found;
   }//End Method
   
   private void search( GridPosition source, GridPosition position, Set< GridPosition > searched, List< Square > found ){
      if ( searched.contains( position ) ) {
         return;
      }
      
      searched.add( position );
      Square selectedSquare = grid.at( source.w, source.h );
      Square positionSquare = grid.at( position.w, position.h );
      if ( positionSquare == null ) {
         return;
      }
      
      if ( !selectedSquare.matches( positionSquare ) ) {
         return;
      }
      
      found.add( positionSquare );
      search( source, new GridPosition( position.w + 1, position.h ), searched, found );
      search( source, new GridPosition( position.w - 1, position.h ), searched, found );
      search( source, new GridPosition( position.w, position.h + 1), searched, found );
      search( source, new GridPosition( position.w, position.h - 1 ), searched, found );
   }//End Method

}//End Class
