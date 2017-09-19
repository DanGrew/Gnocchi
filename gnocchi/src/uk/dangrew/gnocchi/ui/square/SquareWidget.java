package uk.dangrew.gnocchi.ui.square;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;

public class SquareWidget extends Rectangle {
   
   private final Square object;
   
   public SquareWidget( Square object, GridPosition position ) {
      super( position.w * 60, position.h * 60, 50, 50 );
      this.object = object;
      setArcHeight( 10 );
      setArcWidth( 10 );
      setFill( object.colour() );
   }//End Constructor
   
   public Square association(){
      return object;
   }//End Method
   
   public void highlight(){
      setFill( Color.YELLOW );
   }//End Method
   
   public void unhighlight(){
      setFill( object.colour() );
   }//End Method
}//End Class
