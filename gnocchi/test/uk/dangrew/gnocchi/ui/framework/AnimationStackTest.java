package uk.dangrew.gnocchi.ui.framework;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.animation.Animation;
import javafx.event.ActionEvent;

public class AnimationStackTest {

   @Mock private Animation animation1;
   @Mock private Animation animation2;
   @Mock private Animation animation3;
   
   private AnimationStack systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new AnimationStack();
   }//End Method

   @Test public void shouldPlayAnimation() {
      systemUnderTest.stack( animation1 );
      verify( animation1 ).play();
   }//End Method
   
   @Test public void shouldPlayAnimationFollowingCurrent() {
      systemUnderTest.stack( animation1 );
      systemUnderTest.stack( animation2 );
      
      verify( animation2, never() ).play();
      animation1.getOnFinished().handle( new ActionEvent() );
      verify( animation2 ).play();
   }//End Method
   
   @Test public void shouldPlayAnimationFollowingLatestNotStarted() {
      systemUnderTest.stack( animation1 );
      systemUnderTest.stack( animation2 );
      systemUnderTest.stack( animation3 );
      
      verify( animation2, never() ).play();
      verify( animation3, never() ).play();
      animation1.getOnFinished().handle( new ActionEvent() );
      verify( animation2 ).play();
      verify( animation3, never() ).play();
      animation2.getOnFinished().handle( new ActionEvent() );
      verify( animation3 ).play();
   }//End Method

}//End Class
