package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.game.mechanics.Feeder;
import uk.dangrew.gnocchi.grid.model.GridModel;

public class Filler {

   private final GridModel grid;
   
   private final Feeder feeder;
   private final Gravity gravity;
   
   public Filler( GridModel grid, Feeder feeder ) {
      this.grid = grid;
      
      this.feeder = feeder;
      this.gravity = new Gravity( grid );
   }//End Constructor

   public void fill() {
      for ( int h = 0; h < grid.height(); h++ ) {
         for ( int w = 0; w < grid.width(); w++ ) {
            if ( !grid.isEmpty( w, h ) ) {
               continue;
            }
            feeder.feed( w );
            gravity.pullDown( w );
         }
      }
      
      for ( int h = 0; h < grid.height(); h++ ) {
         for ( int w = 0; w < grid.width(); w++ ) {
            if ( !grid.isEmpty( w, h ) ) {
               continue;
            }
            gravity.slideForGaps( w );
            feeder.feed( w );
            gravity.pullDown( w );
         }
      }
   }//End Method
   
}//End Class
