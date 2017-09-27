package uk.dangrew.gnocchi.game.type.colours;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.kode.launch.TestApplication;

public class ColoursGtMechanicsTest {

   private ColoursGtProperties properties;
   private ColoursGtMechanics systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      properties = new ColoursGtProperties();
      systemUnderTest = new ColoursGtMechanics( new GridModel( 3, 10, 10 ), properties );
   }//End Method

   @Test( expected = IllegalArgumentException.class ) public void shouldNotAcceptInvalidProperties(){
      new ColoursGtMechanics( new GridModel( 0, 0, 0 ), new Object() );
   }//End Method
   
   @Test public void shouldProvideFeeder() {
      assertThat( systemUnderTest.feeder(), is( instanceOf( ColoursGtFeeder.class ) ) );
   }//End Method
   
   @Test public void shouldProvideLogic() {
      assertThat( systemUnderTest.logic(), is( instanceOf( ColoursGtLogic.class ) ) );
   }//End Method
   
   @Test public void shouldProvideProperties() {
      assertThat( systemUnderTest.properties(), is( instanceOf( ColoursGtProperties.class ) ) );
   }//End Method

}//End Class
