package uk.dangrew.gnocchi.ui.games.colours;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;
import uk.dangrew.kode.launch.TestApplication;

public class ColoursGtPropertiesUiTest {

   private ColoursGtProperties properties;
   private ColoursGtPropertiesUi systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      properties = new ColoursGtProperties();
      systemUnderTest = new ColoursGtPropertiesUi( properties );
   }//End Method

   @Test public void shouldDisplayAndUpdateMovesRemaining() {
      assertThat( systemUnderTest.movesRemaining().getText(), is( "25" ) );
      properties.moveUsed();
      assertThat( systemUnderTest.movesRemaining().getText(), is( "24" ) );
   }//End Method
   
   @Test public void shouldDisplayTargetColoursAndRemaining() {
      assertThat( systemUnderTest.buttonForTarget( Color.RED ).getText(), is( "100" ) );
      assertThat( systemUnderTest.buttonForTarget( Color.LIGHTSKYBLUE ).getText(), is( "100" ) );
      properties.decreaseRemaining( Color.RED, 40 );
      assertThat( systemUnderTest.buttonForTarget( Color.RED ).getText(), is( "60" ) );
      assertThat( systemUnderTest.buttonForTarget( Color.LIGHTSKYBLUE ).getText(), is( "100" ) );
      properties.decreaseRemaining( Color.LIGHTSKYBLUE, 87 );
      assertThat( systemUnderTest.buttonForTarget( Color.RED ).getText(), is( "60" ) );
      assertThat( systemUnderTest.buttonForTarget( Color.LIGHTSKYBLUE ).getText(), is( "13" ) );
   }//End Method

}//End Class
