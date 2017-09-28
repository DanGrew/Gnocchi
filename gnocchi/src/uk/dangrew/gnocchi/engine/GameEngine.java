package uk.dangrew.gnocchi.engine;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.animator.GameCompletionAnimator;
import uk.dangrew.gnocchi.ui.animator.GravityAnimator;
import uk.dangrew.gnocchi.ui.animator.ColourMatchPopAnimator;
import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;

public class GameEngine {

   private final GameLauncherController launchController;
   private final InputDriver inputDriver;
   
   private final GravityAnimator gravityAnimator;
   private final ColourMatchPopAnimator colourMatchPopAnimator;
   private final GameCompletionAnimator completionAnimator;
   
   private Game game;
   
   public GameEngine( GameLauncherController launchController ) {
      this( new GravityAnimator(), new ColourMatchPopAnimator(), new GameCompletionAnimator(), launchController );
   }//End Constructor
   
   GameEngine( 
            GravityAnimator gravityAnimator, 
            ColourMatchPopAnimator colourMatchPopAnimator, 
            GameCompletionAnimator completionAnimator,
            GameLauncherController launchController
   ) {
      this.launchController = launchController;
      this.inputDriver = new InputDriver( this );
      this.gravityAnimator = gravityAnimator;
      this.colourMatchPopAnimator = colourMatchPopAnimator;
      this.completionAnimator = completionAnimator;
   }//End Constructor
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method
   
   public void launch( Game game ){
      this.game = game;
      
      this.gravityAnimator.hook( game );
      this.colourMatchPopAnimator.hook( game );
      this.completionAnimator.hook( game );
      
      this.gravityAnimator.fillGrid();
   }//End Method

   public void pop( Square object ) {
      if ( colourMatchPopAnimator.pop( object ) ){
         game.properties().moveUsed();
      }
      completionAnimator.handleGameState();
      
      if ( game.hasCompleted() ) {
         launchController.reset();
      }
   }//End Method
   
}//End Class
