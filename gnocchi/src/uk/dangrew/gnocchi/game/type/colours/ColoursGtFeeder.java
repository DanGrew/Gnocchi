package uk.dangrew.gnocchi.game.type.colours;

import uk.dangrew.gnocchi.game.mechanics.Feeder;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.mechanics.SquareTypeGenerator;

public class ColoursGtFeeder implements Feeder {
   
   private final SquareTypeGenerator colours;
   private final GridModel grid;

   public ColoursGtFeeder( GridModel grid ) {
      this( new SquareTypeGenerator(), grid );
   }//End Constructor
   
   ColoursGtFeeder( SquareTypeGenerator colours, GridModel grid ) {
      this.colours = colours;
      this.grid = grid;
   }//End Constructor

   @Override public Square feed( int w ) {
      if ( grid.at( w, grid.lastHeightIndex() ) != null ) {
         return null;
      }
      Square object = new Square( colours.next( grid.colourVariation() ) );
      grid.set( object, w, grid.lastHeightIndex() );
      return object;
   }//End Method

}//End Class
