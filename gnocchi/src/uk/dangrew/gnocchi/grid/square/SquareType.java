package uk.dangrew.gnocchi.grid.square;

import uk.dangrew.gnocchi.algorithm.BombMatcher;
import uk.dangrew.gnocchi.algorithm.ColumnMatcher;
import uk.dangrew.gnocchi.algorithm.CrossMatcher;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.algorithm.RowMatcher;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;

public enum SquareType {

   Regular( new FloodFill() ),
   HorizontalBlast( new RowMatcher() ),
   VerticalBlast( new ColumnMatcher() ),
   CrossBlast( new CrossMatcher() ),
   BombBlast( new BombMatcher() );
   
   private final SquareMatcher matcher;
   
   private SquareType( SquareMatcher matcher ) {
      this.matcher = matcher;
   }//End Constructor
   
   public SquareMatcher matcher(){
      return matcher;
   }//End Method
}//End Enum
