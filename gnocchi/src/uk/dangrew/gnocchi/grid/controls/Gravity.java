package uk.dangrew.gnocchi.grid.controls;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class Gravity {

   private final GridModel grid;
   
   public Gravity( GridModel grid ) {
      this.grid = grid;
   }//End Class
   
   public void pullDown( int w ) {
      List< Integer > filledSquares = new ArrayList<>(); 
      
      for ( int h = 0; h < grid.height(); h++ ) {
         if ( grid.at( w, h ) != null ) {
            filledSquares.add( h );
         }
      }
      
      int lastFilledPosition = -1;
      for ( int h = 0; h < filledSquares.size(); h++ ) {
         
         lastFilledPosition++;
         int squarePosition = filledSquares.get( h );
         
         Square next = grid.at( w, squarePosition );
         if ( !next.type().properties().isMoveable() ) {
            lastFilledPosition = squarePosition;
         }
         grid.set( next, w, lastFilledPosition );
      }
   }//End Method

   public void slideForGaps( int w ) {
      for ( int h = 0; h < grid.height(); h++ ) {
         if ( !grid.isEmpty( w, h ) ) {
            continue;
         }
         
         if ( slideFromLeft( w, h ) ) {
            pullDown( w - 1 );
            pullDown( w );
         } else if ( slideFromRight( w, h ) ) {
            pullDown( w + 1 );
            pullDown( w );
         }
      }
   }//End Method
   
   private boolean slideFromLeft( int w, int h ) {
      if ( w == 0 || h > grid.height() - 1 ) {
         return false;
      }
      
      return slide( w, h, -1 );
   }//End Method
   
   private boolean slideFromRight( int w, int h ) {
      if ( w == grid.lastWidthIndex()  || h > grid.lastHeightIndex() ) {
         return false;
      }

      return slide( w, h, 1 );
   }//End Method
   
   private boolean slide( int w, int h, int direction ) {
      Square slideCandidate = null;
      int candidateHeight = h + 1;
      while (  
                  ( slideCandidate == null || !slideCandidate.type().properties().isMoveable() ) &&
                  candidateHeight <= grid.height()
      ) {
         slideCandidate = grid.at( w + direction, candidateHeight++ );
      }
      
      if ( slideCandidate == null ) {
         return false;
      }
      
      grid.set( slideCandidate, w, h );
      return true;
   }//End Method

}//End Class
