package uk.dangrew.gnocchi.ui.frame.content;

import javafx.scene.layout.GridPane;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class MainMenu extends GridPane {

   public MainMenu( GameSelection gameSelection, GridSelection gridSelection, GameLevelInformation gameLevelInformation ) {
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForRowPercentages( this, 40, 60 );
      styling.configureConstraintsForColumnPercentages( this, 70, 30 );
      
      add( gameSelection, 0, 0 );
      add( gridSelection, 0, 1 );
      add( gameLevelInformation, 1, 1 );
   }//End Constructor
   
}//End Class
