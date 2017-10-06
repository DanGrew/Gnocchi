package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class BombMatcher implements SquareMatcher {
   
   private final int range;
   
   public BombMatcher() {
      this( 2 );
   }//End Constructor
   
   public BombMatcher( int range ) {
      this.range = range;
   }//End Constructor

   @Override public List< Square > match( GridModel model, int w, int h ) {
      int widthFrom = Math.max( w - range, 0 );
      int widthTo = Math.min( w + range, model.lastWidthIndex() );
      
      int heightFrom = Math.max( h - range, 0 );
      int heightTo = Math.min( h + range, model.lastHeightIndex() );
      
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
