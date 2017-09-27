package uk.dangrew.gnocchi.ui.styling;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public class ButtonStyling {

   private static final Color DEFAULT_NO_EFFECT_BACKGROUND = Color.BLACK;
   private static final Color DEFAULT_HIGHLIGHTED_BACKGROUND = Color.YELLOW;
   private static final Color DEFAULT_SELECTED_BACKGROUND = Color.WHITE;
   
   private static final Color DEFAULT_NO_EFFECT_TEXT = Color.WHITE;
   private static final Color DEFAULT_HIGHLIGHTED_TEXT = Color.BLACK;
   private static final Color DEFAULT_SELECTED_TEXT = Color.BLACK;
   
   private final Color noEffectBackground;
   private final Color noEffectText;
   
   private final Color highlightedBackground;
   private final Color highlightedText;
   
   private final Color selectedBackground;
   private final Color selectedText;
   
   public ButtonStyling() {
      this( 
               DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT,
               DEFAULT_HIGHLIGHTED_BACKGROUND, DEFAULT_HIGHLIGHTED_TEXT,
               DEFAULT_SELECTED_BACKGROUND, DEFAULT_SELECTED_TEXT
      );
   }//End Constructor
   
   public ButtonStyling(
         Color noEffectBackground,
         Color noEffectText,
         Color highlightedBackground,
         Color highlightedText,
         Color selectedBackground,
         Color selectedText
   ) {
      this.noEffectBackground = noEffectBackground;
      this.noEffectText = noEffectText;
      this.highlightedBackground = highlightedBackground;
      this.highlightedText = highlightedText;
      this.selectedBackground = selectedBackground;
      this.selectedText = selectedText;
   }//End Class
   
   public ToggleButton configureButton( String text, Color colour ){
      ToggleButton button = new ToggleButton( text );
      
      button.setMaxWidth( Double.MAX_VALUE );
      button.setMaxHeight( Double.MAX_VALUE );
      button.setBorder( borderFor( colour ) );
      
      button.selectedProperty().addListener( ( s, o, n ) -> {
         if ( !n ) resetButtonColour( button );
      } );
      removeBackgroundAndColourOnClick( button );
      return button;
   }//End Method
   
   private void removeBackgroundAndColourOnClick( 
            ToggleButton button 
   ) {
      button.setBackground( backgroundFor( noEffectBackground ) );
      button.setTextFill( noEffectText );
      
      button.setOnMousePressed( event -> {
         button.setBackground( backgroundFor( selectedBackground ) );
         button.setTextFill( selectedText );
      } );
      button.setOnMouseReleased( event -> resetButtonColour( button ) );
      button.setOnMouseEntered( event -> {
         button.setBackground( backgroundFor( highlightedBackground ) );
         button.setTextFill( highlightedText );
      } );
      button.setOnMouseExited( event -> resetButtonColour( button ) );
   }//End Method
   
   private void resetButtonColour( ToggleButton button ) {
      if ( button.isSelected() ) {
         button.setBackground( backgroundFor( selectedBackground ) );
         button.setTextFill( selectedText );
      } else {
         button.setBackground( backgroundFor( noEffectBackground ) );
         button.setTextFill( noEffectText );
      }
   }//End Method
   
   public Background backgroundFor( Color colour ) {
      if ( colour == null ) {
         return null;
      }
      return new Background( 
               new BackgroundFill( colour, null, null )
      );
   }//End Method
   
   public Border borderFor( Color colour ) {
      return new Border( new BorderStroke( colour, BorderStrokeStyle.SOLID, null, new BorderWidths( 5 ) ) );
   }//End Method
   
}//End Class
