package uk.dangrew.gnocchi.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

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

}//End Class
