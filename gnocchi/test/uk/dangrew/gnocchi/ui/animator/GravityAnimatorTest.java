package uk.dangrew.gnocchi.ui.animator;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.ui.animation.GravityAnimation;

public class GravityAnimatorTest {

   @Mock private Game game;
   
   @Mock private GridSnapshot snapshot;
   @Mock private GravityAnimation animation;
   
   private GravityAnimator systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      systemUnderTest = new GravityAnimator( animation );
      systemUnderTest.hook( game );
   }//End Method

   @Test public void shouldAssociateWithAnimation(){
      verify( animation ).hook( game );
   }//End Method
   
   @Test public void shouldSnapshotFileAndAnimate() {
      when( game.snapshot() ).thenReturn( snapshot );
      
      systemUnderTest.fillGrid();
      
      InOrder order = inOrder( game, animation );
      order.verify( game ).snapshot();
      order.verify( game ).fill();
      order.verify( animation ).animate( snapshot );
   }//End Method

}//End Class
