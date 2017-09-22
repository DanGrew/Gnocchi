package uk.dangrew.gnocchi.game;

public class GameBuilder {

   static final int DEFAULT_COLOUR_VARIATION = 3;
   static final int DEFAULT_WIDTH = 10;
   static final int DEFAULT_HEIGHT = 10;
   
   private int colourVariation;
   private int width;
   private int height;
   
   public GameBuilder() {
      this.colourVariation = DEFAULT_COLOUR_VARIATION;
      this.width = DEFAULT_WIDTH;
      this.height = DEFAULT_HEIGHT;
   }//End Constructor

   public int colourVariation() {
      return colourVariation;
   }//End Method

   public GameBuilder withNumberOfColours( int n ) {
      this.colourVariation = n;
      return this;
   }//End Method
   
   public int width() {
      return width;
   }//End Method

   public GameBuilder withWidth( int n ) {
      this.width = n;
      return this;
   }//End Method
   
   public int height() {
      return height;
   }//End Method

   public GameBuilder withHeight( int n ) {
      this.height = n;
      return this;
   }//End Method

}//End Class
