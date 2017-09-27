package uk.dangrew.gnocchi.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;

public class GameEngineTest {

   private GameEngine systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GameEngine( mock( GameLauncherController.class ) );
   }//End Method

   @Test public void shouldFillGridOnLaunch(){
      fail();
   }//End Method

   @Test public void shouldProvideInputDriver(){
      assertThat( systemUnderTest.inputDriver(), is( notNullValue() ) );
   }//End Method
   
   @Test public void shouldPopObject(){
      fail();
   }//End Method
}//End Class
