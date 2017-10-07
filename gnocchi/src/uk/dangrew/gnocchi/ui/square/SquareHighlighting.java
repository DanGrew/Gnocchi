package uk.dangrew.gnocchi.ui.square;

import javafx.scene.paint.Color;

public class SquareHighlighting {

   public void widgetStateChange( SquareWidget widget, SquareHighlightType type ){
      if ( widget == null ) {
         return;
      }
      switch ( type ) {
         case Selected:
            styleSelected( widget );
            break;
         case MatchingSelection:
            styleMatchingSelection( widget );
            break;
         case BonusForSelection:
            styleBonusForSelection( widget );
            break;
         case Highlighted:
            styleHighlighted( widget );
            break;
         case MatchingHighlighted:
            styleMatchingHighlighted( widget );
            break;
         case None:
            removeStyling( widget );
            break;
         default:
            break;
      }
   }//End Method
   
   private void styleSelected( SquareWidget widget ) {
      widget.setStroke( Color.ORANGE );
      widget.setStrokeWidth( 8 );
   }//End Method

   private void styleMatchingSelection( SquareWidget widget ) {
      widget.setStroke( Color.ORANGE );
      widget.setStrokeWidth( 3 );
   }//End Method
   
   private void styleBonusForSelection( SquareWidget widget ) {
      widget.setStroke( Color.MEDIUMPURPLE );
      widget.setStrokeWidth( 6 );
   }//End Method
   
   private void styleHighlighted( SquareWidget widget ) {
      widget.setStroke( Color.YELLOW );
      widget.setStrokeWidth( 8 );
   }//End Method

   private void styleMatchingHighlighted( SquareWidget widget ) {
      widget.setStroke( Color.YELLOW );
      widget.setStrokeWidth( 3 );
   }//End Method
   
   private void removeStyling( SquareWidget widget ) {
      widget.setStroke( null );
   }//End Method

}//End Class
