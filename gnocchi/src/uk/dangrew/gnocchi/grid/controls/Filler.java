package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.grid.GridModel;

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
         Object object = feeder.feed( w );
         while( object != null ) {
            gravity.pull( w, 0 );
            object = feeder.feed( w );
         }
      }
   }//End Method

}//End Class
