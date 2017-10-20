package uk.dangrew.gnocchi.mechanics;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class SquareTypeGeneratorTest {

   @Mock private Random random;
   private SquareTypeGenerator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new SquareTypeGenerator( random );
   }//End Method

   @Test public void shouldProvideColoursForNextInt() {
      when( random.nextInt( 7 ) ).thenReturn( 0, 1, 2, 3, 4, 5, 6 );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Primary ) );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Secondary ) );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Tertiary ) );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Quaternary ) );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Quinary ) );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Senary ) );
      assertThat( systemUnderTest.next( 7 ), is( SquareRegularType.Septenary ) );
   }//End Method

}//End Class
