package uk.dangrew.gnocchi.ui.animator;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.ui.animation.GravityAnimation;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class GravityAnimatorTest {

   @Mock private Grid grid;
   @Mock private GridWidget gridWidget;
   
   @Mock private GridSnapshot snapshot;
   @Mock private GravityAnimation animation;
   
   private GravityAnimator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      systemUnderTest = new GravityAnimator( animation );
      systemUnderTest.associate( grid, gridWidget );
   }//End Method

   @Test public void shouldAssociateWithAnimation(){
      verify( animation ).associate( grid, gridWidget );
   }//End Method
   
   @Test public void shouldSnapshotFileAndAnimate() {
      when( grid.snapshot() ).thenReturn( snapshot );
      
      systemUnderTest.fillGrid();
      
      InOrder order = inOrder( grid, animation );
      order.verify( grid ).snapshot();
      order.verify( grid ).fill();
      order.verify( animation ).animate( snapshot );
   }//End Method

}//End Class
