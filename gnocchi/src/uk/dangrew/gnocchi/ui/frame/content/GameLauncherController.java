package uk.dangrew.gnocchi.ui.frame.content;

import uk.dangrew.gnocchi.engine.GameEngine;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.GameBuilder;
import uk.dangrew.gnocchi.game.type.GameType;
import uk.dangrew.gnocchi.ui.frame.GnocchiFrame;

public class GameLauncherController {

   private final GnocchiFrame frame;
   private final GameEngine engine;
   
   private final MainMenu mainMenu;
   private final GameSelection gameSelection;
   private final GridSelection gridSelection;
   
   public GameLauncherController( GnocchiFrame frame ) {
      this.frame = frame;
      this.engine = new GameEngine( this );
      
      this.gameSelection = new GameSelection( this );
      this.gridSelection = new GridSelection( this );
      this.mainMenu = new MainMenu( gameSelection, gridSelection );
      
      this.reset();
   }//End Constructor
   
   public void showGridsFor( GameType type ) {
      gridSelection.showLevels( type );
   }//End Method

   public void showGrid( GameBuilder builder ) {
      Game game = new Game( engine.inputDriver(), builder );
      engine.launch( game );
      frame.setContent( game.gridUi(), game.propertiesUi() );
   }//End Method

   public void reset() {
      frame.setContent( mainMenu, null );
   }//End Method
   
   MainMenu mainMenu(){
      return mainMenu;
   }//End Method
   
}//End Method
