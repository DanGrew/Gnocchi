package uk.dangrew.gnocchi.ui.grid;

import javafx.scene.layout.BorderPane;
import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.animator.GravityAnimator;
import uk.dangrew.gnocchi.ui.animator.PopAnimator;

public class GridArea extends BorderPane {
   
   private final GridWidget widget;
   
   private final GravityAnimator gravityAnimator;
   private final PopAnimator popAnimator;
   
   public GridArea( Grid grid, InputDriver inputDriver ) {
      this( grid, inputDriver, new GravityAnimator(), new PopAnimator( grid.model() ) );
   }//End Constructor
   
   GridArea( Grid grid, InputDriver inputDriver, GravityAnimator gravityAnimator, PopAnimator popAnimator ) {
      this.widget = new GridWidget( grid, inputDriver );
      this.setCenter( widget );
      this.gravityAnimator = gravityAnimator;
      this.gravityAnimator.associate( grid, widget );
      this.popAnimator = popAnimator;
      this.popAnimator.associate( grid, widget );
   }//End Constructor
   
   public void fillGrid(){
      gravityAnimator.fillGrid();
   }//End Method
   
   public void pop( Square object ) {
      popAnimator.pop( object );
      gravityAnimator.fillGrid();
   }//End Method

}//End Class
