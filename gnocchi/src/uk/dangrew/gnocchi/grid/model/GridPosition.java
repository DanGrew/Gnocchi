package uk.dangrew.gnocchi.grid.model;

public class GridPosition {
   
   public final int w;
   public final int h;
   
   public GridPosition( int w, int h ) {
      this.w = w;
      this.h = h;
   }//End Constructor

   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + h;
      result = prime * result + w;
      return result;
   }//End Method

   @Override public boolean equals( Object obj ) {
      if ( this == obj ) {
         return true;
      }
      if ( obj == null ) {
         return false;
      }
      if ( getClass() != obj.getClass() ) {
         return false;
      }
      GridPosition other = ( GridPosition ) obj;
      if ( h != other.h ) {
         return false;
      }
      if ( w != other.w ) {
         return false;
      }
      return true;
   }//End Method
   
   @Override public String toString() {
      return "( " + w + ", " + h + ")";
   }//End Method

}//End Class
