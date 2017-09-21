package uk.dangrew.gnocchi.mechanics;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;

public class ColorGeneratorTest {

   @Mock private Random random;
   private ColorGenerator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new ColorGenerator( random );
   }//End Method

   @Test public void shouldProvideColoursForNextInt() {
      when( random.nextInt( 6 ) ).thenReturn( 0, 1, 2, 3, 4, 5 );
      assertThat( systemUnderTest.next( 6 ), is( Color.LIGHTSKYBLUE ) );
      assertThat( systemUnderTest.next( 6 ), is( Color.RED ) );
      assertThat( systemUnderTest.next( 6 ), is( Color.GREEN ) );
      assertThat( systemUnderTest.next( 6 ), is( Color.ORANGE ) );
      assertThat( systemUnderTest.next( 6 ), is( Color.MEDIUMPURPLE ) );
      assertThat( systemUnderTest.next( 6 ), is( Color.PEACHPUFF ) );
   }//End Method

}//End Class
