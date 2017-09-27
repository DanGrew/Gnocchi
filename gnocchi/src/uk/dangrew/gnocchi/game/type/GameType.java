package uk.dangrew.gnocchi.game.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import uk.dangrew.gnocchi.game.mechanics.GameMechanics;
import uk.dangrew.gnocchi.game.type.colours.ColoursGames;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtMechanics;
import uk.dangrew.gnocchi.grid.model.GridModel;

public enum GameType {

   Survival( ( m, p ) -> null ),
   Solitaire( ( m, p ) -> null ),
   Treasures( ( m, p ) -> null ),
   Colours( ColoursGtMechanics::new ),
   Chains( ( m, p ) -> null );
   
   private final BiFunction< GridModel, Object, GameMechanics > mechanicsSupplier;
   
   private GameType( 
            BiFunction< GridModel, Object, GameMechanics > mechanicsSupplier
   ) {
      this.mechanicsSupplier = mechanicsSupplier;
   }//End Constructor
   
   public GameMechanics produceMechanics( GridModel model, Object properties ){
      return mechanicsSupplier.apply( model, properties );
   }//End Method
   
   public List< GameLevel > levels(){
      List< GameLevel > levels = new ArrayList<>();
      switch ( this ) {
         case Chains:
         case Solitaire:
         case Survival:
         case Treasures:
            break;
         case Colours:
            levels.addAll( Arrays.asList( ColoursGames.values() ) );
            break;
      }
      return levels;
   }//End Method
   
}//End Enum
