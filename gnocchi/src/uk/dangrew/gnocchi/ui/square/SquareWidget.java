package uk.dangrew.gnocchi.ui.square;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;

public class SquareWidget extends StackPane {
   
   private final Rectangle squareBackground;
   private final ImageView imageView;
   
   private final Square object;
   private final RegistrationManager registrations;
   
   public SquareWidget( Square object, double x, double y, double w, double h ) {
      this.setMinSize( w, h );
      this.setMaxSize( w, h );
      this.setTranslateX( x );
      this.setTranslateY( y );
      this.object = object;
      
      this.squareBackground = new Rectangle( x, y, w, h );
      this.squareBackground.setArcHeight( 10 );
      this.squareBackground.setArcWidth( 10 );
      this.squareBackground.setFill( object.colour() );
      
      this.imageView = new ImageView();
      this.imageView.setFitWidth( w );
      this.imageView.setFitHeight( h );
      this.imageView.getTransforms().add( new Rotate( -90, w / 2, h / 2 ) );
      this.getChildren().addAll( squareBackground, imageView );
      
      this.registrations = new RegistrationManager();
      this.registrations.apply( new ReadOnlyChangeListenerRegistrationImpl<>( object.typeProperty(), ( s, o, n ) -> resetWidget() ) );
      this.registrations.apply( new ReadOnlyChangeListenerRegistrationImpl<>( object.colourProperty(), ( s, o, n ) -> resetWidget() ) );
      this.resetWidget();
   }//End Constructor
   
   public void resetWidget(){
      this.squareBackground.setFill( object.colour() );
      this.imageView.setImage( object.type().image() );
      this.imageView.setOpacity( 1 );
   }//End Method
   
   public Rectangle squareBackground(){
      return squareBackground;
   }//End Method
   
   public ImageView imageView(){
      return imageView;
   }//End Method
   
   public Square association(){
      return object;
   }//End Method
   
   public void detachFromSystem(){
      registrations.shutdown();
   }//End Method
}//End Class
