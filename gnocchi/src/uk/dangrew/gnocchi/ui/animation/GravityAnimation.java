package uk.dangrew.gnocchi.ui.animation;

import java.util.Iterator;

import javafx.animation.PathTransition;
import javafx.scene.shape.Rectangle;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.grid.model.GridEntry;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;

public class GravityAnimation {
   
   private final FallingPathTransitionCreator transitionCreator;
   
   private Game game;
   
   public GravityAnimation() {
      this.transitionCreator = new FallingPathTransitionCreator();
   }//End Constructor
   
   public void hook( Game game ) {
      this.game = game;
   }//End Class
   
   public void animate( GridSnapshot snapshot ){
      for ( int i = 0; i < game.model().height(); i++ ) {
         Iterator< GridEntry > iterator = game.model().columnIterator( i );
         GridEntry current = iterator.next();
         while ( !animate( snapshot, current, iterator ) ){
            current = iterator.next();
            if ( current.object == null ) {
               break;
            }
         }
      }
   }//End Method
   
   private boolean animate( GridSnapshot snapshot, GridEntry current, Iterator< GridEntry > iterator ) {
      GridPosition from = snapshot.of( current.object );
      if ( from == null ) {
         from = new GridPosition( current.position.w, game.model().height() );
      }
      GridPosition to = current.position;
      if ( from.equals( to ) ) {
         return false;
      }
      
      Rectangle widget = game.ui().widgetFor( current.object );
      
      PathTransition animation = constructAnimation( widget, from, to );
      if ( iterator.hasNext() ) {
         animation.setOnFinished( e -> animate( snapshot, iterator.next(), iterator ) );
      }
      animation.play();
      return true;
   }//End Method
   
   private PathTransition constructAnimation( Rectangle widget, GridPosition from, GridPosition to ){
      return transitionCreator.create( 
               widget, from, to, game.model().width(), game.model().height() 
      );
   }//End Method

}//End Class
