package uk.dangrew.gnocchi.ui.grid;

import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class GridMeasurements {

   static final int GRID_PIXEL_WIDTH = 800;
   static final int GRID_PIXEL_HEIGHT = 800;
   static final double OFFSCREEN = -1000.0;
   
   static final double GRAVITY_TOTAL_TIME = 500;
   
   static final double SQUARE_PORTION_OF_SQUARE_AREA = 0.9;
   static final double DRAW_AREA_WIDTH_PORTION_OF_GRID = 0.8;
   
   private final int pixelWidth;
   private final int pixelHeight;
   
   public GridMeasurements() {
      this( GRID_PIXEL_WIDTH, GRID_PIXEL_HEIGHT );
   }//End Constructor
   
   GridMeasurements( int pixelWidth, int pixelHeight ) {
      this.pixelWidth = pixelWidth;
      this.pixelHeight = pixelHeight;
   }//End Constructor
   
   public int gridPixelWidth() {
      return pixelWidth;
   }//End Method

   public int gridPixelHeight() {
      return pixelHeight;
   }//End Method

   public double calculateSquareDimension( int w, int h ) {
      double dimension = calculateSquareArea( w, h );
      return dimension * SQUARE_PORTION_OF_SQUARE_AREA;
   }//End Method
   
   public double calculateSquareArea( int w, int h ) {
      double width = pixelWidth * DRAW_AREA_WIDTH_PORTION_OF_GRID / w;
      double height = pixelHeight / h;
      
      return Math.min( width, height );
   }//End Method

   public double calculateWidthOffset( int w, int h ) {
      double squareArea = calculateSquareArea( w, h );
      double areaCoveredBySquare = squareArea * w;
      double offcut = pixelWidth - areaCoveredBySquare;
      offcut += squareArea;
      return offcut / 2;
   }//End Method
   
   public double calculateHeightOffset( int w, int h ) {
      double squareArea = calculateSquareArea( w, h );
      double areaCoveredBySquare = squareArea * h;
      double offcut = pixelHeight - areaCoveredBySquare;
      return offcut * 3 / 4;
   }//End Method

   public SquareWidget constructSquareWidget( Square object, int w, int h ) {
      double squareDimension = calculateSquareDimension( w, h );
      return new SquareWidget( object, OFFSCREEN, OFFSCREEN, squareDimension, squareDimension );
   }//End Method

   public double calculateGravity( int h ) {
      return GRAVITY_TOTAL_TIME / h;
   }//End Method

}//End Class
