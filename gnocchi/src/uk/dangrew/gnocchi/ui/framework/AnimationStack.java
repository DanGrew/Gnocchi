package uk.dangrew.gnocchi.ui.framework;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AnimationStack {

   private final List< Animation > queue;
   private final EventHandler< ActionEvent > animationCompletionAction;
   
   public AnimationStack() {
      this.queue = new ArrayList<>();
      this.animationCompletionAction = e -> playNextAnimation();
   }//End Constructor
   
   public void stack( Animation animation ) {
      boolean initiallyEmpty = queue.isEmpty(); 
      chainAnimation( animation );
      if ( initiallyEmpty ) {
         animation.play();
         return;
      }
   }//End Method
   
   private Animation nextAnimation(){
      if ( queue.isEmpty() ) {
         return null;
      }
      return queue.get( 0 );
   }//End Method
   
   private void chainAnimation( Animation animation ) {
      queue.add( animation );
      animation.setOnFinished( animationCompletionAction );
   }//End Method
   
   private void playNextAnimation() {
      queue.remove( 0 );
      Animation nextInQueue = nextAnimation();
      if ( nextInQueue == null ) {
         return;
      }
      
      nextInQueue.play();
   }//End Method

}//End Class
