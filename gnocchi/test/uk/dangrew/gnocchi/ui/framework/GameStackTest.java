package uk.dangrew.gnocchi.ui.framework;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.framework.Action;
import uk.dangrew.gnocchi.framework.GameAction;
import uk.dangrew.gnocchi.framework.GameStack;
import uk.dangrew.gnocchi.framework.GnocchiAnimation;

public class GameStackTest {

   private GameAction action1;
   private GameAction action2;
   private GameAction action3;
   
   private GnocchiAnimation animation1;
   private GnocchiAnimation animation2;
   private GnocchiAnimation animation3;
   
   private GameStack systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      action1 = spy( new GameAction( mock( Action.class ), animation1 = new TestAnimation() ) );
      action2 = spy( new GameAction( mock( Action.class ), animation2 = new TestAnimation() ) );
      action3 = spy( new GameAction( mock( Action.class ), animation3 = new TestAnimation() ) );
      systemUnderTest = new GameStack();
   }//End Method

   @Test public void shouldPlayAnimation() {
      systemUnderTest.stack( action1 );
      verify( action1 ).execute();
   }//End Method
   
   @Test public void shouldPlayAnimationFollowingCurrent() {
      systemUnderTest.stack( action1 );
      systemUnderTest.stack( action2 );
      
      verify( action2, never() ).execute();
      animation1.onCompletion().run();
      verify( action2 ).execute();
   }//End Method
   
   @Test public void shouldPlayAnimationFollowingLatestNotStarted() {
      systemUnderTest.stack( action1 );
      systemUnderTest.stack( action2 );
      systemUnderTest.stack( action3 );
      
      verify( action2, never() ).execute();
      verify( action3, never() ).execute();
      animation1.onCompletion().run();
      verify( action2 ).execute();
      verify( action3, never() ).execute();
      animation2.onCompletion().run();
      verify( action3 ).execute();
   }//End Method

}//End Class
