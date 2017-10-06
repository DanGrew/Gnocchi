package uk.dangrew.gnocchi.grid.square;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTypeTest {

   @Test public void shouldProvideMatcher() {
      for( SquareType type : SquareType.values() ) {
         assertThat( type.matcher(), is( notNullValue() ) );
      }
   }//End Method

}//End Class
