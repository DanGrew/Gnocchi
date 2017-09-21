package uk.dangrew.gnocchi.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.grid.GridArea;

public class GameEngineTest {

   @Mock private GridArea grid;
   private GameEngine systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GameEngine();
   }//End Method

   @Test public void shouldFillGridOnLaunch(){
      systemUnderTest.setGridArea( grid );
      systemUnderTest.launch();
      verify( grid ).fillGrid();
   }//End Method

   @Test public void shouldProvideInputDriver(){
      assertThat( systemUnderTest.inputDriver(), is( notNullValue() ) );
   }//End Method
   
   @Test public void shouldPopObject(){
      systemUnderTest.setGridArea( grid );
      Square object = Square.randomSquare();
      systemUnderTest.pop( object );
      verify( grid ).pop( object );
   }//End Method
}//End Class
