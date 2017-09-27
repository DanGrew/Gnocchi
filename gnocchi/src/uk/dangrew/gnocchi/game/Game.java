package uk.dangrew.gnocchi.game;

import java.util.List;

import javafx.scene.Node;
import uk.dangrew.gnocchi.game.mechanics.GameMechanics;
import uk.dangrew.gnocchi.game.mechanics.GameState;
import uk.dangrew.gnocchi.grid.controls.Filler;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class Game {

   private final GridModel model;
   private final GridWidget gridWidget;
   private final GameMechanics mechanics;
   private final Filler filler;
   
   public Game( InputDriver inputDriver, GameBuilder builder ) {
      this.model = new GridModel( builder.colourVariation(), builder.width(), builder.height() );
      this.gridWidget = new GridWidget( model, inputDriver );
      this.mechanics = builder.type().produceMechanics( model, builder.properties() );
      this.filler = new Filler( model, mechanics.feeder() );
   }//End Constructor
   
   public GridModel model(){
      return model;
   }//End Method
   
   public GridWidget gridUi(){
      return gridWidget;
   }//End Method
   
   public Node propertiesUi(){
      return mechanics.ui();
   }//End Method
   
   public GridSnapshot snapshot(){
      return model.snapshot();
   }//End Method
   
   public void fill(){
      filler.fill();
   }//End Method 
   
   public List< Square > pop( Square object ) {
      return mechanics.logic().pop( object );
   }//End Method
   
   public GameState state(){
      return mechanics.logic().determineGameState();
   }//End Method

   public boolean hasCompleted() {
      return mechanics.logic().determineGameState() != GameState.Active;
   }//End Method
}//End Class
