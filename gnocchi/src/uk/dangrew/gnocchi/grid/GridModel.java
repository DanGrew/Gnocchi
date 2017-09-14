package uk.dangrew.gnocchi.grid;

public class GridModel {

   private final int width;
   private final int height;
   private final Object[][] grid;
   
   public GridModel( int width, int height ) {
      this.width = width;
      this.height = height;
      
      this.grid = new Object[ width ][ height ];
   }//End Constructor
   
   public Object at( int w, int h ) {
      if ( w < 0 || w >= width ) {
         return null;
      }
      if ( h < 0 || h >= height ) {
         return null;
      }
      return grid[ w ][ h ];
   }//End Method
   
   public void set( Object object, int w, int h ) {
      grid[ w ][ h ] = object;
   }//End Method
   
   public int width() {
      return width;
   }//End Method

   public int height() {
      return height;
   }//End Method
}//End Class
