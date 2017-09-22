package uk.dangrew.gnocchi.engine;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.animator.GravityAnimator;
import uk.dangrew.gnocchi.ui.animator.PopAnimator;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class GameEngine {

   private final InputDriver inputDriver;
   
   private final GravityAnimator gravityAnimator;
   private final PopAnimator popAnimator;
   
   public GameEngine() {
      this( new GravityAnimator(), new PopAnimator() );
   }//End Constructor
   
   GameEngine( GravityAnimator gravityAnimator, PopAnimator popAnimator ) {
      this.inputDriver = new InputDriver( this );
      this.gravityAnimator = gravityAnimator;
      this.popAnimator = popAnimator;
   }//End Constructor
   
   public void launch( Grid grid, GridWidget widget ){
      this.gravityAnimator.hook( grid, widget );
      this.popAnimator.hook( grid, widget );
      
      this.gravityAnimator.fillGrid();
   }//End Method
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method

   public void pop( Square object ) {
      popAnimator.pop( object );
      gravityAnimator.fillGrid();
   }//End Method
   
}//End Class
