package uk.dangrew.gnocchi.engine;

import java.util.List;
import java.util.stream.Collectors;

import uk.dangrew.gnocchi.framework.GameAction;
import uk.dangrew.gnocchi.framework.GameStack;
import uk.dangrew.gnocchi.framework.action.CompletionAction;
import uk.dangrew.gnocchi.framework.action.FillAction;
import uk.dangrew.gnocchi.framework.action.GridSetupAction;
import uk.dangrew.gnocchi.framework.action.SquareRemovalAction;
import uk.dangrew.gnocchi.framework.animation.BlastAnimation;
import uk.dangrew.gnocchi.framework.animation.GravityAnimation;
import uk.dangrew.gnocchi.framework.animation.NoAnimation;
import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.bonus.BonusCombiner;
import uk.dangrew.gnocchi.game.bonus.BonusDetector;
import uk.dangrew.gnocchi.game.matching.MatchChainer;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;
import uk.dangrew.gnocchi.grid.square.SquareType;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.mechanics.SquareTypeGenerator;
import uk.dangrew.gnocchi.ui.frame.content.GameLauncherController;

public class GameEngine {

   private final GameLauncherController launchController;
   private final InputDriver inputDriver;
   
   private final SquareTypeGenerator squareGenerator;
   private final BonusCombiner bonusCombiner;
   private final MatchChainer matchChainer;
   private final BonusDetector bonusDetector;
   private final GameStack stack;
   
   private Game game;
   
   public GameEngine( GameLauncherController launchController ) {
      this.launchController = launchController;
      this.inputDriver = new InputDriver( this );
      this.stack = new GameStack();
      this.bonusDetector = new BonusDetector();
      this.matchChainer = new MatchChainer();
      this.bonusCombiner = new BonusCombiner();
      this.squareGenerator = new SquareTypeGenerator();
   }//End Constructor
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method
   
   public void launch( Game game ){
      this.game = game;
      this.stack.stack( new GameAction( new GridSetupAction( game ), new GravityAnimation( game ) ) );
      this.stack.stack( new GameAction( new FillAction( game ), new GravityAnimation( game ) ) );
   }//End Method

   public void pop( Square object ) {
      List< Square > matches = matchChainer.match( game.model(), object.position().w, object.position().h );
      if ( matches.isEmpty() ) {
         return;
      }
      SquareBonusType bonus = bonusDetector.detectBonus( object, matches );
      if ( bonus != null ) {
         object.setType( bonus );
         matches.remove( object );
      }
      
      matches = matches.stream()
               .filter( s -> s.type().properties().isDestructible() )
               .collect( Collectors.toList() );
      
      stack.stack( new GameAction( new SquareRemovalAction( game, matches ), new BlastAnimation( game, matches ) ) );
      stack.stack( new GameAction( new FillAction( game ), new GravityAnimation( game ) ) );
      stack.stack( new GameAction( new CompletionAction( game, launchController ), new NoAnimation() ) );
   }//End Method

   public void combine( Square selected, Square bonus ) {
      SquareType combination = bonusCombiner.determineCombination( selected.type(), bonus.type() );
      if ( combination != null ) {
         selected.setType( combination );
         bonus.setType( squareGenerator.next( game.model().colourVariation() ) );
      }
   }//End Method
   
}//End Class
