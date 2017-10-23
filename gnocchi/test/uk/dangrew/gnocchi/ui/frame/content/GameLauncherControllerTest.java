package uk.dangrew.gnocchi.ui.frame.content;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javafx.scene.Node;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.ui.frame.GnocchiFrame;
import uk.dangrew.gnocchi.ui.grid.GridWidget;
import uk.dangrew.kode.launch.TestApplication;

public class GameLauncherControllerTest {

   @Mock private GnocchiFrame frame;
   private GameLauncherController systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GameLauncherController( frame );
   }//End Method

   @Test public void shouldInitiallyConfigureMainMenu() {
      verify( frame ).setContent( systemUnderTest.mainMenu(), null );
   }//End Method
   
   @Test public void shouldShowGridAndProperties() {
      systemUnderTest.showGrid( new GameBuilder() );
      verify( frame, times( 2 ) ).setContent( Mockito.any( GridWidget.class ), Mockito.any( Node.class ) );
   }//End Method
   
   @Test public void shouldResetMainMenu(){
      systemUnderTest.reset();
      verify( frame, times( 2 ) ).setContent( systemUnderTest.mainMenu(), null );
   }//End Method

   @Test public void shouldShowInformation(){
      fail();
   }//End Method
}//End Class
