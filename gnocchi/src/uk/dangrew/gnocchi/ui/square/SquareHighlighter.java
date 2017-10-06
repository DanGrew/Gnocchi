package uk.dangrew.gnocchi.ui.square;

import javafx.beans.value.ObservableValue;
import uk.dangrew.kode.observable.FunctionSetChangeListenerImpl;

public class SquareHighlighter {

   private final SquareHighlighting highlighting;
   private final HighlightModel highlightModel;
   
   public SquareHighlighter( HighlightModel highlightModel ) {
      this( new SquareHighlighting(), highlightModel );
   }//End Constructor
   
   SquareHighlighter( SquareHighlighting highlighting, HighlightModel highlightModel ) {
      this.highlighting = highlighting;
      this.highlightModel = highlightModel;

      this.highlightModel.selected().addListener( this::selectionChanged );
      this.highlightModel.matchingSelection().addListener( new FunctionSetChangeListenerImpl<>( 
               this::updateWidget, this::updateWidget 
      ) );
      this.highlightModel.highlighted().addListener( this::selectionChanged );
      this.highlightModel.matchingHighlighted().addListener( new FunctionSetChangeListenerImpl<>( 
               this::updateWidget, this::updateWidget 
      ) );
   }//End Constructor
   
   private void selectionChanged( ObservableValue< ? extends SquareWidget > source, SquareWidget previous, SquareWidget current ){
      updateWidget( previous );
      updateWidget( current );
   }//End Method
   
   private void updateWidget( SquareWidget widget ){
      if ( highlightModel.isSelected( widget ) ) {
         highlighting.widgetStateChange( widget, SquareHighlightType.Selected );
      } else if ( highlightModel.isMatchingSelection( widget ) ) {
         highlighting.widgetStateChange( widget, SquareHighlightType.MatchingSelection );
      } else if ( highlightModel.isHighlighted( widget ) ){
         highlighting.widgetStateChange( widget, SquareHighlightType.Highlighted );
      } else if ( highlightModel.isMatchingHighlighted( widget ) ){
         highlighting.widgetStateChange( widget, SquareHighlightType.MatchingHighlighted );
      } else {
         highlighting.widgetStateChange( widget, SquareHighlightType.None );
      }
   }//End Method
   
}//End Class
