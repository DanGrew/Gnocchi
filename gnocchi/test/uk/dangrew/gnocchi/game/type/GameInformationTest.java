package uk.dangrew.gnocchi.game.type;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.grid.model.GridBuilder;

public class GameInformationTest {

   private GameInformation systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GameInformation();
   }//End Method

   @Test public void shouldConfigureGameInfo() {
      GameBuilder builder = new GameBuilder().withGrid( new GridBuilder()
               .withWidth( 34 )
               .withHeight( 23 )
               .withNumberOfColours( 6 )
      );
      systemUnderTest.configure( builder );
      
      assertThat( systemUnderTest.valueFor( GameInformation.PROPERTY_SIZE ), is( "34 x 23" ) );
      assertThat( systemUnderTest.valueFor( GameInformation.PROPERTY_COLOURS ), is( "6" ) );
   }//End Method

   @Test public void shouldConfigureGameProperties() {
      GameProperties properties = mock( GameProperties.class );
      systemUnderTest.configure( new GameBuilder().withProperties( properties ) );
      verify( properties ).configureInformation( systemUnderTest );
   }//End Method
}//End Class
