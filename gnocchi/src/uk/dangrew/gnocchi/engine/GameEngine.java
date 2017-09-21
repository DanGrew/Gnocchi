package uk.dangrew.gnocchi.engine;

import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;
import uk.dangrew.gnocchi.ui.grid.GridArea;

public class GameEngine {

   private final InputDriver inputDriver;
   private GridArea grid;
   
   public GameEngine() {
      this.inputDriver = new InputDriver( this );
   }//End Constructor
   
   public void setGridArea( GridArea area ){
      this.grid = area;
   }//End Method
   
   public InputDriver inputDriver(){
      return inputDriver;
   }//End Method

   public void launch() {
      grid.fillGrid();
   }//End Method
   
   public void pop( Square object ) {
      grid.pop( object );
   }//End Method

}//End Class
