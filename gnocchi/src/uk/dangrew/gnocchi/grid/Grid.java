package uk.dangrew.gnocchi.grid;

import uk.dangrew.gnocchi.grid.controls.Filler;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;

public class Grid {
   
   private final Filler filler;
   private final GridModel model;
   
   public Grid( int colourVariation, int width, int height ) {
      this( new GridModel( colourVariation, width, height ) );
   }//End Constructor
   
   private Grid( GridModel model ) {
      this( model, new Filler( model ) );
   }//End Constructor
   
   Grid( GridModel model, Filler filler ) {
      this.model = model;
      this.filler = filler;
   }//End Constructor

   public GridSnapshot snapshot(){
      return model.snapshot();
   }//End Method
   
   public GridModel model(){
      return model;
   }//End Method
   
   public void fill(){
      filler.fill();
   }//End Method 
   
}//End Class
