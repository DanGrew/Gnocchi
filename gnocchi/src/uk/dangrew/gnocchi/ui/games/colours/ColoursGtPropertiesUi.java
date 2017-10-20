package uk.dangrew.gnocchi.ui.games.colours;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;
import uk.dangrew.gnocchi.grid.square.SquareType;
import uk.dangrew.gnocchi.ui.styling.ButtonStyling;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.observable.FunctionMapAnyKeyChangeListenerImpl;

public class ColoursGtPropertiesUi extends GridPane {

   private final ToggleButton movesRemaining;
   private final Map< SquareType, ToggleButton > targets;
   
   public ColoursGtPropertiesUi( ColoursGtProperties properties ) {
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForEvenRows( this, 10 );
      styling.configureConstraintsForEvenColumns( this, 1 );
      
      ButtonStyling buttonStyling = new ButtonStyling(
               Color.BLACK, Color.WHITE,
               Color.BLACK, Color.WHITE,
               Color.BLACK, Color.WHITE
      );
      setPadding( new Insets( 20 ) );
      
      Label label = styling.createBoldLabel( "Colours", 60 );
      label.setTextFill( Color.WHITE );
      add( label, 0, 2 );
      
      this.movesRemaining = buttonStyling.configureButton( convertToString( properties.movesRemaining().get() ), Color.WHITE );
      properties.movesRemaining().addListener( ( s, o, n ) -> movesRemaining.setText( convertToString( n ) ) );
      add( movesRemaining, 0, 4 );
      
      GridPane targetsPane = new GridPane();
      styling.configureConstraintsForEvenRows( targetsPane, 1 );
      styling.configureConstraintsForEvenColumns( targetsPane, properties.targetTypes().size() );
      
      this.targets = new HashMap<>();
      
      for ( int i = 0; i < properties.targetTypes().size(); i++ ) {
         SquareType target = properties.targetTypes().get( i );
         ToggleButton button = buttonStyling.configureButton( convertToString( properties.remainingColours().get( target ) ), target.properties().colour() );
         targets.put( target, button );
         targetsPane.add( button, i, 0 );
      }
      
      properties.remainingColours().addListener( new FunctionMapAnyKeyChangeListenerImpl<>( 
               colour -> {
                  ToggleButton button = targets.get( colour );
                  int remaining = properties.remainingFor( colour );
                  button.setText( convertToString( remaining ) );
               }
      ) );
      
      add( targetsPane, 0, 5 );
   }//End Class
   
   private String convertToString( Integer value ) {
      return value.toString();
   }//End Method
   
   ToggleButton buttonForTarget( SquareType type ) {
      return targets.get( type );
   }//End Method
   
   ToggleButton movesRemaining(){
      return movesRemaining;
   }//End Method
   
}//End Class
