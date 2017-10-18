package uk.dangrew.gnocchi.game;

import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtProperties;
import uk.dangrew.gnocchi.grid.model.GridBuilder;

public class GameBuilder {

   static final GameType DEFAULT_GAME_TYPE = GameType.Colours;
   static final GameProperties DEFAULT_PROPERTIES = new ColoursGtProperties();
   
   private GridBuilder gridBuilder;
   private GameType gameType;
   private GameProperties properties;
   
   public GameBuilder() {
      this.gridBuilder = new GridBuilder();
      this.gameType = DEFAULT_GAME_TYPE;
      this.properties = DEFAULT_PROPERTIES;
   }//End Constructor
   
   public GridBuilder gridBuilder(){
      return gridBuilder;
   }//End Method
   
   public GameBuilder withGrid( GridBuilder builder ) {
      this.gridBuilder = builder;
      return this;
   }//End Method

   public GameType type() {
      return gameType;
   }//End Method

   public GameBuilder ofType( GameType type ) {
      this.gameType = type;
      return this;
   }//End Method

   public GameProperties properties() {
      return properties;
   }//End Method

   public GameBuilder withProperties( GameProperties properties ) {
      this.properties = properties;
      return this;
   }//End Method

}//End Class
