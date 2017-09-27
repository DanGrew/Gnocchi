package uk.dangrew.gnocchi.game;

import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;

public class GameBuilder {

   static final int DEFAULT_COLOUR_VARIATION = 3;
   static final int DEFAULT_WIDTH = 10;
   static final int DEFAULT_HEIGHT = 10;
   static final GameType DEFAULT_GAME_TYPE = GameType.Colours;
   static final Object DEFAULT_PROPERTIES = new ColoursGtProperties();
   
   private int colourVariation;
   private int width;
   private int height;
   private GameType gameType;
   private Object properties;
   
   public GameBuilder() {
      this.colourVariation = DEFAULT_COLOUR_VARIATION;
      this.width = DEFAULT_WIDTH;
      this.height = DEFAULT_HEIGHT;
      this.gameType = DEFAULT_GAME_TYPE;
      this.properties = DEFAULT_PROPERTIES;
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

   public GameType type() {
      return gameType;
   }//End Method

   public GameBuilder ofType( GameType type ) {
      this.gameType = type;
      return this;
   }//End Method

   public Object properties() {
      return properties;
   }//End Method

   public GameBuilder withProperties( Object properties ) {
      this.properties = properties;
      return this;
   }//End Method

}//End Class
