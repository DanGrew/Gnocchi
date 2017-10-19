package uk.dangrew.gnocchi.grid.square;

import uk.dangrew.gnocchi.algorithm.FloodFill;

public enum SquareRegularType implements SquareType {

   Regular( 
            new SquareProperties().withMatcher( new FloodFill() )
   );
   
   private final SquareProperties properties;
   
   private SquareRegularType( SquareProperties properties ) {
      this.properties = properties;
   }//End Constructor
   
   @Override public SquareProperties properties() {
      return properties;
   }//End Method
   
}//End Enum
