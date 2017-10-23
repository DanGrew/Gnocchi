package uk.dangrew.gnocchi.ui.frame.content;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.type.GameLevel;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.ui.styling.ButtonStyling;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class GridSelection extends GridPane {

   private final JavaFxStyle styling;
   private final ButtonStyling buttonStyling;
   private final GameLauncherController controller;
   
   private final ToggleGroup toggleGroup;
   
   public GridSelection( GameLauncherController controller ) {
      this.controller = controller;
      this.styling = new JavaFxStyle();
      
      this.styling.configureConstraintsForEvenRows( this, 7 );
      this.styling.configureConstraintsForEvenColumns( this, 7 );
            
      this.buttonStyling = new ButtonStyling();
      this.setBackground( buttonStyling.backgroundFor( Color.BLACK ) );
      
      this.toggleGroup = new ToggleGroup();
   }//End Constructor
   
   public void showLevels( GameType type ) {
      getChildren().clear();
      toggleGroup.getToggles().clear();
      
      if ( type.levels().isEmpty() ) {
         return;
      }
      
      int r = 1;
      int c = 1;
      for ( GameLevel level : type.levels() ) {
         ToggleButton button = buttonStyling.configureButton( level.levelName(), Color.WHITE );
         toggleGroup.getToggles().add( button );
         button.setOnAction( e -> {
            if ( !button.isSelected() ) {
               controller.showGrid( level.builder() );
            } else {
               controller.showGameLevelInformation( level );
            }
         } );
         
         add( button, c++, r );
         
         if ( c > 5 ) {
            c = 1;
            r++;
         }
      }
   }//End Method
}//End Class
