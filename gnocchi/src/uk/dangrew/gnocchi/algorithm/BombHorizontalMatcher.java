package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class BombHorizontalMatcher implements SquareMatcher {

   private final BombMatcher bombMatcher;
   private final RowMatcher horizontalMatcher;
   
   public BombHorizontalMatcher() {
      this.bombMatcher = new BombMatcher();
      this.horizontalMatcher = new RowMatcher();
   }//End Constructor
   
   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = new ArrayList<>();
      
      Square bombSource = model.at( w, h );
      if ( bombSource != null ) {
         matches.addAll( bombMatcher.match( model, w, h ) );
      }
      
      Square hSource = model.at( w, h - 1 );
      if ( hSource != null ) {
         matches.addAll( horizontalMatcher.match( model, w, h - 1 ) );
      }
      hSource = model.at( w, h );
      if ( hSource != null ) {
         matches.addAll( horizontalMatcher.match( model, w, h ) );
      }
      hSource = model.at( w, h + 1 );
      if ( hSource != null ) {
         matches.addAll( horizontalMatcher.match( model, w, h + 1 ) );
      }
      
      return matches;
   }//End Method

}//End Class
