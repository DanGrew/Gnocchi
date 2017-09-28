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
   
   @Test public void shouldUseMoveForEverySuccessfulPop(){
      fail();
//      when( floodFill.flood( model, r1.position().w, r1.position().h ) )
//         .thenReturn( Arrays.asList( r1 ) )
//         .thenReturn( Arrays.asList( r1, r2 ) )
//         .thenReturn( Arrays.asList( r1, r2, r3 ) )
//         .thenReturn( Arrays.asList( r1, r2, g1 ) );
//      
//      systemUnderTest.pop( r1 );
//      assertThat( properties.movesRemaining().get(), is( 5 ) );
//      systemUnderTest.pop( r1 );
//      assertThat( properties.movesRemaining().get(), is( 5 ) );
//      systemUnderTest.pop( r1 );
//      assertThat( properties.movesRemaining().get(), is( 4 ) );
//      systemUnderTest.pop( r1 );
//      assertThat( properties.movesRemaining().get(), is( 3 ) );
   }//End Method
}//End Class
