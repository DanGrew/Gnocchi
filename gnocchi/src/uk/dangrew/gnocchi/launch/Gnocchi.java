package uk.dangrew.gnocchi.launch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.dangrew.gnocchi.ui.frame.GnocchiFrame;
import uk.dangrew.jupa.javafx.platform.PlatformLifecycle;

public class Gnocchi extends Application {

   @Override public void start( Stage stage ) throws Exception {
      stage.setTitle( "Gnocchi" );
      stage.setOnCloseRequest( event -> PlatformLifecycle.shutdown() );
      stage.setScene( new Scene( new GnocchiFrame() ) );
      stage.setMaximized( true );
      stage.show();
   }//End Method
   
   public static void main( String[] args ) {
      launch( args );
   }//End Method

}//End Class
