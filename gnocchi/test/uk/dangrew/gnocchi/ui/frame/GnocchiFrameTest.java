package uk.dangrew.gnocchi.ui.frame;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import uk.dangrew.kode.launch.TestApplication;

public class GnocchiFrameTest {

   private GnocchiFrame systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GnocchiFrame();
   }//End Method

   @Test public void shouldShowContentAndProperties() {
      Node grid = new Rectangle();
      Node properties = new Rectangle();
      
      systemUnderTest.setContent( grid, properties );
      assertThat( systemUnderTest.getChildren().contains( systemUnderTest.gridWrapper() ), is( true ) );
      assertThat( systemUnderTest.gridWrapper().getCenter(), is( grid ) );
      assertThat( systemUnderTest.getChildren().contains( properties ), is( true ) );
      
      assertThat( GridPane.getRowSpan( systemUnderTest.gridWrapper() ), is( 2 ) );
      
      shouldProvideLogo();
   }//End Method
   
   @Test public void shouldShowContentWithoutProperties() {
      Node grid = new Rectangle();
      
      systemUnderTest.setContent( grid, null );
      assertThat( systemUnderTest.getChildren().contains( systemUnderTest.gridWrapper() ), is( true ) );
      assertThat( systemUnderTest.gridWrapper().getCenter(), is( grid ) );
      
      assertThat( GridPane.getRowSpan( systemUnderTest.gridWrapper() ), is( 2 ) );
      
      shouldProvideLogo();
   }//End Method
   
   @Test public void shouldProvideLogo(){
      assertThat( systemUnderTest.logo(), is( notNullValue() ) );
      assertThat( systemUnderTest.getChildren().contains( systemUnderTest.logo() ), is( true ) );
   }//End Method
   
}//End Class
