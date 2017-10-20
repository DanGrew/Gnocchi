package uk.dangrew.gnocchi.ui.games.colours;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;
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
      assertThat( systemUnderTest.buttonForTarget( SquareRegularType.Primary ).getText(), is( "100" ) );
      assertThat( systemUnderTest.buttonForTarget( SquareRegularType.Secondary ).getText(), is( "100" ) );
      properties.decreaseRemaining( SquareRegularType.Primary, 40 );
      assertThat( systemUnderTest.buttonForTarget( SquareRegularType.Primary ).getText(), is( "60" ) );
      assertThat( systemUnderTest.buttonForTarget( SquareRegularType.Secondary ).getText(), is( "100" ) );
      properties.decreaseRemaining( SquareRegularType.Secondary, 87 );
      assertThat( systemUnderTest.buttonForTarget( SquareRegularType.Primary ).getText(), is( "60" ) );
      assertThat( systemUnderTest.buttonForTarget( SquareRegularType.Secondary ).getText(), is( "13" ) );
   }//End Method

}//End Class
