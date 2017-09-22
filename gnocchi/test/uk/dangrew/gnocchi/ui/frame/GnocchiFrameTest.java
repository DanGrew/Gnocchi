package uk.dangrew.gnocchi.ui.frame;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.engine.GameEngine;
import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.ui.frame.content.GridSelectionContent;
import uk.dangrew.gnocchi.ui.grid.GridWidget;
import uk.dangrew.kode.launch.TestApplication;

public class GnocchiFrameTest {

   @Mock private GameEngine engine;
   private GnocchiFrame systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GnocchiFrame( engine );
   }//End Method

   @Test public void shouldProvideGridSelection(){
      assertThat( systemUnderTest.gridSelection(), is( notNullValue() ) );
      assertThat( systemUnderTest.gridSelection(), is( instanceOf( GridSelectionContent.class ) ) );
   }//End Method
   
   @Test public void shouldShowGridSelection() {
      assertThat( systemUnderTest.content(), is( systemUnderTest.gridSelection() ) );
   }//End Method
   
   @Test public void shouldShowGridAndApplyEngine() {
      shouldShowGridSelection();
      systemUnderTest.showGrid( new Grid( 3, 10, 10 ) );
      verify( engine ).launch( Mockito.any(), Mockito.any() );
      assertThat( systemUnderTest.getChildren(), hasSize( 1 ) );
      assertThat( systemUnderTest.getChildren().get( 0 ), is( instanceOf( GridWidget.class ) ) );
   }//End Method
   
   @Test public void shouldShowGridSelectionAfterGrid() {
      shouldShowGridSelection();
      systemUnderTest.showGrid( new Grid( 3, 10, 10 ) );
      assertThat( systemUnderTest.getChildren().get( 0 ), is( instanceOf( GridWidget.class ) ) );
      systemUnderTest.showGridSelection();
      assertThat( systemUnderTest.content(), is( systemUnderTest.gridSelection() ) );
   }//End Method

}//End Class
