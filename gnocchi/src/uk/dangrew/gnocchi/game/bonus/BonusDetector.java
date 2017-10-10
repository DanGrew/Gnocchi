package uk.dangrew.gnocchi.game.bonus;

import java.util.List;

import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquarePopType;

public class BonusDetector {
   
   static final int LINE_THRESHOLD = 7;
   static final int BOMB_THRESHOLD = 15;
   static final int MASS_THRESHOLD = 20;

   public SquarePopType detectBonus( Square source, List< Square > matches ) {
      if ( source.type() != SquarePopType.Regular ) {
         return null;
      }
      if ( matches.size() < LINE_THRESHOLD ) {
         return null;
      }
      if ( matches.size() >= MASS_THRESHOLD ) {
         return SquarePopType.MassBlast;
      }
      if ( matches.size() >= BOMB_THRESHOLD ) {
         return SquarePopType.BombBlast;
      }
      
      int minWidth = source.position().w;
      int maxWidth = source.position().w;
      int minHeight = source.position().h;
      int maxHeight = source.position().h;
      
      for ( Square square : matches ) {
         minWidth = Math.min( minWidth, square.position().w );
         maxWidth = Math.max( maxWidth, square.position().w );
         
         minHeight = Math.min( minHeight, square.position().h );
         maxHeight = Math.max( maxHeight, square.position().h );
      }
      
      int widthRange = maxWidth - minWidth;
      int heightRange = maxHeight - minHeight;
      
      if ( widthRange > heightRange ) {
         return SquarePopType.HorizontalBlast;
      } else if ( heightRange > widthRange ) {
         return SquarePopType.VerticalBlast;
      } else {
         return SquarePopType.CrossBlast;
      }
   }//End Method

}//End Class
