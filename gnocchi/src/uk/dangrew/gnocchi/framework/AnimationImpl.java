package uk.dangrew.gnocchi.framework;

public abstract class AnimationImpl implements GnocchiAnimation {

   private Runnable onCompletion;
   
   @Override public abstract void animate();

   @Override public Runnable onCompletion() {
      return onCompletion;
   }//End Method

   @Override public void setOnCompletion( Runnable onCompletion ) {
      this.onCompletion = onCompletion;
   }//End Method

}//End Class
