package uk.dangrew.gnocchi.grid.square;

import uk.dangrew.gnocchi.ui.resources.Images;

public enum SquareObstacleType implements SquareType {
   
//   Indestructible(
//            new SquareProperties()
//               .withImage( new Images().indestructibleImage() )
//   ),
   FixedIndestructible(
            new SquareProperties()
               .withImage( new Images().indestructibleImage() )
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
