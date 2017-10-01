package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class BombMatcher implements SquareMatcher {
   
   private static final int RANGE = 2;

   @Override public List< Square > match( GridModel model, int w, int h ) {
      int widthFrom = Math.max( w - RANGE, 0 );
      int widthTo = Math.min( w + RANGE, model.lastWidthIndex() );
      
      int heightFrom = Math.max( h - RANGE, 0 );
      int heightTo = Math.min( h + RANGE, model.lastHeightIndex() );
      
      List< Square > matches = new ArrayList<>();
      for ( int i = widthFrom; i <= widthTo; i++ ) {
         for ( int j = heightFrom; j <= heightTo; j++ ) {
            if ( !model.isEmpty( i, j ) ) {
               matches.add( model.at( i, j ) );
            }
         }
      }
      
      return matches;
   }//End Method

}//End Class
