package uk.dangrew.gnocchi.framework;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.animation.Animation;
import javafx.event.ActionEvent;

public class AnimationSynchronizerTest {

   @Mock private Animation animation1;
   @Mock private Animation animation2;
   
   @Mock private Runnable onCompletion;
   private AnimationSynchronizer systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      systemUnderTest = new AnimationSynchronizer();
      systemUnderTest.setOnCompletion( onCompletion );
   }//End Method

   @Test public void shouldCompleteWhenAnimationFinishes() {
      systemUnderTest.waitFor( animation1 );
      verify( onCompletion, never() ).run();
      
      animation1.getOnFinished().handle( new ActionEvent() );
      verify( onCompletion ).run();
   }//End Method
   
   @Test public void shouldCompleteWhenMultipleAnimationsFinish() {
      systemUnderTest.waitFor( animation1 );
      systemUnderTest.waitFor( animation2 );
      verify( onCompletion, never() ).run();
      
      animation2.getOnFinished().handle( new ActionEvent() );
      verify( onCompletion, never() ).run();
      animation1.getOnFinished().handle( new ActionEvent() );
      verify( onCompletion ).run();
   }//End Method
   
   @Test public void shouldPlayInOrder(){
      systemUnderTest.waitFor( animation2 );
      systemUnderTest.waitFor( animation1 );
      
      systemUnderTest.playAll();
      InOrder order = inOrder( animation1, animation2 );
      order.verify( animation2 ).play();
      order.verify( animation1 ).play();
   }//End Method
   
   @Test public void shouldProvideEmpty(){
      assertThat( systemUnderTest.isEmpty(), is( true ) );
      systemUnderTest.waitFor( animation1 );
      assertThat( systemUnderTest.isEmpty(), is( false ) );
      animation1.getOnFinished().handle( new ActionEvent() );
      assertThat( systemUnderTest.isEmpty(), is( true ) );
   }//End Method

}//End Class
