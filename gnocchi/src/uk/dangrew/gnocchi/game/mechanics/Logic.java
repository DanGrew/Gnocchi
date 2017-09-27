package uk.dangrew.gnocchi.game.mechanics;

import java.util.List;

import uk.dangrew.gnocchi.grid.square.Square;

public interface Logic {

   public List< Square > pop( Square object );

   public GameState determineGameState();

}//End Interface
