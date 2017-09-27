package uk.dangrew.gnocchi.game.type;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.type.colours.ColoursGames;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.kode.launch.TestApplication;

public class GameTypeTest {

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
   }//End Method

   @Test public void shouldProvideMechanics() {
      assertThat( GameType.Colours.produceMechanics( new GridModel( 3, 10, 10 ), new ColoursGtProperties() ), is( notNullValue() ) );
   }//End Method
   
   @Test public void shouldProvideGameLevels(){
      assertThat( GameType.Colours.levels(), is( Arrays.asList( ColoursGames.values() ) ) );
   }//End Method

}//End Class
