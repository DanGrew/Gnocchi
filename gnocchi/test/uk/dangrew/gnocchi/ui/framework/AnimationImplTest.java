package uk.dangrew.gnocchi.ui.framework;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.framework.AnimationImpl;

public class AnimationImplTest {

   private AnimationImpl systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new AnimationImpl() {
         @Override public void animate() {}
      };
   }//End Method

   @Test public void shouldProvideCompletion() {
      assertThat( systemUnderTest.onCompletion(), is( nullValue() ) );
      Runnable runnable = () -> {};
      systemUnderTest.setOnCompletion( runnable );
      assertThat( systemUnderTest.onCompletion(), is( runnable ) );
   }//End Method

}//End Class
