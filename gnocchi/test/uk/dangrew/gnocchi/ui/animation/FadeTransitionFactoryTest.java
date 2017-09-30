package uk.dangrew.gnocchi.ui.animation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class FadeTransitionFactoryTest {

   private FadeTransitionFactory systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new FadeTransitionFactory();
   }//End Method

   @Test public void shouldProvideFadeTransition() {
      Node node = new Rectangle();
      
      FadeTransition transition = systemUnderTest.create( node );
      assertThat( transition.getDuration().toMillis(), is( FadeTransitionFactory.DURATION_MILLIS ) );
      assertThat( transition.getFromValue(), is( FadeTransitionFactory.FADE_FROM ) );
      assertThat( transition.getToValue(), is( FadeTransitionFactory.FADE_TO ) );
      assertThat( transition.getNode(), is( node ) );
   }//End Method

}//End Class
