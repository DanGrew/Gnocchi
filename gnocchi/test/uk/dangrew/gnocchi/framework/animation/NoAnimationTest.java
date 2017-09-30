package uk.dangrew.gnocchi.framework.animation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class NoAnimationTest {

   private NoAnimation systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new NoAnimation();
   }//End Method

   @Test public void shouldRunCompletion() {
      Runnable runnable = mock( Runnable.class );
      systemUnderTest.setOnCompletion( runnable );
      
      systemUnderTest.animate();
      verify( runnable ).run();
   }//End Method

}//End Class
