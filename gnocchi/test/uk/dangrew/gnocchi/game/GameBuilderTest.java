package uk.dangrew.gnocchi.game;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;
import uk.dangrew.gnocchi.grid.model.GridBuilder;

public class GameBuilderTest {

   private GameBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GameBuilder();
   }//End Method
   
   @Test public void shouldProvideGridBuilder(){
      assertThat( systemUnderTest.gridBuilder(), is( notNullValue() ) );
      assertThat( systemUnderTest.gridBuilder(), is( systemUnderTest.gridBuilder() ) );
      
      GridBuilder builder = new GridBuilder();
      assertThat( systemUnderTest.withGrid( builder ), is( systemUnderTest ) );
      assertThat( systemUnderTest.gridBuilder(), is( builder ) );
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
