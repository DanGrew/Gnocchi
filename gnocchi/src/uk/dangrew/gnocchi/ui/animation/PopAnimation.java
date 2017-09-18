package uk.dangrew.gnocchi.ui.animation;

import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class PopAnimation {
   
   private Grid grid;
   private GridWidget gridWidget;
   
   public void associate( Grid grid, GridWidget gridWidget ) {
      this.grid = grid;
      this.gridWidget = gridWidget;
   }//End Class
   
   public void animate( Object object ) {
      gridWidget.removeWidget( object );
   }//End Method

}//End Class
