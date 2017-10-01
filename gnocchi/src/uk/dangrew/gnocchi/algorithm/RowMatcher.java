package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class RowMatcher implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = new ArrayList<>();
      for ( int i = 0; i < model.width(); i++ ) {
         if ( !model.isEmpty( i, h ) ) {
            matches.add( model.at( i, h ) );
         }
      }
      return matches;
   }//End Method

}//End Class
