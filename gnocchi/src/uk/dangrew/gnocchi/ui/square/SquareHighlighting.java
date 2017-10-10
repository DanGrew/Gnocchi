package uk.dangrew.gnocchi.ui.square;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.bonus.BonusDetector;
import uk.dangrew.gnocchi.grid.square.SquarePopType;

public class SquareHighlighting {
   
   private final BonusDetector bonusDetector;
   
   public SquareHighlighting() {
      this.bonusDetector = new BonusDetector();
   }//End Constructor

   void styleSelected( SquareWidget widget, List< SquareWidget > matches ) {
      SquarePopType type = bonusDetector.detectBonus( 
               widget.association(), 
               matches.stream()
                  .map( SquareWidget::association )
                  .collect( Collectors.toList() ) 
      );
      if ( type != null ) {
         widget.imageView().setImage( type.properties().image() );
         widget.imageView().setOpacity( 0.5 );
      }
      
      widget.squareBackground().setStroke( Color.ORANGE );
      widget.squareBackground().setStrokeWidth( 8 );
   }//End Method

   void styleMatchingSelection( SquareWidget widget ) {
      widget.resetWidget();
      widget.squareBackground().setStroke( Color.ORANGE );
      widget.squareBackground().setStrokeWidth( 3 );
   }//End Method
   
   void styleBonusForSelection( SquareWidget widget ) {
      widget.resetWidget();
      widget.squareBackground().setStroke( Color.MEDIUMPURPLE );
      widget.squareBackground().setStrokeWidth( 6 );
   }//End Method
   
   void styleHighlighted( SquareWidget widget ) {
      widget.resetWidget();
      widget.squareBackground().setStroke( Color.YELLOW );
      widget.squareBackground().setStrokeWidth( 8 );
   }//End Method

   void styleMatchingHighlighted( SquareWidget widget ) {
      widget.resetWidget();
      widget.squareBackground().setStroke( Color.YELLOW );
      widget.squareBackground().setStrokeWidth( 3 );
   }//End Method
   
   void removeStyling( SquareWidget widget ) {
      widget.resetWidget();
      widget.squareBackground().setStroke( null );
   }//End Method

}//End Class
