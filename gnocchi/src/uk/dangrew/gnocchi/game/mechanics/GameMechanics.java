package uk.dangrew.gnocchi.game.mechanics;

import javafx.scene.Node;
import uk.dangrew.gnocchi.game.type.GameProperties;

public interface GameMechanics {

   public Feeder feeder();

   public Logic logic();

   public GameProperties properties();
   
   public Node ui();

}//End Class
