package uk.dangrew.gnocchi.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.ui.animator.GravityAnimator;
import uk.dangrew.gnocchi.ui.animator.PopAnimator;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class GameEngineTest {

   @Mock private Grid grid;
   @Mock private GridWidget gridWidget;
   @Mock private GravityAnimator gravityAnimator;
   @Mock private PopAnimator popAnimator;
   private GameEngine systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new GameEngine( 
               grid, gridWidget, 
               gravityAnimator, popAnimator
      );
   }//End Method

   @Test public void shouldAssociateWithAnimators() {
      verify( gravityAnimator ).associate( grid, gridWidget );
      verify( popAnimator ).associate( grid, gridWidget );
   }//End Method
   
   @Test public void shouldFillGridOnLaunch(){
      systemUnderTest.launch();
      verify( gravityAnimator ).fillGrid();
   }//End Method

   @Test public void shouldProvideDisplay(){
      assertThat( systemUnderTest.display(), is( gridWidget ) );
   }//End Method
   
   @Test public void shouldPopObject(){
      Object object = new Object();
      systemUnderTest.pop( object );
      
      InOrder order = inOrder( gravityAnimator, popAnimator );
      order.verify( popAnimator ).pop( object );
      order.verify( gravityAnimator ).fillGrid();
   }//End Method
}//End Class
