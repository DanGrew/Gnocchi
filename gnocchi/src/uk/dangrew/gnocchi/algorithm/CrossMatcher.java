package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class CrossMatcher implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      Set< Square > matches = new LinkedHashSet<>();
      for ( int i = 0; i < model.height(); i++ ) {
         if ( !model.isEmpty( w, i ) ) {
            matches.add( model.at( w, i ) );
         }
      }
      for ( int i = 0; i < model.height(); i++ ) {
         if ( !model.isEmpty( i, h ) ) {
            matches.add( model.at( i, h ) );
         }
      }
      return new ArrayList<>( matches );
   }//End Method

}//End Class
