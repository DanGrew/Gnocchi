package uk.dangrew.gnocchi.grid.square;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class SquareTypesTest {

   @Test public void shoudProvideTypes() {
      assertThat( SquareTypes.values(), is( SquareTypes.values() ) );
      assertThat( Arrays.asList( SquareTypes.values() ).containsAll( Arrays.asList( SquarePopType.values() ) ), is( true ) );
      assertThat( Arrays.asList( SquareTypes.values() ).containsAll( Arrays.asList( SquareObstacleType.values() ) ), is( true ) );
   }//End Method

}//End Class
