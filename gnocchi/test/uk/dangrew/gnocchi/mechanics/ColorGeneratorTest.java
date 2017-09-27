package uk.dangrew.gnocchi.mechanics;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ColorGeneratorTest {

   @Mock private Random random;
   private ColorGenerator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new ColorGenerator( random );
   }//End Method

   @Test public void shouldProvideColoursForNextInt() {
      when( random.nextInt( 7 ) ).thenReturn( 0, 1, 2, 3, 4, 5, 6 );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_1 ) );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_2 ) );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_3 ) );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_4 ) );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_5 ) );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_6 ) );
      assertThat( systemUnderTest.next( 7 ), is( ColorGenerator.COLOUR_7 ) );
   }//End Method

}//End Class
