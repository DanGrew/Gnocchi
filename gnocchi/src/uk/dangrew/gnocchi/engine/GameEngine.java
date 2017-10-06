package uk.dangrew.gnocchi.engine;

import java.util.List;

import javafx.scene.paint.Color;
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
import uk.dangrew.gnocchi.game.bonus.BonusDetector;
import uk.dangrew.gnocchi.game.matching.MatchChainer;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareType;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;

public class GameEngine {

   private final GameLauncherController launchController;
   private final InputDriver inputDriver;
   
   private final MatchChainer matchChainer;
   private final BonusDetector bonusDetector;
   private final GameStack stack;
   
   private Game game;
   
   public GameEngine( GameLauncherController launchController ) {
      this.launchController = launchController;
      this.inputDriver = new InputDriver( this );
      this.stack = new GameStack();
      this.bonusDetector = new BonusDetector();
      this.matchChainer = new MatchChainer();
   }//End Constructor
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method
   
   public void launch( Game game ){
      this.game = game;
      this.stack.stack( new GameAction( new FillAction( game ), new GravityAnimation( game ) ) );
   }//End Method

   public void pop( Square object ) {
      List< Square > matches = matchChainer.match( game.model(), object.position().w, object.position().h );
      if ( matches.isEmpty() ) {
         return;
      }
      SquareType bonus = bonusDetector.detectBonus( object, matches );
      if ( bonus != null ) {
         object.setType( bonus );
         if ( bonus != SquareType.MassMatcher && 
                  bonus != SquareType.MassBomb &&
                  bonus != SquareType.MassCross &&
                  bonus != SquareType.MassHorizontal &&
                  bonus != SquareType.MassMass &&
                  bonus != SquareType.MassVertical
         ) {
            object.setColour( Color.BLACK );
         }
         matches.remove( object );
      }
      
      stack.stack( new GameAction( new SquareRemovalAction( game, matches ), new BlastAnimation( game, matches ) ) );
      stack.stack( new GameAction( new FillAction( game ), new GravityAnimation( game ) ) );
      stack.stack( new GameAction( new CompletionAction( game, launchController ), new NoAnimation() ) );
   }//End Method
   
}//End Class
