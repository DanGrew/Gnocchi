package uk.dangrew.gnocchi.ui.grid;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.square.SquareHighlighter;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class GridWidget extends Pane {

   private final GridMeasurements measurements;
   private final InputDriver inputDriver;
   
   private final GridModel model;
   private final Map< Object, SquareWidget > widgets;
   private final SquareHighlighter highlighter;
   
   public GridWidget( GridModel model, InputDriver inputDriver ) {
      this.model = model;
      this.widgets = new HashMap<>();
      this.highlighter = new SquareHighlighter( model );
      this.inputDriver = inputDriver;
      this.measurements = new GridMeasurements();
      
      this.setMaxSize( measurements.gridPixelWidth(), measurements.gridPixelHeight() );
      this.setMinSize( measurements.gridPixelWidth(), measurements.gridPixelHeight() );
      this.setPrefSize( measurements.gridPixelWidth(), measurements.gridPixelHeight() );
      
      this.setBorder( new Border( new BorderStroke( Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths( 5 ) ) ) );
   }//End Class
   
   public SquareWidget widgetFor( Square object ) {
      SquareWidget widget = widgets.get( object );
      if ( widget == null ) {
         widget = measurements.constructSquareWidget( object, model.width(), model.height() );
         inputDriver.popAction( widget );
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
