package uk.dangrew.gnocchi.game.type.colours;

import javafx.scene.Node;
import uk.dangrew.gnocchi.game.mechanics.GameMechanics;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.ui.games.colours.ColoursGtPropertiesUi;

public class ColoursGtMechanics implements GameMechanics {
   
   private final ColoursGtFeeder feeder;
   private final ColoursGtLogic logic;
   private final ColoursGtProperties properties;
   private final ColoursGtPropertiesUi ui;

   public ColoursGtMechanics( GridModel model, Object externalProperties ) {
      if ( !externalProperties.getClass().equals( ColoursGtProperties.class ) ) {
         throw new IllegalArgumentException( "Invalid properties type." );
      }
      this.feeder = new ColoursGtFeeder( model );
      this.properties = ColoursGtProperties.class.cast( externalProperties );
      this.logic = new ColoursGtLogic( properties, model );
      this.ui = new ColoursGtPropertiesUi( properties );
   }//End Constructor

   @Override public ColoursGtFeeder feeder() {
      return feeder;
   }//End Method

   @Override public ColoursGtLogic logic() {
      return logic;
   }//End Method

   @Override public ColoursGtProperties properties() {
      return properties;
   }//End Method
   
   @Override public Node ui() {
      return ui;
   }//End Method

}//End Class
