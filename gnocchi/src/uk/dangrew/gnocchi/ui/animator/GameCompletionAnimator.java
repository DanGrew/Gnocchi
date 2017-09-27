package uk.dangrew.gnocchi.ui.animator;

import javafx.scene.control.Alert;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.mechanics.GameState;

public class GameCompletionAnimator {

   private Game game;
   
   public void hook( Game game ) {
      this.game = game;
   }//End Method
   
   public void handleGameState(){
      GameState state = game.state();
      switch ( state ) {
         case Failure:
            new Alert( Alert.AlertType.ERROR, "Game Over!" ).showAndWait();
            break;
         case Success:
            new Alert( Alert.AlertType.CONFIRMATION, "Success!" ).showAndWait();
            break;
         case Active:
         default:
            break;
      }
   }//End Method
   
}//End Class
