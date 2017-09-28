package uk.dangrew.gnocchi.game.mechanics;

import java.util.Collection;

import uk.dangrew.gnocchi.grid.square.Square;

public interface Logic {

   public void pop( Square object );
   
   public void popAll( Collection< Square > objects );

   public GameState determineGameState();

}//End Interface
