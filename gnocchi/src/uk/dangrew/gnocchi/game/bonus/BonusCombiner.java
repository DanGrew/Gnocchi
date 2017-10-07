package uk.dangrew.gnocchi.game.bonus;

import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusCombiner {
   
   public SquareType determineCombination( SquareType first, SquareType second ) {
      if ( !first.isComboEnabled() || !second.isComboEnabled() ) {
         return null;
      }
      switch ( first ) {
         case HorizontalBlast:
            switch ( second ) {
               case HorizontalBlast:
               case VerticalBlast:
                  return SquareType.CrossBlast;
               case CrossBlast:
                  return SquareType.BombBlast;
               case BombBlast:
                  return SquareType.BombHorizontal;
               case MassBlast:
                  return SquareType.MassHorizontal;
            }
         case VerticalBlast:
            switch ( second ) {
               case HorizontalBlast:
               case VerticalBlast:
                  return SquareType.CrossBlast;
               case CrossBlast:
                  return SquareType.BombBlast;
               case BombBlast:
                  return SquareType.BombVertical;
               case MassBlast:
                  return SquareType.MassVertical;
            }
         case CrossBlast:
            switch ( second ) {
               case HorizontalBlast:
               case VerticalBlast:
               case CrossBlast:
                  return SquareType.BombBlast;
               case BombBlast:
                  return SquareType.BombCross;
               case MassBlast:
                  return SquareType.MassCross;
            }
         case BombBlast:
            switch ( second ) {
               case HorizontalBlast:
                  return SquareType.BombHorizontal;
               case VerticalBlast:
                  return SquareType.BombVertical;
               case CrossBlast:
                  return SquareType.BombCross;
               case BombBlast:
                  return SquareType.BombBomb;
               case MassBlast:
                  return SquareType.MassBomb;
            }
         case MassBlast:
            switch ( second ) {
               case HorizontalBlast:
                  return SquareType.MassHorizontal;
               case VerticalBlast:
                  return SquareType.MassVertical;
               case CrossBlast:
                  return SquareType.MassCross;
               case BombBlast:
                  return SquareType.MassBomb;
               case MassBlast:
                  return SquareType.MassMass;
            }
      }
      return null;
   }//End Method

}//End Class
