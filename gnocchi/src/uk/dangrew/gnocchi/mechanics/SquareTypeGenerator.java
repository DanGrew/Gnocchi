package uk.dangrew.gnocchi.mechanics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.dangrew.gnocchi.grid.square.SquareRegularType;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class SquareTypeGenerator {
   
   private static final Random INSTANCE = new Random();
   
   private final Random random;
   private final Map< Integer, SquareRegularType > types;
   
   public SquareTypeGenerator() {
      this( INSTANCE );
   }//End Constructor
   
   SquareTypeGenerator( Random random ) {
      this.random = random;
      this.types = new HashMap<>();
      this.types.put( 0, SquareRegularType.Primary );
      this.types.put( 1, SquareRegularType.Secondary );
      this.types.put( 2, SquareRegularType.Tertiary );
      this.types.put( 3, SquareRegularType.Quaternary );
      this.types.put( 4, SquareRegularType.Quinary );
      this.types.put( 5, SquareRegularType.Senary );
      this.types.put( 6, SquareRegularType.Septenary );
   }//End Constructor
   
   public SquareType next( int colourVariation ){
      int next = random.nextInt( colourVariation );
      return types.get( next );
   }//End Method

}//End Class
