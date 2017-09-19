package uk.dangrew.gnocchi.ui.grid;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.square.SquareHighlighter;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class GridWidget extends Group {
   
   private final Map< Object, SquareWidget > widgets;
   private final SquareHighlighter highlighter;
   
   public GridWidget( Grid grid ) {
      this.widgets = new HashMap<>();
      this.highlighter = new SquareHighlighter( new FloodFill( grid.model() ) );
   }//End Class
   
   public Rectangle widgetFor( Square object ) {
      SquareWidget widget = widgets.get( object );
      if ( widget == null ) {
         if ( object == null ) {
            toString();
         }
         widget = new SquareWidget( object, new GridPosition( -1, -1 ) );
         new InputDriver().popAction( widget );
         highlighter.monitor( widget );
         widgets.put( object, widget );
         getChildren().add( widget );
      }
      return widget;
   }//End Method

   public void removeWidget( Square object ) {
      SquareWidget widget = widgets.remove( object );
      highlighter.remove( object );
      getChildren().remove( widget );
   }//End Method

}//End Class
