package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class BombCrossMatcher implements SquareMatcher {

   private final BombMatcher bombMatcher;
   private final RowMatcher horizontalMatcher;
   private final ColumnMatcher verticalMatcher;
   
   public BombCrossMatcher() {
      this.bombMatcher = new BombMatcher();
      this.horizontalMatcher = new RowMatcher();
      this.verticalMatcher = new ColumnMatcher();
   }//End Constructor
   
   @Override public List< Square > match( GridModel model, int w, int h ) {
      List< Square > matches = new ArrayList<>();
      
      Square source = model.at( w, h );
      if ( source != null ) {
         matches.addAll( bombMatcher.match( model, w, h ) );
      }
      
      source = model.at( w, h - 1 );
      if ( source != null ) {
         matches.addAll( horizontalMatcher.match( model, w, h - 1 ) );
      }
      source = model.at( w, h );
      if ( source != null ) {
         matches.addAll( horizontalMatcher.match( model, w, h ) );
      }
      source = model.at( w, h + 1 );
      if ( source != null ) {
         matches.addAll( horizontalMatcher.match( model, w, h + 1 ) );
      }
      
      source = model.at( w - 1, h );
      if ( source != null ) {
         matches.addAll( verticalMatcher.match( model, w - 1, h ) );
      }
      source = model.at( w, h );
      if ( source != null ) {
         matches.addAll( verticalMatcher.match( model, w, h ) );
      }
      source = model.at( w + 1, h );
      if ( source != null ) {
         matches.addAll( verticalMatcher.match( model, w + 1, h ) );
      }
      
      return matches;
   }//End Method

}//End Class
