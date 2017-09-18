package uk.dangrew.gnocchi.ui.animator;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.ui.animation.PopAnimation;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class PopAnimator {

   private final PopAnimation animation;
   
   private Grid grid;
   
   public PopAnimator() {
      this( new PopAnimation() );
   }//End Constructor
   
   PopAnimator( PopAnimation animation ) {
      this.animation = animation;
   }//End Constructor
   
   public void associate( Grid grid, GridWidget gridWidget ) {
      this.grid = grid;
      this.animation.associate( grid, gridWidget );
   }//End Method

   public void pop( Object object ) {
      grid.model().remove( object );
      animation.animate( object );
   }//End Method
   
}//End Class
