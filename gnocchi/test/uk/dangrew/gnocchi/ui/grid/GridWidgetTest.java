package uk.dangrew.gnocchi.ui.grid;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.javafx.application.PlatformImpl;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.ui.animation.GravityAnimation;
import uk.dangrew.kode.launch.TestApplication;

public class GridWidgetTest {
   
   private Grid grid;
   private GridWidget systemUnderTest;

   @Ignore
   @Test public void manual() throws InterruptedException {
      grid = new Grid( 10, 10 );
      TestApplication.launch( () -> systemUnderTest = new GridWidget( grid ) );
      
      PlatformImpl.runLater( () -> {
         GridSnapshot snapshot = grid.snapshot();
         grid.fill();
         GravityAnimation animation = new GravityAnimation();
         animation.associate( grid, systemUnderTest );
         animation.animate( snapshot );
      } );
      
      Thread.sleep( 100000000 );
   }//End Method

}//End Class
