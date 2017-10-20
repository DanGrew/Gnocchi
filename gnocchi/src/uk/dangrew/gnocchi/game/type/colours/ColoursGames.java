package uk.dangrew.gnocchi.game.type.colours;

import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.game.type.GameLevel;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.grid.model.GridBuilder;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.SquareObstacleType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public enum ColoursGames implements GameLevel {

   Level1( 
      "1",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 10 )
                  .withHeight( 10 )
                  .withNumberOfColours( 3 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 25 )
                  .withTarget( SquareRegularType.Primary, 45 )
                  .withTarget( SquareRegularType.Secondary, 45 )
         )
   ),
   Level2( 
      "2",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 20 )
                  .withHeight( 20 )
                  .withNumberOfColours( 3 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 25 )
                  .withTarget( SquareRegularType.Primary, 60 )
                  .withTarget( SquareRegularType.Secondary, 60 )
         )
   ),
   Level3( 
      "3",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 40 )
                  .withHeight( 40 )
                  .withNumberOfColours( 3 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 35 )
                  .withTarget( SquareRegularType.Primary, 200 )
                  .withTarget( SquareRegularType.Secondary, 200 )
         )
   ),
   Level4( 
      "4",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 10 )
                  .withHeight( 10 )
                  .withNumberOfColours( 4 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 25 )
                  .withTarget( SquareRegularType.Tertiary, 70 )
         )
   ),
   Level5( 
      "5",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 20 )
                  .withHeight( 20 )
                  .withNumberOfColours( 4 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 50 )
                  .withTarget( SquareRegularType.Primary, 150 )
                  .withTarget( SquareRegularType.Secondary, 150 )
                  .withTarget( SquareRegularType.Tertiary, 150 )
         )
   ),
   Level6( 
      "6",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 30 )
                  .withHeight( 30 )
                  .withNumberOfColours( 6 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 50 )
                  .withTarget( SquareRegularType.Primary, 150 )
                  .withTarget( SquareRegularType.Secondary, 150 )
                  .withTarget( SquareRegularType.Tertiary, 150 )
         )
   ),
   Level7( 
      "7",
      new GameBuilder()
         .ofType( GameType.Colours )
         .withGrid( new GridBuilder()
                  .withWidth( 10 )
                  .withHeight( 10 )
                  .withNumberOfColours( 5 )
         )
         .withProperties( new ColoursGtProperties()
                  .withMoves( 50 )
                  .withTarget( SquareRegularType.Primary, 150 )
                  .withTarget( SquareRegularType.Secondary, 150 )
                  .withTarget( SquareRegularType.Tertiary, 150 )
         )
   ),
   Level8( 
            "8",
            new GameBuilder()
               .ofType( GameType.Colours )
               .withGrid( new GridBuilder()
                        .withWidth( 10 )
                        .withHeight( 10 )
                        .withNumberOfColours( 5 )
                        .withSpecific( new GridPosition( 3, 3 ), SquareObstacleType.FixedIndestructible )
                        .withSpecific( new GridPosition( 7, 3 ), SquareObstacleType.FixedIndestructible )
                        .withSpecific( new GridPosition( 4, 6 ), SquareObstacleType.FixedIndestructible )
                        .withSpecific( new GridPosition( 6, 6 ), SquareObstacleType.FixedIndestructible )
               )
               .withProperties( new ColoursGtProperties()
                        .withMoves( 50 )
                        .withTarget( SquareRegularType.Primary, 150 )
                        .withTarget( SquareRegularType.Secondary, 150 )
                        .withTarget( SquareRegularType.Tertiary, 150 )
               )
         )
   ;
   
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
