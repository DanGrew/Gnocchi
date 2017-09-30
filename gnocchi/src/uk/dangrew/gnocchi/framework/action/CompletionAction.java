package uk.dangrew.gnocchi.framework.action;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.control.Alert;
import uk.dangrew.gnocchi.framework.Action;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.mechanics.GameState;
import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;

public class CompletionAction implements Action {

   private final Game game;
   private final GameLauncherController launchController;
   
   public CompletionAction( Game game, GameLauncherController launchController ) {
      this.game = game;
      this.launchController = launchController;
   }//End Constructor
   
   @Override public void execute() {
      GameState state = game.state();
      switch ( state ) {
         case Failure:
            PlatformImpl.runAndWait( () -> {
               new Alert( Alert.AlertType.ERROR, "Game Over!" ).showAndWait();
            } );
            launchController.reset();
            break;
         case Success:
            PlatformImpl.runAndWait( () -> {
               new Alert( Alert.AlertType.CONFIRMATION, "Success!" ).showAndWait();
            } );
            launchController.reset();
            break;
         case Active:
         default:
            break;
      }
   }//End Method
   
}//End Class
