package uk.dangrew.gnocchi.framework.action;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.game.Game;

public class FillActionTest {

   @Mock private Game game;
   private FillAction systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new FillAction( game );
   }//End Method

   @Test public void shouldFillGrid() {
      systemUnderTest.execute();
      verify( game ).fill();
   }//End Method

}//End Class
