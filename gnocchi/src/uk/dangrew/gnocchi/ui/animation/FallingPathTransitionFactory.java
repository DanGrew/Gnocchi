package uk.dangrew.gnocchi.ui.animation;

import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.ui.grid.GridMeasurements;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class FallingPathTransitionFactory {
   
   private final GridMeasurements measurements;
   
   public FallingPathTransitionFactory() {
      this.measurements = new GridMeasurements();
   }//End Constructor
   
   public PathTransition create( 
            SquareWidget widget, 
            GridPosition from, 
            GridPosition to,
            int gridWidth,
            int gridHeight
   ) {
      double widgetSize = measurements.calculateSquareArea( gridWidth, gridHeight );
      double horizontalOffset = measurements.calculateWidthOffset( gridWidth, gridHeight );
      double verticalOffset = measurements.calculateHeightOffset( gridWidth, gridHeight );
      double gravity = measurements.calculateGravity( gridHeight );
      
      Path path = new Path();
      path.getElements().add( new MoveTo( from.w * widgetSize + horizontalOffset, ( gridHeight - from.h ) * widgetSize + verticalOffset ) );
      path.getElements().add( new LineTo( to.w * widgetSize + horizontalOffset, ( gridHeight - to.h ) * widgetSize + verticalOffset ) );
      
      int squaresToTravel = from.h - to.h;
      double verticalDuration =  squaresToTravel * gravity * 2;
      double delayPerSquare = ( to.w * gravity );
      double horizontalDelay = delayPerSquare * squaresToTravel / gridHeight;
      
      PathTransition pathTransition = new PathTransition();
      pathTransition.setDelay( Duration.millis( horizontalDelay ) );
      pathTransition.setDuration( Duration.millis( gravity * 4 ) );//verticalDuration ) );
      pathTransition.setPath( path );
      pathTransition.setNode( widget );
      pathTransition.setOrientation( PathTransition.OrientationType.NONE );
      pathTransition.setCycleCount( 1 );
      
      return pathTransition;
   }//End Method

}//End Class
