package uk.dangrew.gnocchi.grid.controls;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.mechanics.ColorGenerator;

public class Feeder {
   
   private final ColorGenerator colours;
   private final GridModel grid;

   public Feeder( GridModel grid ) {
      this( new ColorGenerator(), grid );
   }//End Constructor
   
   Feeder( ColorGenerator colours, GridModel grid ) {
      this.colours = colours;
      this.grid = grid;
   }//End Constructor

   public Square feed( int w ) {
      if ( grid.at( w, grid.lastHeightIndex() ) != null ) {
         return null;
      }
      Square object = new Square( colours.next( grid.colourVariation() ) );
      grid.set( object, w, grid.lastHeightIndex() );
      return object;
   }//End Method

}//End Class
