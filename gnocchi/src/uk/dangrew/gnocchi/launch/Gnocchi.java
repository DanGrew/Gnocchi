package uk.dangrew.gnocchi.launch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.dangrew.gnocchi.engine.GameEngine;
import uk.dangrew.gnocchi.engine.SystemWideGameEngine;
import uk.dangrew.jupa.javafx.platform.PlatformLifecycle;

public class Gnocchi extends Application {

   @Override public void start( Stage stage ) throws Exception {
      GameEngine engine = new SystemWideGameEngine().get();
      
      stage.setTitle( "Gnocchi" );
      stage.setOnCloseRequest( event -> PlatformLifecycle.shutdown() );
      stage.setScene( new Scene( engine.display() ) );
      stage.setMaximized( true );
      stage.show();
      
      engine.launch();
   }//End Method
   
   public static void main( String[] args ) {
      launch( args );
   }//End Method

}//End Class
