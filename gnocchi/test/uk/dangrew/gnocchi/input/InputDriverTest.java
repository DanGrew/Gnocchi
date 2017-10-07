package uk.dangrew.gnocchi.input;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.engine.GameEngine;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.square.SquareWidget;
import uk.dangrew.kode.launch.TestApplication;

public class InputDriverTest {

   @Mock private GameEngine engine;
   private InputDriver systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new InputDriver( engine );
   }//End Method

   @Test public void shouldPopSquare() {
      SquareWidget widget = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );
      systemUnderTest.pop( widget );
      verify( engine ).pop( widget.association() );
   }//End Method
   
   @Test public void shouldCombineSquares() {
      SquareWidget widget1 = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );
      SquareWidget widget2 = new SquareWidget( Square.randomSquare(), 0, 0, 20, 20 );
      systemUnderTest.combine( widget1, widget2 );
      verify( engine ).combine( widget1.association(), widget2.association() );
   }//End Method

}//End Class
