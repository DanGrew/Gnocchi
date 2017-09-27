package uk.dangrew.gnocchi.game.type.colours;

import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.game.type.GameLevel;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.mechanics.ColorGenerator;

public enum ColoursGames implements GameLevel {

   Level1( 
      "1",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withWidth( 10 )
         .withHeight( 10 )
         .withNumberOfColours( 3 )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 25 )
                  .withTarget( ColorGenerator.COLOUR_1, 45 )
                  .withTarget( ColorGenerator.COLOUR_2, 45 )
         )
   ),
   Level2( 
      "2",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withWidth( 20 )
         .withHeight( 20 )
         .withNumberOfColours( 3 )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 25 )
                  .withTarget( ColorGenerator.COLOUR_1, 60 )
                  .withTarget( ColorGenerator.COLOUR_2, 60 )
         )
   ),
   Level3( 
      "3",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withWidth( 40 )
         .withHeight( 40 )
         .withNumberOfColours( 3 )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 35 )
                  .withTarget( ColorGenerator.COLOUR_1, 200 )
                  .withTarget( ColorGenerator.COLOUR_2, 200 )
         )
   ),
   Level4( 
      "4",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withWidth( 10 )
         .withHeight( 10 )
         .withNumberOfColours( 4 )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 25 )
                  .withTarget( ColorGenerator.COLOUR_3, 70 )
         )
   ),
   Level5( 
      "5",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withWidth( 20 )
         .withHeight( 20 )
         .withNumberOfColours( 4 )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 50 )
                  .withTarget( ColorGenerator.COLOUR_1, 150 )
                  .withTarget( ColorGenerator.COLOUR_2, 150 )
                  .withTarget( ColorGenerator.COLOUR_3, 150 )
         )
   );
   
   private final String levelName;
   private final GameBuilder builder;
   
   private ColoursGames( String levelName, GameBuilder builder ) {
      this.levelName = levelName;
      this.builder = builder;
   }//End Constructor
   
   @Override public String levelName() {
      return levelName;
   }//End Method
   
   @Override public GameBuilder builder(){
      return builder;
   }//End Method
   
}//End Enum
