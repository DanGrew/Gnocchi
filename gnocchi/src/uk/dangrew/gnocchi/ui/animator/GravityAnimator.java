package uk.dangrew.gnocchi.ui.animator;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.ui.animation.GravityAnimation;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class GravityAnimator {

   private final GravityAnimation animation;
   
   private Grid grid;
   
   public GravityAnimator() {
      this( new GravityAnimation() );
   }//End Constructor
   
   GravityAnimator( GravityAnimation animation ) {
      this.animation = animation;
   }//End Constructor
   
   public void associate( Grid grid, GridWidget gridWidget ) {
      this.grid = grid;
      this.animation.associate( grid, gridWidget );
   }//End Method

   public void fillGrid() {
      GridSnapshot snapshot = grid.snapshot();
      grid.fill();
      animation.animate( snapshot );
   }//End Method

}//End Class
