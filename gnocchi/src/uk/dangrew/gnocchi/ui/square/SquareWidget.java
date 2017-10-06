package uk.dangrew.gnocchi.ui.square;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareType;
import uk.dangrew.gnocchi.ui.resources.Images;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;

public class SquareWidget extends Rectangle {
   
   private final Square object;
   private final RegistrationManager registrations;
   
   public SquareWidget( Square object, double x, double y, double w, double h ) {
      super( x, y, w, h );
      this.object = object;
      this.setArcHeight( 10 );
      this.setArcWidth( 10 );
      this.setFill( object.colour() );
      this.getTransforms().add( new Rotate( -90, x + w / 2, y + h / 2 ) );
      
      this.registrations = new RegistrationManager();
      this.registrations.apply( new ReadOnlyChangeListenerRegistrationImpl<>( object.typeProperty(), ( s, o, n ) -> updateImage() ) );
      this.updateImage();
   }//End Constructor
   
   private void updateImage(){
      Paint fill = null;
      switch ( object.type() ) {
         case Regular:
            fill = object.colour();
            break;
         default:
            fill = new ImagePattern( object.type().image() );
            break;
//            ImageView view = new ImageView( images.massMatchImage() );
//            ColorAdjust adjust = new ColorAdjust();
//            adjust.setHue( object.colour().getHue() );
//            adjust.setSaturation( 1 );
//            view.setEffect( adjust );
//            fill = new ImagePattern( view.snapshot( new SnapshotParameters(), null ) );
      }
      setFill( fill );
   }//End Method
   
   public Square association(){
      return object;
   }//End Method
   
   public void detachFromSystem(){
      registrations.shutdown();
   }//End Method
}//End Class
