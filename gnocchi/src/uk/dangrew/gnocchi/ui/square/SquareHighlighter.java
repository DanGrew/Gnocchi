package uk.dangrew.gnocchi.ui.square;

import java.util.ArrayList;

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
      this.highlightModel.bonusForSelection().addListener( new FunctionSetChangeListenerImpl<>( 
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
      if ( widget == null ) {
         return;
      }
      if ( highlightModel.isSelected( widget ) ) {
         highlighting.styleSelected( widget, new ArrayList<>( highlightModel.matchingSelection() ) );
      } else if ( highlightModel.isBonusForSelection( widget ) ) {
         highlighting.styleBonusForSelection( widget );
      } else if ( highlightModel.isMatchingSelection( widget ) ) {
         highlighting.styleMatchingSelection( widget );
      } else if ( highlightModel.isHighlighted( widget ) ){
         highlighting.styleHighlighted( widget );
      } else if ( highlightModel.isMatchingHighlighted( widget ) ){
         highlighting.styleMatchingHighlighted( widget );
      } else {
         highlighting.removeStyling( widget );
      }
   }//End Method
   
}//End Class
