package uk.dangrew.gnocchi.ui.square;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.grid.square.Square;

public class SquareWidget extends Rectangle {
   
   private final Square object;
   
   public SquareWidget( Square object, double x, double y, double w, double h ) {
      super( x, y, w, h );
      this.object = object;
      this.setArcHeight( 10 );
      this.setArcWidth( 10 );
      this.setFill( object.colour() );
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
