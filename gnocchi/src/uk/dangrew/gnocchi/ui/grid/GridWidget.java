package uk.dangrew.gnocchi.ui.grid;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.mechanics.ColorGenerator;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class GridWidget extends Group {
   
   private final ColorGenerator colours;
   private final Map< Object, SquareWidget > widgets;
   
   public GridWidget() {
      this.widgets = new HashMap<>();
      this.colours = new ColorGenerator();
   }//End Class
   
   public Rectangle widgetFor( Object object ) {
      SquareWidget widget = widgets.get( object );
      if ( widget == null ) {
         widget = new SquareWidget( object, new GridPosition( -1, -1 ), colours.next() );
         new InputDriver().popAction( widget );
         widgets.put( object, widget );
         getChildren().add( widget );
      }
      return widget;
   }//End Method

   public void removeWidget( Object object ) {
      SquareWidget widget = widgets.remove( object );
      getChildren().remove( widget );
   }//End Method

}//End Class
