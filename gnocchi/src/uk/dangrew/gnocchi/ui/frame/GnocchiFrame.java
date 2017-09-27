package uk.dangrew.gnocchi.ui.frame;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;
import uk.dangrew.gnocchi.ui.resources.Images;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class GnocchiFrame extends GridPane {

   private final BorderPane logo;
   
   public GnocchiFrame() {
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForRowPercentages( this, 70, 30 );
      styling.configureConstraintsForColumnPercentages( this, 10, 60, 30 );
      
      ImageView view = new ImageView( new Images().logoImage() );
      view.setFitWidth( 200 );
      view.setFitHeight( 200 );
      this.logo = new BorderPane();
      this.logo.setRight( view );
      
      this.setBackground( new Background( new BackgroundFill( Color.BLACK, null, null ) ) );
      
      new GameLauncherController( this );
   }//End Constructor
   
   public void setContent( Node grid, Node properties ) {
      getChildren().clear();
      add( grid, 1, 0 );
      GridPane.setRowSpan( grid, 2 );
      if ( properties != null ) {
         add( properties, 2, 0 );
      }
      add( logo, 2, 1 );
   }//End Method
   
   BorderPane logo(){
      return logo;
   }//End Method

}//End Method
