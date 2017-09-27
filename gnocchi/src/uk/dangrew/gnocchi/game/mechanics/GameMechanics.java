package uk.dangrew.gnocchi.game.mechanics;

import javafx.scene.Node;

public interface GameMechanics {

   public Feeder feeder();

   public Logic logic();

   public Object properties();
   
   public Node ui();

}//End Class
