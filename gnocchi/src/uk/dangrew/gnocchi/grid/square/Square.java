package uk.dangrew.gnocchi.grid.square;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;
import uk.dangrew.gnocchi.grid.model.GridPosition;

public class Square {

   private final ObjectProperty< SquareType > type;
   private GridPosition position;
   
   public Square( SquareType type ) {
      this( new GridPosition( -1, -1 ), type );
   }//End Constructor
   
   public Square( GridPosition position, SquareType type ) {
      this.position = position;
      this.type = new SimpleObjectProperty<>( type );
   }//End Constructor

   public Color colour() {
      return type.get().properties().colour();
   }//End Method
   
   public void moveTo( GridPosition position ) {
      this.position = position;
   }//End Method
   
   public GridPosition position(){
      return position;
   }//End Method
   
   public SquareType type() {
      return type.get();
   }//End Method
   
   public ReadOnlyObjectProperty< SquareType > typeProperty() {
      return type;
   }//End Method
   
   public void setType( SquareType type ) {
      this.type.set( type );
   }//End Method
   
   public SquareMatcher typeMatcher() {
      return type().properties().matcher();
   }//End Method
   
   public boolean matches( Square square ) {
      return type().properties().isBasic() &&
             square.type().properties().isBasic() &&
             type() == square.type();
   }//End Method
   
   @Override public String toString() {
      return position.toString();
   }//End Method
   
   public static Square randomSquare(){
      return new Square( new GridPosition( 0, 0 ), SquareRegularType.Primary );
   }//End Method
   
   public static Square typedSquare( SquareRegularType type ){
      return new Square( new GridPosition( 0, 0 ), type );
   }//End Method
   
}//End Class
