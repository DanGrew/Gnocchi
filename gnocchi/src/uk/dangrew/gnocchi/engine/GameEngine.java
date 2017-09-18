package uk.dangrew.gnocchi.engine;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.ui.animator.GravityAnimator;
import uk.dangrew.gnocchi.ui.animator.PopAnimator;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class GameEngine {

   private final GridWidget display;
   private final GravityAnimator gravityAnimator;
   private final PopAnimator popAnimator;
   
   public GameEngine() {
      this( 
               new Grid( 10, 10 ), 
               new GridWidget(), 
               new GravityAnimator(),
               new PopAnimator()
      );
   }//End Constructor
   
   GameEngine( 
            Grid grid, 
            GridWidget gridWidget, 
            GravityAnimator gravityAnimator,
            PopAnimator popAnimator
   ) {
      this.display = gridWidget;
      this.gravityAnimator = gravityAnimator;
      this.gravityAnimator.associate( grid, gridWidget );
      this.popAnimator = popAnimator;
      this.popAnimator.associate( grid, gridWidget );
   }//End Constructor
   
   public GridWidget display() {
      return display;
   }//End Method

   public void launch() {
      gravityAnimator.fillGrid();
   }//End Method
   
   public void pop( Object object ) {
      popAnimator.pop( object );
      gravityAnimator.fillGrid();
   }//End Method

}//End Class
