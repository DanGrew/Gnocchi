package uk.dangrew.gnocchi.grid.square;

import javafx.scene.paint.Color;

public enum SquareObstacleType implements SquareType {
   
   MoveableIndestructible(
            new SquareProperties()
               .withColour( Color.WHITE )
               .withMoveable( false )
               .withDestructible( false )
   ),
   FixedIndestructible(
            new SquareProperties()
               .withColour( Color.WHITE )
               .withMoveable( false )
               .withDestructible( false )
   ),
   MoveableDestructible(
            new SquareProperties()
               .withColour( Color.WHITE )
               .withMoveable( false )
               .withDestructible( true )
   ),
   FixedDstructible(
            new SquareProperties()
               .withColour( Color.WHITE )
               .withMoveable( false )
               .withDestructible( true )
   )
   ;

   private final SquareProperties properties;
   
   private SquareObstacleType( SquareProperties properties ) {
      this.properties = properties;
   }//End Constructor
   
   @Override public SquareProperties properties() {
      return properties;
   }//End Method

}//End Enum
