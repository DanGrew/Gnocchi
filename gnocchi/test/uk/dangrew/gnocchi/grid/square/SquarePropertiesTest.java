package uk.dangrew.gnocchi.grid.square;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.image.Image;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;

public class SquarePropertiesTest {

   private SquareProperties systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new SquareProperties();
   }//End Method

   @Test public void shouldProvideMatcher() {
      assertThat( systemUnderTest.matcher(), is( SquareProperties.DEFAULT_MATCHER ) );
      SquareMatcher matcher = mock( SquareMatcher.class );
      assertThat( systemUnderTest.withMatcher( matcher ), is( systemUnderTest ) );
      assertThat( systemUnderTest.matcher(), is( matcher ) );
   }//End Method
   
   @Test public void shouldProvideImage() {
      assertThat( systemUnderTest.image(), is( nullValue() ) );
      Image image = mock( Image.class );
      assertThat( systemUnderTest.withImage( image ), is( systemUnderTest ) );
      assertThat( systemUnderTest.image(), is( image ) );
   }//End Method
   
   @Test public void shouldProvideCombination() {
      assertThat( systemUnderTest.isComboEnabled(), is( false ) );
      assertThat( systemUnderTest.withComboEnabled( true ), is( systemUnderTest ) );
      assertThat( systemUnderTest.isComboEnabled(), is( true ) );
   }//End Method
   
   @Test public void shouldProvidePoppable(){
      assertThat( systemUnderTest.isPoppable(), is( false ) );
      assertThat( systemUnderTest.withPoppable( true ), is( systemUnderTest ) );
      assertThat( systemUnderTest.isPoppable(), is( true ) );
   }//End Method
   
   @Test public void shouldProvideMoveable(){
      assertThat( systemUnderTest.isMoveable(), is( true ) );
      assertThat( systemUnderTest.withMoveable( false ), is( systemUnderTest ) );
      assertThat( systemUnderTest.isMoveable(), is( false ) );
   }//End Method

}//End Class
