package uk.dangrew.gnocchi.game.bonus;

import java.util.List;

import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusDetector {
   
   static final int LINE_THRESHOLD = 7;
   static final int BOMB_THRESHOLD = 10;

   public SquareType detectBonus( Square source, List< Square > matches ) {
      if ( source.type() != SquareType.Regular ) {
         return null;
      }
      if ( matches.size() < LINE_THRESHOLD ) {
         return null;
      }
      if ( matches.size() >= BOMB_THRESHOLD ) {
         return SquareType.BombBlast;
      }
      
      int moreWidth = 0;
      int moreHeight = 0;
      for ( Square square : matches ) {
         int w = Math.abs( square.position().w - source.position().w );
         int h = Math.abs( square.position().h - source.position().h );
         
         if ( w > h ) {
            moreWidth++;
         } else if ( h > w ) {
            moreHeight++;
         }
      }
      
      if ( moreWidth > moreHeight ) {
         return SquareType.HorizontalBlast;
      } else if ( moreHeight > moreWidth ) {
         return SquareType.VerticalBlast;
      } else {
         return SquareType.CrossBlast;
      }
   }//End Method

}//End Class
