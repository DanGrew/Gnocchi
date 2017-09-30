package uk.dangrew.gnocchi.framework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GameStack {

   private final BlockingQueue< GameAction > queue;
   private final Runnable animationCompletionAction;
   
   public GameStack() {
      this.queue = new ArrayBlockingQueue<>( 100 );
      this.animationCompletionAction = this::playNextAnimation;
   }//End Constructor
   
   public void stack( GameAction action ) {
      boolean initiallyEmpty = queue.isEmpty(); 
      chainAnimation( action );
      if ( initiallyEmpty ) {
         action.execute();
         return;
      }
   }//End Method
   
   private GameAction nextAnimation(){
      if ( queue.isEmpty() ) {
         return null;
      }
      return queue.peek();
   }//End Method
   
   private void chainAnimation( GameAction action ) {
      queue.add( action );
      action.setOnCompletion( animationCompletionAction );
   }//End Method
   
   private void playNextAnimation() {
      try {
         queue.take();
      } catch ( InterruptedException e ) {}
      
      GameAction nextInQueue = nextAnimation();
      if ( nextInQueue == null ) {
         return;
      }
      
      nextInQueue.execute();
   }//End Method

}//End Class
