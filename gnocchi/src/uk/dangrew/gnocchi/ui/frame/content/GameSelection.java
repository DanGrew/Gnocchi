package uk.dangrew.gnocchi.ui.frame.content;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.ui.styling.ButtonStyling;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class GameSelection extends GridPane {
   
   public GameSelection( GameLauncherController controller ) {
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForColumnPercentages( this, 30, 40, 30 );
      styling.configureConstraintsForEvenRows( this, 6 );
      
      ButtonStyling buttonStyling = new ButtonStyling();
      int r = 1;
      
      ToggleButton chains = buttonStyling.configureButton( "Chains", Color.LIGHTSKYBLUE );
      customButtonConfiguration( chains, GameType.Chains, controller );
      add( chains, 1, r++ );
      
      ToggleButton colours = buttonStyling.configureButton( "Colours", Color.HOTPINK );
      customButtonConfiguration( colours, GameType.Colours, controller );
      add( colours, 1, r++ );
      
      ToggleButton solitaire = buttonStyling.configureButton( "Solitaire", Color.ORANGE );
      customButtonConfiguration( solitaire, GameType.Solitaire, controller );
      add( solitaire, 1, r++ );
      
      ToggleButton survival = buttonStyling.configureButton( "Survival", Color.GREENYELLOW );
      customButtonConfiguration( survival, GameType.Survival, controller );
      add( survival, 1, r++ );
      
      ToggleButton treasures = buttonStyling.configureButton( "Treasures", Color.MEDIUMPURPLE );
      customButtonConfiguration( treasures, GameType.Treasures, controller );
      add( treasures, 1, r++ );
      
      ToggleGroup group = new ToggleGroup();
      group.getToggles().addAll( chains, colours, solitaire, survival, treasures );
      
      setBackground( buttonStyling.backgroundFor( Color.BLACK ) );
   }//End Constructor
   
   private void customButtonConfiguration( ToggleButton button, GameType type, GameLauncherController controller ) {
      button.setOnAction( e -> controller.showGridsFor( type ) );
      button.setPrefHeight( 50 );
   }//End Method
}//End Class
