package uk.dangrew.gnocchi.game;

import uk.dangrew.gnocchi.grid.controls.Filler;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.grid.GridWidget;

public class Game {

   private final Filler filler;
   private final GridModel model;
   private final GridWidget gridWidget;
   
   public Game( InputDriver inputDriver, GameBuilder builder ) {
      this.model = new GridModel( builder.colourVariation(), builder.width(), builder.height() );
      this.gridWidget = new GridWidget( model, inputDriver );
      this.filler = new Filler( model );
   }//End Constructor
   
   public GridModel model(){
      return model;
   }//End Method
   
   public GridWidget ui(){
      return gridWidget;
   }//End Method
   
   public GridSnapshot snapshot(){
      return model.snapshot();
   }//End Method
   
   public void fill(){
      filler.fill();
   }//End Method 
}//End Class
