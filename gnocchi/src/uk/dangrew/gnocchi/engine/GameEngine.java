package uk.dangrew.gnocchi.engine;

import java.util.List;

import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.framework.GameAction;
import uk.dangrew.gnocchi.framework.GameStack;
import uk.dangrew.gnocchi.framework.action.CompletionAction;
import uk.dangrew.gnocchi.framework.action.FillAction;
import uk.dangrew.gnocchi.framework.action.SquareRemovalAction;
import uk.dangrew.gnocchi.framework.animation.BlastAnimation;
import uk.dangrew.gnocchi.framework.animation.GravityAnimation;
import uk.dangrew.gnocchi.framework.animation.NoAnimation;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;

public class GameEngine {

   private final GameLauncherController launchController;
   private final InputDriver inputDriver;
   
   private final GameStack stack;
   
   private Game game;
   
   public GameEngine( GameLauncherController launchController ) {
      this.launchController = launchController;
      this.inputDriver = new InputDriver( this );
      this.stack = new GameStack();
   }//End Constructor
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method
   
   public void launch( Game game ){
      this.game = game;
      this.stack.stack( new GameAction( new FillAction( game ), new GravityAnimation( game ) ) );
   }//End Method

   public void pop( Square object ) {
      List< Square > flood = new FloodFill().flood( game.model(), object.position().w, object.position().h );
      if ( flood.size() < 3 ) {
         return;
      }
      stack.stack( new GameAction( new SquareRemovalAction( game, flood ), new BlastAnimation( game, flood ) ) );
      stack.stack( new GameAction( new FillAction( game ), new GravityAnimation( game ) ) );
      stack.stack( new GameAction( new CompletionAction( game, launchController ), new NoAnimation() ) );
   }//End Method
   
}//End Class
