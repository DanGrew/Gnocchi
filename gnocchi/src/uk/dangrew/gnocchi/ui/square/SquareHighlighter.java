package uk.dangrew.gnocchi.ui.square;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;

public class SquareHighlighter {

   private final Map< Square, SquareWidget > widgets;
   private final FloodFill floodFill;
   
   public SquareHighlighter( FloodFill floodFill ) {
      this.floodFill = floodFill;
      this.widgets = new HashMap<>();
   }//End Constructor

   public void monitor( SquareWidget widget ) {
      widgets.put( widget.association(), widget );
      widget.setOnMouseEntered( e -> flood( widget ) );
      widget.setOnMouseExited( e -> flush( widget ) );
   }//End Method
   
   public void remove( Square object ) {
      SquareWidget widget = widgets.remove( object );
      if ( widget != null ) {
         widget.unhighlight();
      }
   }//End Method
   
   private void flood( SquareWidget widget ) {
      GridPosition position = widget.association().position();
      List< Square > connected = floodFill.flood( position.w, position.h );
      connected.forEach( s -> { 
         if ( widgets.get( s ) != null ) {
            widgets.get( s ).highlight(); 
         }
      } );
   }//End Method
   
   private void flush( SquareWidget widget ) {
      GridPosition position = widget.association().position();
      List< Square > connected = floodFill.flood( position.w, position.h );
      connected.forEach( s -> { 
         if ( widgets.get( s ) != null ) {
            widgets.get( s ).unhighlight(); 
         }
      } );
   }//End Method
   
}//End Class
