package uk.dangrew.gnocchi.grid.square;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridPosition;

public class Square {

   private final Color colour;
   private GridPosition position;
   
   public Square( Color colour ) {
      this( new GridPosition( -1, -1 ), colour );
   }//End Constructor
   
   public Square( GridPosition position, Color colour ) {
      this.position = position;
      this.colour = colour;
   }//End Constructor

   public Color colour() {
      return colour;
   }//End Method
   
   public void moveTo( GridPosition position ) {
      this.position = position;
   }//End Method
   
   public GridPosition position(){
      return position;
   }//End Method
   
   public boolean matches( Square square ) {
      return square.colour.equals( colour );
   }//End Method
   
   public static Square randomSquare(){
      return new Square( new GridPosition( 0, 0 ), Color.RED );
   }//End Method
   
   public static Square colouredSquare( Color colour ){
      return new Square( new GridPosition( 0, 0 ), colour );
   }//End Method
   
}//End Class
