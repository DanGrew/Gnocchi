package uk.dangrew.gnocchi.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;

public class GameBuilderTest {

   private GameBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GameBuilder();
   }//End Method

   @Test public void shouldConfigureColours() {
      assertThat( systemUnderTest.colourVariation(), is( GameBuilder.DEFAULT_COLOUR_VARIATION ) );
      assertThat( systemUnderTest.withNumberOfColours( 6 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.colourVariation(), is( 6 ) );
   }//End Method
   
   @Test public void shouldConfigureWidth() {
      assertThat( systemUnderTest.width(), is( GameBuilder.DEFAULT_WIDTH ) );
      assertThat( systemUnderTest.withWidth( 18 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.width(), is( 18 ) );
   }//End Method   
   
   @Test public void shouldConfigureHeight() {
      assertThat( systemUnderTest.height(), is( GameBuilder.DEFAULT_HEIGHT ) );
      assertThat( systemUnderTest.withHeight( 18 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.height(), is( 18 ) );
   }//End Method 

   @Test public void shouldConfigureGameType() {
      assertThat( systemUnderTest.type(), is( GameBuilder.DEFAULT_GAME_TYPE ) );
      assertThat( systemUnderTest.ofType( GameType.Chains ), is( systemUnderTest ) );
      assertThat( systemUnderTest.type(), is( GameType.Chains ) );
   }//End Method 
   
   @Test public void shouldConfigureGameProperties() {
      assertThat( systemUnderTest.properties(), is( GameBuilder.DEFAULT_PROPERTIES ) );
      GameProperties properties = new ColoursGtProperties();
      assertThat( systemUnderTest.withProperties( properties ), is( systemUnderTest ) );
      assertThat( systemUnderTest.properties(), is( properties ) );
   }//End Method 
}//End Class
