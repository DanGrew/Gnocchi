package uk.dangrew.gnocchi.ui.frame.content;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.type.GameLevel;
import uk.dangrew.gnocchi.game.type.GameInformation;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class GameLevelInformation extends GridPane {

   private final JavaFxStyle styling;
   private final Label description;
   
   public GameLevelInformation() {
      this.styling = new JavaFxStyle();
      this.styling.configureConstraintsForEvenColumns( this, 2 );
      this.styling.configureConstraintsForEvenRows( this, 10 );
      this.description = styling.createLabel( "Level Properties:", 20, Color.WHITE );
   }//End Class
   
   public void displayLevelInformation( GameLevel level ) {
      getChildren().clear();
      
      add( description, 0, 0 );
      GridPane.setColumnSpan( description, 2 );
      
      GameInformation information = level.builder().produceInformation();
      for ( int i = 0; i < information.keys().size(); i++ ) {
         add( styling.createLabel( information.keys().get( i ), 15, Color.WHITE ), 0, i + 1 );
         add( styling.createLabel( information.valueFor( information.keys().get( i ) ), 15, Color.WHITE ), 1, i + 1 );
      }
   }//End Method
   
}//End Class
