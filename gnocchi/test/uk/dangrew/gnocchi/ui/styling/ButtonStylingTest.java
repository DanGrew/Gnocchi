package uk.dangrew.gnocchi.ui.styling;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import uk.dangrew.kode.launch.TestApplication;

public class ButtonStylingTest {
   
   private static final Color DEFAULT_NO_EFFECT_BACKGROUND = Color.ALICEBLUE;
   private static final Color DEFAULT_HIGHLIGHTED_BACKGROUND = Color.ANTIQUEWHITE;
   private static final Color DEFAULT_SELECTED_BACKGROUND = Color.AQUA;
   
   private static final Color DEFAULT_NO_EFFECT_TEXT = Color.AQUAMARINE;
   private static final Color DEFAULT_HIGHLIGHTED_TEXT = Color.AZURE;
   private static final Color DEFAULT_SELECTED_TEXT = Color.BEIGE;
   
   private static final String TEXT = "this text";
   private static final Color COLOUR = Color.LAWNGREEN;
   
   private ToggleButton button; 
   private ButtonStyling systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      systemUnderTest = new ButtonStyling( 
            DEFAULT_NO_EFFECT_BACKGROUND,
            DEFAULT_NO_EFFECT_TEXT,
            DEFAULT_HIGHLIGHTED_BACKGROUND,
            DEFAULT_HIGHLIGHTED_TEXT,
            DEFAULT_SELECTED_BACKGROUND,
            DEFAULT_SELECTED_TEXT
      );
      button = systemUnderTest.configureButton( TEXT, COLOUR );
   }//End Method

   @Test public void shouldProvideBackgroundFor() {
      Background background = systemUnderTest.backgroundFor( Color.RED );
      assertThat( background.getFills().get( 0 ).getFill(), is( Color.RED ) );
   }//End Method
   
   @Test public void shouldNotProvideBackgroundForNullColour() {
      assertThat( systemUnderTest.backgroundFor( null ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldProvideBorderFor() {
      Border border = systemUnderTest.borderFor( Color.RED );
      assertBorderIs( Color.RED, border );
   }//End Method
   
   @Test public void shouldConfigureBasicProperties(){
      assertThat( button.getText(), is( TEXT ) );
      assertThat( button.getMaxWidth(), is( Double.MAX_VALUE ) );
      assertThat( button.getMaxHeight(), is( Double.MAX_VALUE ) );
   }//End Method
   
   @Test public void shouldHandleMouseMovement(){
      assertButtonColoursAre( DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT );
      button.getOnMouseEntered().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_HIGHLIGHTED_BACKGROUND, DEFAULT_HIGHLIGHTED_TEXT );
      button.getOnMouseExited().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT );
   }//End Method
   
   @Test public void shouldHandleMousePressing(){
      assertButtonColoursAre( DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT );
      button.getOnMousePressed().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_SELECTED_BACKGROUND, DEFAULT_SELECTED_TEXT );
      button.getOnMouseReleased().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT );
   }//End Method
   
   @Test public void shouldResetWhenMouseMovedAndSelected(){
      button.setSelected( true );
      
      assertButtonColoursAre( DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT );
      button.getOnMouseEntered().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_HIGHLIGHTED_BACKGROUND, DEFAULT_HIGHLIGHTED_TEXT );
      button.getOnMouseExited().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_SELECTED_BACKGROUND, DEFAULT_SELECTED_TEXT );
   }//End Method
   
   @Test public void shouldResetWhenMouseMovedAndPressed(){
      button.setSelected( true );
      
      assertButtonColoursAre( DEFAULT_NO_EFFECT_BACKGROUND, DEFAULT_NO_EFFECT_TEXT );
      button.getOnMousePressed().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_SELECTED_BACKGROUND, DEFAULT_SELECTED_TEXT );
      button.getOnMouseReleased().handle( newMouseEvent() );
      assertButtonColoursAre( DEFAULT_SELECTED_BACKGROUND, DEFAULT_SELECTED_TEXT );
   }//End Method
   
   private void assertButtonColoursAre( Color backgroundColour, Color textColour ) {
      assertThat( button.getBackground().getFills().get( 0 ).getFill(), is( backgroundColour ) );
      assertThat( button.getTextFill(), is( textColour ) );
   }//End Method
   
   private void assertBorderIs( Color colour, Border border ) {
      assertThat( border.getStrokes().get( 0 ).getBottomStroke(), is( Color.RED ) );
      assertThat( border.getStrokes().get( 0 ).getTopStroke(), is( Color.RED ) );
      assertThat( border.getStrokes().get( 0 ).getRightStroke(), is( Color.RED ) );
      assertThat( border.getStrokes().get( 0 ).getLeftStroke(), is( Color.RED ) );
      
      assertThat( border.getStrokes().get( 0 ).getBottomStyle(), is( BorderStrokeStyle.SOLID ) );
      assertThat( border.getStrokes().get( 0 ).getTopStyle(), is( BorderStrokeStyle.SOLID ) );
      assertThat( border.getStrokes().get( 0 ).getRightStyle(), is( BorderStrokeStyle.SOLID ) );
      assertThat( border.getStrokes().get( 0 ).getLeftStyle(), is( BorderStrokeStyle.SOLID ) );
   }//End Method
   
   private MouseEvent newMouseEvent(){
      return new MouseEvent( 
               null, null, null, 0, 0, 0, 0, null, 0, true, true, true, 
               true, true, true, true, true, true, true, null );
   }//End Method

}//End Class
