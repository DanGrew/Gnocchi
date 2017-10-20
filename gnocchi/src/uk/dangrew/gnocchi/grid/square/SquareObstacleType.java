package uk.dangrew.gnocchi.grid.square;

import javafx.scene.paint.Color;

public enum SquareObstacleType implements SquareType {
   
//   Indestructible(
//            new SquareProperties()
//               .withImage( new Images().indestructibleImage() )
//   ),
   FixedIndestructible(
            new SquareProperties()
//               .withImage( new Images().indestructibleImage() )
               .withColour( Color.WHITE )
               .withMoveable( false )
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
