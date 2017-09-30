package uk.dangrew.gnocchi.framework;

import java.util.LinkedHashSet;
import java.util.Set;

import javafx.animation.Animation;

public class AnimationSynchronizer {

   private final Set< Animation > waitingFor;
   
   private Runnable onCompletion;
   
   public AnimationSynchronizer() {
      this.waitingFor = new LinkedHashSet<>();
   }//End Constructor
   
   public void setOnCompletion( Runnable onCompletion ) {
      this.onCompletion = onCompletion;
   }//End Method
   
   public void waitFor( Animation animation ) {
      this.waitingFor.add( animation );
      animation.setOnFinished( e -> animationCompleted( animation ) );
   }//End Method
   
   private void animationCompleted( Animation animation ) {
      if ( waitingFor.isEmpty() ) {
         return;
      }
      waitingFor.remove( animation );
      if ( waitingFor.isEmpty() ) {
         onCompletion.run();
      }
   }//End Method
   
   public boolean isEmpty(){
      return waitingFor.isEmpty();
   }//End Method

   public void playAll() {
      Set< Animation > a = new LinkedHashSet<>( waitingFor );
      a.forEach( Animation::play );
   }//End Method

}//End Class
