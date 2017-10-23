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

   private final BorderPane gridWrapper;
   private final BorderPane logo;
   
   public GnocchiFrame() {
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForRowPercentages( this, 70, 30 );
      styling.configureConstraintsForColumnPercentages( this, 80, 20 );
      
      ImageView view = new ImageView( new Images().logoImage() );
      view.setFitWidth( 200 );
      view.setFitHeight( 200 );
      this.logo = new BorderPane();
      this.logo.setRight( view );
      
      this.gridWrapper = new BorderPane();
      this.setBackground( new Background( new BackgroundFill( Color.BLACK, null, null ) ) );
      
      new GameLauncherController( this );
   }//End Constructor
   
   public void setContent( Node grid, Node properties ) {
      getChildren().clear();
      add( gridWrapper, 0, 0 );
      
      this.gridWrapper.setCenter( grid );
      GridPane.setRowSpan( gridWrapper, 2 );
      if ( properties != null ) {
         add( properties, 1, 0 );
      }
      add( logo, 1, 1 );
   }//End Method
   
   BorderPane logo(){
      return logo;
   }//End Method
   
   BorderPane gridWrapper(){
      return gridWrapper;
   }//End Method

}//End Method
