package uk.dangrew.gnocchi.grid;

public class Grid {
   
   private final GridModel model;
   
   public Grid( int width, int height ) {
      this.model = new GridModel( width, height );
   }//End Constructor
}//End Class
