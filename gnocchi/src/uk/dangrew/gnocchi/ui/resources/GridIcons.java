package uk.dangrew.gnocchi.ui.resources;

import javafx.scene.image.Image;

public enum GridIcons {

   gi_3c10( "uk/dangrew/gnocchi/ui/resources/3c-10.png", 3, 10, 10 ),
   gi_3c20( "uk/dangrew/gnocchi/ui/resources/3c-20.png", 3, 20, 20 ),
   gi_3c40( "uk/dangrew/gnocchi/ui/resources/3c-40.png", 3, 40, 40 ),
   gi_4c10( "uk/dangrew/gnocchi/ui/resources/4c-10.png", 4, 10, 10 ),
   gi_4c20( "uk/dangrew/gnocchi/ui/resources/4c-20.png", 4, 20, 20 ),
   gi_4c40( "uk/dangrew/gnocchi/ui/resources/4c-40.png", 4, 40, 40 ),
   gi_5c10( "uk/dangrew/gnocchi/ui/resources/5c-10.png", 5, 10, 10 ),
   gi_5c20( "uk/dangrew/gnocchi/ui/resources/5c-20.png", 5, 20, 20 ),
   gi_5c40( "uk/dangrew/gnocchi/ui/resources/5c-40.png", 5, 40, 40 ),
   gi_6c10( "uk/dangrew/gnocchi/ui/resources/6c-10.png", 6, 10, 10 ),
   gi_6c20( "uk/dangrew/gnocchi/ui/resources/6c-20.png", 6, 20, 20 ),
   gi_6c40( "uk/dangrew/gnocchi/ui/resources/6c-40.png", 6, 40, 40 );
   
   private final Image image;
   private final int colourVariation;
   private final int width;
   private final int height;
   
   private GridIcons( String path, int c, int w, int h ) {
      this.image = new Image( getClass().getClassLoader().getResourceAsStream( path ) );
      this.colourVariation = c;
      this.width = w;
      this.height = h;
   }//End Constructor
   
   public Image image(){
      return image;
   }//End Method
   
   public int colourVariation(){
      return colourVariation;
   }//End Method
   
   public int width(){
      return width;
   }//End Method
   
   public int height(){
      return height;
   }//End Method
   
}//End Enum
