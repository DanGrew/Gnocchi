package uk.dangrew.gnocchi.ui.resources;

import javafx.scene.image.Image;

public class Images {
   
   private static final String IMAGE_PATH = "uk/dangrew/gnocchi/ui/resources/";
   
   private static final String LOGO_IMAGE_PATH = IMAGE_PATH + "gnocchi-logo.png";
   
   private static final String HORIZONTAL_BLAST_IMAGE_PATH = IMAGE_PATH + "horizontal-blast.png";
   private static final String VERTICAL_BLAST_IMAGE_PATH = IMAGE_PATH + "vertical-blast.png";
   private static final String CROSS_BLAST_IMAGE_PATH = IMAGE_PATH + "cross-blast.png";
   private static final String BOMB_BLAST_IMAGE_PATH = IMAGE_PATH + "bomb-blast.png";
   private static final String MASS_MATCH_IMAGE_PATH = IMAGE_PATH + "mass-match.png";
   
   private static final String BOMB_HORIZONTAL_IMAGE_PATH = IMAGE_PATH + "bomb-horizontal.png";
   private static final String BOMB_VERTICAL_IMAGE_PATH = IMAGE_PATH + "bomb-vertical.png";
   private static final String BOMB_CROSS_IMAGE_PATH = IMAGE_PATH + "bomb-cross.png";
   private static final String BOMB_BOMB_IMAGE_PATH = IMAGE_PATH + "bomb-bomb.png"; 
   
   private static final String MASS_HORIZONTAL_IMAGE_PATH = IMAGE_PATH + "mass-horizontal.png";
   private static final String MASS_VERTICAL_IMAGE_PATH = IMAGE_PATH + "mass-vertical.png";
   private static final String MASS_CROSS_IMAGE_PATH = IMAGE_PATH + "mass-cross.png";
   private static final String MASS_BOMB_IMAGE_PATH = IMAGE_PATH + "mass-bomb.png";
   private static final String MASS_MASS_IMAGE_PATH = IMAGE_PATH + "mass-mass.png";
   
   private static final Image LOGO_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( LOGO_IMAGE_PATH ) );
   
   private static final Image HORIZONTAL_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( HORIZONTAL_BLAST_IMAGE_PATH ) );
   private static final Image VERTICAL_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( VERTICAL_BLAST_IMAGE_PATH ) );
   private static final Image CROSS_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( CROSS_BLAST_IMAGE_PATH ) );
   private static final Image BOMB_BLAST_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( BOMB_BLAST_IMAGE_PATH ) );
   private static final Image MASS_MATCH_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( MASS_MATCH_IMAGE_PATH ) );
   
   private static final Image BOMB_HORIZONTAL_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( BOMB_HORIZONTAL_IMAGE_PATH ) );
   private static final Image BOMB_VERTICAL_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( BOMB_VERTICAL_IMAGE_PATH ) );
   private static final Image BOMB_CROSS_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( BOMB_CROSS_IMAGE_PATH ) );
   private static final Image BOMB_BOMB_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( BOMB_BOMB_IMAGE_PATH ) );
   
   private static final Image MASS_HORIZONTAL_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( MASS_HORIZONTAL_IMAGE_PATH ) );
   private static final Image MASS_VERTICAL_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( MASS_VERTICAL_IMAGE_PATH ) );
   private static final Image MASS_CROSS_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( MASS_CROSS_IMAGE_PATH ) );
   private static final Image MASS_BOMB_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( MASS_BOMB_IMAGE_PATH ) );
   private static final Image MASS_MASS_IMAGE = new Image( Images.class.getClassLoader().getResourceAsStream( MASS_MASS_IMAGE_PATH ) );
   
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

   public Image massMatchImage() {
      return MASS_MATCH_IMAGE;
   }//End Method
   
   public Image bombHorizontalImage(){
      return BOMB_HORIZONTAL_IMAGE;
   }//End Method
   
   public Image bombVerticalImage(){
      return BOMB_VERTICAL_IMAGE;
   }//End Method
   
   public Image bombCrossImage(){
      return BOMB_CROSS_IMAGE;
   }//End Method
   
   public Image bombBombImage(){
      return BOMB_BOMB_IMAGE;
   }//End Method
   
   public Image massHorizontalImage() {
      return MASS_HORIZONTAL_IMAGE;
   }//End Method
   
   public Image massVerticalImage() {
      return MASS_VERTICAL_IMAGE;
   }//End Method
   
   public Image massCrossImage() {
      return MASS_CROSS_IMAGE;
   }//End Method
   
   public Image massBombImage() {
      return MASS_BOMB_IMAGE;
   }//End Method
   
   public Image massMassImage() {
      return MASS_MASS_IMAGE;
   }//End Method
   
}//End Constructor
