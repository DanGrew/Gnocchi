package uk.dangrew.gnocchi.ui.square;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.grid.model.GridPosition;

public class SquareWidget extends Rectangle {
   
   private final Object object;
   
   public SquareWidget( Object object, GridPosition position, Color colour ) {
      super( position.w * 60, position.h * 60, 50, 50 );
      this.object = object;
      setArcHeight( 10 );
      setArcWidth( 10 );
      setFill( colour );
   }//End Constructor
   
   public Object association(){
      return object;
   }//End Method 

}//End Class
