package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class BonusMatcher implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = new ArrayList<>();
      
      Square source = model.at( w, h );
      if ( 
               source == null || 
               !source.type().properties().isComboEnabled() 
      ) {
         return matches;
      }
      
      for ( int i = w - 1; i <= w + 1; i++ ) {
         for ( int j = h - 1; j <= h + 1; j++ ) {
            if ( i == w && j == h ) {
               continue;
            }
            
            if ( !model.isEmpty( i, j ) ) {
               Square square = model.at( i, j );
               if ( square.type().properties().isComboEnabled() ) {
                  matches.add( square );
               }
            }
         }
      }
      
      return matches;
   }//End Method

}//End Class
