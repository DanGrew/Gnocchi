package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.grid.model.GridModel;

public class Filler {

   private final GridModel grid;
   
   private final Feeder feeder;
   private final Gravity gravity;
   
   public Filler( GridModel grid ) {
      this.grid = grid;
      
      this.feeder = new Feeder( grid );
      this.gravity = new Gravity( grid );
   }//End Constructor

   public void fill() {
      for ( int w = 0; w < grid.width(); w++ ) {
         for ( int h = 0; h < grid.height(); h++ ) {
            if ( !grid.isEmpty( w, h ) ) {
               continue;
            }
            feeder.feed( w );
            gravity.pullDown( w );
         }
      }
   }//End Method
   
}//End Class
