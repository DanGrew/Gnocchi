package uk.dangrew.gnocchi.ui.animation;

import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uk.dangrew.gnocchi.grid.model.GridPosition;

public class FallingPathTransitionCreator {
   
   static final int HORIZONTAL_OFFSET = 30;
   static final int WIDGET_SIZE = 60;
   static final int MOVEMENT_SPEED = 50;
   
   public PathTransition create( 
            Rectangle widget, 
            GridPosition from, 
            GridPosition to,
            int gridHeight
   ) {
      Path path = new Path();
      path.getElements().add( new MoveTo( from.w * WIDGET_SIZE + HORIZONTAL_OFFSET, ( gridHeight - from.h ) * WIDGET_SIZE ) );
      path.getElements().add( new LineTo( to.w * WIDGET_SIZE + HORIZONTAL_OFFSET, ( gridHeight - to.h ) * WIDGET_SIZE ) );
      
      int squaresToTravel = ( gridHeight - to.h );
      int verticalDuration =  squaresToTravel * MOVEMENT_SPEED;
      
      int delayPerSquare = ( to.w * MOVEMENT_SPEED );
      int horizontalDelay = delayPerSquare * squaresToTravel / gridHeight;
      
      PathTransition pathTransition = new PathTransition();
      pathTransition.setDelay( Duration.millis( horizontalDelay ) );
      pathTransition.setDuration( Duration.millis( verticalDuration ) );
      pathTransition.setPath( path );
      pathTransition.setNode( widget );
      pathTransition.setOrientation( PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT );
      pathTransition.setCycleCount( 1 );
      
      return pathTransition;
   }//End Method

}//End Class
