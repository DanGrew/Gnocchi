package uk.dangrew.gnocchi.ui.frame.content;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.ui.frame.GnocchiFrame;
import uk.dangrew.gnocchi.ui.grid.GridMeasurements;
import uk.dangrew.gnocchi.ui.resources.GridIcons;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

public class GridSelectionContent extends GridPane {

   private final JavaFxStyle styling;
   
   private final GnocchiFrame frame;
   private final double iconDimension;
   
   public GridSelectionContent( GnocchiFrame frame ) {
      this.frame = frame;
      this.iconDimension = new GridMeasurements().calculateSquareArea( 3, 5 );
      this.styling = new JavaFxStyle();
      
      styling.configureConstraintsForRowPercentages( this, 5 );
      styling.configureConstraintsForEvenColumns( this, 5 );
      
      int c = 1;
      int r = 1;
      add( styleButtonSize( GridIcons.gi_3c10 ), c++, r );
      add( styleButtonSize( GridIcons.gi_3c20 ), c++, r );
      add( styleButtonSize( GridIcons.gi_3c40 ), c++, r );
      
      c = 1;
      r++;
      add( styleButtonSize( GridIcons.gi_4c10 ), c++, r );
      add( styleButtonSize( GridIcons.gi_4c20 ), c++, r );
      add( styleButtonSize( GridIcons.gi_4c40 ), c++, r );
      
      c = 1;
      r++;
      add( styleButtonSize( GridIcons.gi_5c10 ), c++, r );
      add( styleButtonSize( GridIcons.gi_5c20 ), c++, r );
      add( styleButtonSize( GridIcons.gi_5c40 ), c++, r );
      
      c = 1;
      r++;
      add( styleButtonSize( GridIcons.gi_6c10 ), c++, r );
      add( styleButtonSize( GridIcons.gi_6c20 ), c++, r );
      add( styleButtonSize( GridIcons.gi_6c40 ), c++, r );
      
      setBackground( new Background( new BackgroundFill( Color.BLACK, null, null ) ) );
   }//End Constructor
   
   public Button styleButtonSize( GridIcons icon ) {
      Button button = new Button();
      styling.removeBackgroundAndColourOnClick( button, Color.GRAY );
      ImageView view = new ImageView( icon.image() );
      view.setFitHeight( iconDimension );
      view.setFitWidth( iconDimension );
      button.setGraphic( view );
      button.setPrefSize( iconDimension, iconDimension );
      button.setMaxWidth( iconDimension );
      button.setMaxHeight( iconDimension );
      button.setAlignment( Pos.CENTER );
      button.setOnAction( e -> frame.showGrid( 
               new GameBuilder()
                  .withNumberOfColours( icon.colourVariation() )
                  .withWidth( icon.width() )
                  .withHeight( icon.height() ) 
      ) );
      return button;
   }//End Method
   
}//End Class
