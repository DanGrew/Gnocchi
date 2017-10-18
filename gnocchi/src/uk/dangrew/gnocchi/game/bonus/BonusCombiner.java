package uk.dangrew.gnocchi.game.bonus;

import uk.dangrew.gnocchi.grid.square.SquarePopType;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class BonusCombiner {
   
   public SquarePopType determineCombination( SquareType first, SquareType second ) {
      if ( !first.properties().isComboEnabled() || !second.properties().isComboEnabled() ) {
         return null;
      }
      
      if ( !first.properties().isPoppable() || !second.properties().isPoppable() ) {
         return null;
      }
      
      
      SquarePopType firstPoppable = ( SquarePopType )first;
      SquarePopType secondPoppable = ( SquarePopType )second;
      switch ( firstPoppable ) {
         case HorizontalBlast:
            switch ( secondPoppable ) {
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
            switch ( secondPoppable ) {
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
            switch ( secondPoppable ) {
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
            switch ( secondPoppable ) {
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
            switch ( secondPoppable ) {
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
