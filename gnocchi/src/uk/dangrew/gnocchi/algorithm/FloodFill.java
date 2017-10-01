package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;

public class FloodFill implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = flood( model, w, h );
      if ( matches.size() < 3 ) {
         matches.clear();
      }
      return matches;
   }//End Method
   
   public List< Square > flood( GridModel model, int w, int h ) {
      Set< GridPosition > searched = new HashSet<>();
      List< Square > found = new ArrayList<>();
      
      Square source = model.at( w, h );
      if ( source != null ) {
         GridPosition start = new GridPosition( w, h );
         search( model, start, start, searched, found );
      }
      return found;
   }//End Method
   
   private void search( 
            GridModel model, 
            GridPosition source, 
            GridPosition position, 
            Set< GridPosition > searched, 
            List< Square > found 
   ){
      if ( searched.contains( position ) ) {
         return;
      }
      
      searched.add( position );
      Square selectedSquare = model.at( source.w, source.h );
      Square positionSquare = model.at( position.w, position.h );
      if ( positionSquare == null ) {
         return;
      }
      
      if ( !selectedSquare.matches( positionSquare ) ) {
         return;
      }
      
      found.add( positionSquare );
      search( model, source, new GridPosition( position.w + 1, position.h ), searched, found );
      search( model, source, new GridPosition( position.w - 1, position.h ), searched, found );
      search( model, source, new GridPosition( position.w, position.h + 1), searched, found );
      search( model, source, new GridPosition( position.w, position.h - 1 ), searched, found );
   }//End Method

}//End Class
