package uk.dangrew.gnocchi.ui.framework;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.framework.Action;
import uk.dangrew.gnocchi.framework.GameAction;
import uk.dangrew.gnocchi.framework.GnocchiAnimation;

public class GameActionTest {

   @Mock private Action action;
   @Mock private GnocchiAnimation gnocchiAnimation;
   @Mock private Runnable onCompletion;
   private GameAction systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GameAction( action, gnocchiAnimation );
   }//End Method

   @Test public void shouldCompleteActionWithAnimation() {
      systemUnderTest.execute();
      InOrder order = inOrder( action, gnocchiAnimation );
      order.verify( action ).execute();
      order.verify( gnocchiAnimation ).animate();
   }//End Method
   
   @Test public void shouldTriggerOnCompletion(){
      systemUnderTest.setOnCompletion( onCompletion );
      verify( gnocchiAnimation ).setOnCompletion( onCompletion );
   }//End Method

}//End Class
