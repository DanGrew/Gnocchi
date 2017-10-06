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
      assertThat( systemUnderTest.bombBlastImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.bombBombImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.bombCrossImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.bombHorizontalImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.bombVerticalImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.crossBlastImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.horizontalBlastImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.massBombImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.massCrossImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.massHorizontalImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.massMassImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.massMatchImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.massVerticalImage(), is( notNullValue() ) );
      assertThat( systemUnderTest.verticalBlastImage(), is( notNullValue() ) );
   }//End Method

}//End Class
