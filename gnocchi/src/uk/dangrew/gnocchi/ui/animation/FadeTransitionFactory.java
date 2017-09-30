package uk.dangrew.gnocchi.ui.animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeTransitionFactory {

   static final double FADE_FROM = 1.0;
   static final double FADE_TO = 0.0;
   static final double DURATION_MILLIS = 500;
   
   public FadeTransition create( Node node ){
      FadeTransition fade = new FadeTransition( Duration.millis( DURATION_MILLIS ) );
      fade.setFromValue( FADE_FROM );
      fade.setToValue( FADE_TO );
      fade.setNode( node );
      return fade;
   }//End Method
   
}//End Class
