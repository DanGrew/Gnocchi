package uk.dangrew.gnocchi.ui.frame;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.engine.GameEngine;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.ui.frame.content.GridSelectionContent;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class GnocchiFrame extends GridPane {

   private final GameEngine engine;
   private final GridSelectionContent gridSelection;
   
   public GnocchiFrame() {
      this( new GameEngine() );
   }//End Constructor
   
   GnocchiFrame( GameEngine engine ) {
      this.engine = engine;
      this.gridSelection = new GridSelectionContent( this );
      
      this.setBackground( new Background( new BackgroundFill( Color.BLACK, null, null ) ) );
      new JavaFxStyle().configureConstraintsForColumnPercentages( this, 20, 60, 20 );
      
      showGridSelection();
   }//End Constructor
   
   public void showGridSelection(){
      getChildren().clear();
      setMainArea( gridSelection );
   }//End Method
   
   public void showGrid( GameBuilder builder ) {
      Game game = new Game( engine.inputDriver(), builder );
      engine.launch( game );
      getChildren().clear();
      setMainArea( game.ui() );
   }//End Method
   
   private void setMainArea( Node node ) {
      add( node, 1, 0 );
   }//End Method

   GridSelectionContent gridSelection() {
      return gridSelection;
   }//End Method

   Node content() {
      return getChildren().get( 0 );
   }//End Method
   
}//End Method
