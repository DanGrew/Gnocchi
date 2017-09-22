package uk.dangrew.gnocchi.ui.animator;

import java.util.List;

import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.animation.PopAnimation;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class PopAnimator {

   private final FloodFill floodFill;
   private final PopAnimation animation;
   
   private Grid grid;
   
   public PopAnimator() {
      this( new PopAnimation(), new FloodFill() );
   }//End Constructor
   
   PopAnimator( PopAnimation animation, FloodFill floodFill ) {
      this.animation = animation;
      this.floodFill = floodFill;
   }//End Constructor
   
   public void hook( Grid grid, GridWidget gridWidget ) {
      this.grid = grid;
      this.animation.associate( grid, gridWidget );
   }//End Method

   public void pop( Square object ) {
      List< Square > flood = floodFill.flood( grid.model(), object.position().w, object.position().h );
      flood.forEach( o -> grid.model().remove( o ) );
      flood.forEach( o -> animation.animate( o ) );
   }//End Method
   
}//End Class
