package uk.dangrew.gnocchi.game.type.colours;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.type.GameInformation;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class ColoursGtPropertyInformationProviderTest {

   private GameInformation information;
   private ColoursGtProperties properties;
   private ColoursGtPropertyInformationProvider systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      properties = new ColoursGtProperties();
      properties.increaseRemaining( SquareRegularType.Primary, 20 );
      properties.increaseRemaining( SquareBonusType.BombBomb, 3 );
      properties.increaseMoves( 6 );
      
      information = new GameInformation();
      systemUnderTest = new ColoursGtPropertyInformationProvider();
      systemUnderTest.configure( properties, information );
   }//End Method

   @Test public void shouldProvideMapOfValues() {
      assertThat( information.valueFor( "Target for Primary" ), is( "120" ) );
      assertThat( information.valueFor( "Target for BombBomb" ), is( "3" ) );
   }//End Method
   
   @Test public void shouldProvidePropertiesInOrder(){
      List< String > properties = new ArrayList<>( information.keys() );
      assertThat( properties.get( 0 ), is( "Target for Primary" ) );
      assertThat( properties.get( 1 ), is( "Target for Secondary" ) );
      assertThat( properties.get( 2 ), is( "Target for BombBomb" ) );
   }//End Method

}//End Class
