package uk.dangrew.gnocchi.ui.animation;

import java.util.Iterator;

import javafx.animation.PathTransition;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.grid.Grid;
import uk.dangrew.gnocchi.grid.model.GridEntry;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class GravityAnimation {
   
   private Grid grid;
   private GridWidget gridWidget;
   
   public void associate( Grid grid, GridWidget gridWidget ) {
      this.grid = grid;
      this.gridWidget = gridWidget;
   }//End Class
   
   public void animate( GridSnapshot snapshot ){
      for ( int i = 0; i < grid.model().height(); i++ ) {
         Iterator< GridEntry > iterator = grid.model().columnIterator( i );
         GridEntry current = iterator.next();
         while ( !animate( snapshot, current, iterator ) ){
            current = iterator.next();
            if ( current == null ) {
               break;
            }
         }
      }
   }//End Method
   
   private boolean animate( GridSnapshot snapshot, GridEntry current, Iterator< GridEntry > iterator ) {
      GridPosition from = snapshot.of( current.object );
      if ( from == null ) {
         from = new GridPosition( current.position.w, grid.model().height() );
      }
      GridPosition to = current.position;
      if ( from.equals( to ) ) {
         return false;
      }
      
      Rectangle widget = gridWidget.widgetFor( current.object );
      
      PathTransition animation = constructAnimation( widget, from, to );
      if ( iterator.hasNext() ) {
         animation.setOnFinished( e -> animate( snapshot, iterator.next(), iterator ) );
      }
      animation.play();
      return true;
   }//End Method
   
   private PathTransition constructAnimation( Rectangle widget, GridPosition from, GridPosition to ){
      return new FallingPathTransitionCreator().create( 
               widget, from, to, grid.model().height() 
      );
   }//End Method

}//End Class
