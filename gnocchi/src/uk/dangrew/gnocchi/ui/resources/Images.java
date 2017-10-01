package uk.dangrew.gnocchi.ui.resources;

import javafx.scene.image.Image;

public class Images {
   
   private static final String IMAGE_PATH = "uk/dangrew/gnocchi/ui/resources/";
   
   private static final String LOGO_IMAGE_PATH = IMAGE_PATH + "gnocchi-logo.png";
   private static final String HORIZONTAL_BLAST_IMAGE_PATH = IMAGE_PATH + "horizontal-blast.png";
   private static final String VERTICAL_BLAST_IMAGE_PATH = IMAGE_PATH + "vertical-blast.png";
   private static final String CROSS_BLAST_IMAGE_PATH = IMAGE_PATH + "cross-blast.png";
   private static final String BOMB_BLAST_IMAGE_PATH = IMAGE_PATH + "bomb-blast.png";
   
   private static final Image LOGO_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( LOGO_IMAGE_PATH ) );
   private static final Image HORIZONTAL_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( HORIZONTAL_BLAST_IMAGE_PATH ) );
   private static final Image VERTICAL_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( VERTICAL_BLAST_IMAGE_PATH ) );
   private static final Image CROSS_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( CROSS_BLAST_IMAGE_PATH ) );
   private static final Image BOMB_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( BOMB_BLAST_IMAGE_PATH ) );
   
   public Image logoImage(){
      return LOGO_IMAGE;
   }//End Method
   
   public Image horizontalBlastImage(){
      return HORIZONTAL_BLAST_IMAGE;
   }//End Method
   
   public Image verticalBlastImage(){
      return VERTICAL_BLAST_IMAGE;
   }//End Method
   
   public Image crossBlastImage(){
      return CROSS_BLAST_IMAGE;
   }//End Method
   
   public Image bombBlastImage(){
      return BOMB_BLAST_IMAGE;
   }//End Method
   
}//End Constructor
