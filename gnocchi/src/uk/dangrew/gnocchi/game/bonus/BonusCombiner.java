package uk.dangrew.gnocchi.game.bonus;

import uk.dangrew.gnocchi.grid.square.SquarePopType;

public class BonusCombiner {
   
   public SquarePopType determineCombination( SquarePopType first, SquarePopType second ) {
      if ( !first.properties().isComboEnabled() || !second.properties().isComboEnabled() ) {
         return null;
      }
      switch ( first ) {
         case HorizontalBlast:
            switch ( second ) {
               case HorizontalBlast:
               case VerticalBlast:
                  return SquarePopType.CrossBlast;
               case CrossBlast:
                  return SquarePopType.BombBlast;
               case BombBlast:
                  return SquarePopType.BombHorizontal;
               case MassBlast:
                  return SquarePopType.MassHorizontal;
            }
         case VerticalBlast:
            switch ( second ) {
               case HorizontalBlast:
               case VerticalBlast:
                  return SquarePopType.CrossBlast;
               case CrossBlast:
                  return SquarePopType.BombBlast;
               case BombBlast:
                  return SquarePopType.BombVertical;
               case MassBlast:
                  return SquarePopType.MassVertical;
            }
         case CrossBlast:
            switch ( second ) {
               case HorizontalBlast:
               case VerticalBlast:
               case CrossBlast:
                  return SquarePopType.BombBlast;
               case BombBlast:
                  return SquarePopType.BombCross;
               case MassBlast:
                  return SquarePopType.MassCross;
            }
         case BombBlast:
            switch ( second ) {
               case HorizontalBlast:
                  return SquarePopType.BombHorizontal;
               case VerticalBlast:
                  return SquarePopType.BombVertical;
               case CrossBlast:
                  return SquarePopType.BombCross;
               case BombBlast:
                  return SquarePopType.BombBomb;
               case MassBlast:
                  return SquarePopType.MassBomb;
            }
         case MassBlast:
            switch ( second ) {
               case HorizontalBlast:
                  return SquarePopType.MassHorizontal;
               case VerticalBlast:
                  return SquarePopType.MassVertical;
               case CrossBlast:
                  return SquarePopType.MassCross;
               case BombBlast:
                  return SquarePopType.MassBomb;
               case MassBlast:
                  return SquarePopType.MassMass;
            }
      }
      return null;
   }//End Method

}//End Class
