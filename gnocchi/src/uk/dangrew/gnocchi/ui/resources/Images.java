package uk.dangrew.gnocchi.ui.resources;

import javafx.scene.image.Image;

public class Images {
   
   private static final String LOGO_IMAGE_PATH = "uk/dangrew/gnocchi/ui/resources/gnocchi-logo.png";
   private static final Image LOGO_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( LOGO_IMAGE_PATH ) );
   
   public Image logoImage(){
      return LOGO_IMAGE;
   }//End Method
   
}//End Constructor
