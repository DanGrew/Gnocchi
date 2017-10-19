package uk.dangrew.gnocchi.grid.square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareTypes {

   private static final SquareType[] TYPES;
   
   static {
      List< SquareType > values = new ArrayList<>();
      values.addAll( Arrays.asList( SquareRegularType.values() ) );
      values.addAll( Arrays.asList( SquareBonusType.values() ) );
      values.addAll( Arrays.asList( SquareObstacleType.values() ) );
      TYPES = new SquareType[ values.size() ];
      values.toArray( TYPES );
   }
   
   public static SquareType[] values() {
      return TYPES;
   }//End Method
   
}//End Class
