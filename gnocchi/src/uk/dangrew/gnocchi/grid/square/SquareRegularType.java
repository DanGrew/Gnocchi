package uk.dangrew.gnocchi.grid.square;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.FloodFill;

public enum SquareRegularType implements SquareType {

   Primary( new SquareProperties().withColour( Color.LIGHTSKYBLUE ) ),
   Secondary( new SquareProperties().withColour( Color.RED ) ), 
   Tertiary( new SquareProperties().withColour( Color.GREEN ) ),
   Quaternary( new SquareProperties().withColour( Color.ORANGE ) ),
   Quinary( new SquareProperties().withColour( Color.MEDIUMPURPLE ) ), 
   Senary( new SquareProperties().withColour( Color.PEACHPUFF ) ), 
   Septenary( new SquareProperties().withColour( Color.DEEPPINK ) );
   
   private final SquareProperties properties;
   
   private SquareRegularType( SquareProperties properties ) {
      this.properties = properties;
      this.properties
         .withMatcher( new FloodFill() )
         .withMoveable( true )
         .withPoppable( true )
         .withBasic( true );
   }//End Constructor
   
   @Override public SquareProperties properties() {
      return properties;
   }//End Method
   
}//End Enum
