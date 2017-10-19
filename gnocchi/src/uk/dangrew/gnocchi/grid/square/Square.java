package uk.dangrew.gnocchi.grid.square;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;
import uk.dangrew.gnocchi.grid.model.GridPosition;

public class Square {

   private final ObjectProperty< Color > colour;
   private final ObjectProperty< SquareType > type;
   private GridPosition position;
   
   public Square( Color colour ) {
      this( new GridPosition( -1, -1 ), colour );
   }//End Constructor
   
   public Square( GridPosition position, Color colour ) {
      this.position = position;
      this.colour = new SimpleObjectProperty<>( colour );
      this.type = new SimpleObjectProperty<>( SquareRegularType.Regular );
   }//End Constructor

   public Color colour() {
      return colour.get();
   }//End Method
   
   public ReadOnlyObjectProperty< Color > colourProperty() {
      return colour;
   }//End Method
   
   public void setColour( Color colour ) {
      this.colour.set( colour );
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
      if ( 
               type.get() != SquareRegularType.Regular ||
               square.type() != SquareRegularType.Regular 
      ) {
         return false;
      }
      return square.colour().equals( colour() );
   }//End Method
   
   @Override public String toString() {
      return position.toString();
   }//End Method
   
   public static Square randomSquare(){
      return new Square( new GridPosition( 0, 0 ), Color.RED );
   }//End Method
   
   public static Square colouredSquare( Color colour ){
      return new Square( new GridPosition( 0, 0 ), colour );
   }//End Method
   
}//End Class
