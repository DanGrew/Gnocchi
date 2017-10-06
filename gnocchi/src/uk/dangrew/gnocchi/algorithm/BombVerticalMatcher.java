package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class BombVerticalMatcher implements SquareMatcher {

   private final BombMatcher bombMatcher;
   private final ColumnMatcher verticalMatcher;
   
   public BombVerticalMatcher() {
      this.bombMatcher = new BombMatcher();
      this.verticalMatcher = new ColumnMatcher();
   }//End Constructor
   
   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = new ArrayList<>();
      
      Square bombSource = model.at( w, h );
      if ( bombSource != null ) {
         matches.addAll( bombMatcher.match( model, w, h ) );
      }
      
      Square vSource = model.at( w - 1, h );
      if ( vSource != null ) {
         matches.addAll( verticalMatcher.match( model, w - 1, h ) );
      }
      vSource = model.at( w, h );
      if ( vSource != null ) {
         matches.addAll( verticalMatcher.match( model, w, h ) );
      }
      vSource = model.at( w + 1, h );
      if ( vSource != null ) {
         matches.addAll( verticalMatcher.match( model, w + 1, h ) );
      }
      
      return matches;
   }//End Method

}//End Class
