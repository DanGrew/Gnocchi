package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class MassColourMatcher implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = new ArrayList<>();
      Square source = model.at( w, h );
      if ( source == null || source.colour() == null ) {
         return matches;
      }
      
      for ( int i = 0; i < model.width(); i++ ) {
         for ( int j = 0; j < model.width(); j++ ) {
            Square square = model.at( i, j );
            if ( square == null ) {
               continue;
            }
            
            if ( square.matches( source ) ) {
               matches.add( square );
            }
         }  
      }
      
      return matches;
   }//End Method

}//End Class
