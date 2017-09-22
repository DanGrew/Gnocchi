package uk.dangrew.gnocchi.engine;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.animator.GravityAnimator;
import uk.dangrew.gnocchi.ui.animator.PopAnimator;

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
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method
   
   public void launch( Game game ){
      this.gravityAnimator.hook( game );
      this.popAnimator.hook( game );
      
      this.gravityAnimator.fillGrid();
   }//End Method

   public void pop( Square object ) {
      popAnimator.pop( object );
      gravityAnimator.fillGrid();
   }//End Method
   
}//End Class
