package uk.dangrew.gnocchi.ui.resources;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ImagesTest {

   private Images systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new Images();
   }//End Method

   @Test public void shouldProvideImages() {
      assertThat( systemUnderTest.logoImage(), is( notNullValue() ) );
   }//End Method

}//End Class
