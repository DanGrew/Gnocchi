package uk.dangrew.gnocchi.game.bonus;

import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusCombiner {
   
   public SquareBonusType determineCombination( SquareType first, SquareType second ) {
      if ( !first.properties().isComboEnabled() || !second.properties().isComboEnabled() ) {
         return null;
      }
      
      if ( !first.properties().isPoppable() || !second.properties().isPoppable() ) {
         return null;
      }
      
      
      SquareBonusType firstPoppable = ( SquareBonusType )first;
      SquareBonusType secondPoppable = ( SquareBonusType )second;
      switch ( firstPoppable ) {
         case HorizontalBlast:
            switch ( secondPoppable ) {
               case HorizontalBlast:
               case VerticalBlast:
                  return SquareBonusType.CrossBlast;
               case CrossBlast:
                  return SquareBonusType.BombBlast;
               case BombBlast:
                  return SquareBonusType.BombHorizontal;
               case MassBlast:
                  return SquareBonusType.MassHorizontal;
            }
         case VerticalBlast:
            switch ( secondPoppable ) {
               case HorizontalBlast:
               case VerticalBlast:
                  return SquareBonusType.CrossBlast;
               case CrossBlast:
                  return SquareBonusType.BombBlast;
               case BombBlast:
                  return SquareBonusType.BombVertical;
               case MassBlast:
                  return SquareBonusType.MassVertical;
            }
         case CrossBlast:
            switch ( secondPoppable ) {
               case HorizontalBlast:
               case VerticalBlast:
               case CrossBlast:
                  return SquareBonusType.BombBlast;
               case BombBlast:
                  return SquareBonusType.BombCross;
               case MassBlast:
                  return SquareBonusType.MassCross;
            }
         case BombBlast:
            switch ( secondPoppable ) {
               case HorizontalBlast:
                  return SquareBonusType.BombHorizontal;
               case VerticalBlast:
                  return SquareBonusType.BombVertical;
               case CrossBlast:
                  return SquareBonusType.BombCross;
               case BombBlast:
                  return SquareBonusType.BombBomb;
               case MassBlast:
                  return SquareBonusType.MassBomb;
            }
         case MassBlast:
            switch ( secondPoppable ) {
               case HorizontalBlast:
                  return SquareBonusType.MassHorizontal;
               case VerticalBlast:
                  return SquareBonusType.MassVertical;
               case CrossBlast:
                  return SquareBonusType.MassCross;
               case BombBlast:
                  return SquareBonusType.MassBomb;
               case MassBlast:
                  return SquareBonusType.MassMass;
            }
      }
      return null;
   }//End Method

}//End Class
