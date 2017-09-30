package uk.dangrew.gnocchi.framework.animation;

import uk.dangrew.gnocchi.framework.AnimationImpl;

public class NoAnimation extends AnimationImpl {

   @Override public void animate() {
      onCompletion().run();
   }//End Method

}//End Class
