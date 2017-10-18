package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;

public class NoneMatcherTest {

   private NoneMatcher systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new NoneMatcher();
   }//End Method

   @Test public void shouldProvideEmptyList() {
      assertThat( systemUnderTest.match( mock( GridModel.class ), 4, 5 ), is( notNullValue()) );
   }//End Method

}//End Class
