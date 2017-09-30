package uk.dangrew.gnocchi.framework;

public class GameAction {

   private final Action action;
   private final GnocchiAnimation gnocchiAnimation;
   
   public GameAction( Action action, GnocchiAnimation gnocchiAnimation ) {
      this.action = action;
      this.gnocchiAnimation = gnocchiAnimation;
   }//End Constructor

   public void execute() {
      action.execute();
      gnocchiAnimation.animate();
   }//End Method

   public void setOnCompletion( Runnable onCompletion ) {
      gnocchiAnimation.setOnCompletion( onCompletion );
   }//End Method

}//End Class
