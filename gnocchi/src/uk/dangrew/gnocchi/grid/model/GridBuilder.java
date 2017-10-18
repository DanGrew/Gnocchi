package uk.dangrew.gnocchi.grid.model;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class GridBuilder {

   static final int DEFAULT_COLOUR_VARIATION = 3;
   static final int DEFAULT_WIDTH = 10;
   static final int DEFAULT_HEIGHT = 10;
   
   private int colourVariation;
   private int width;
   private int height;
   private List< Pair< GridPosition, SquareType > > specifics;
   
   public GridBuilder() {
      this.colourVariation = DEFAULT_COLOUR_VARIATION;
      this.width = DEFAULT_WIDTH;
      this.height = DEFAULT_HEIGHT;
      this.specifics = new ArrayList<>();
   }//End Constructor
   
   public int colourVariation() {
      return colourVariation;
   }//End Method

   public GridBuilder withNumberOfColours( int n ) {
      this.colourVariation = n;
      return this;
   }//End Method
   
   public int width() {
      return width;
   }//End Method

   public GridBuilder withWidth( int n ) {
      this.width = n;
      return this;
   }//End Method
   
   public int height() {
      return height;
   }//End Method

   public GridBuilder withHeight( int n ) {
      this.height = n;
      return this;
   }//End Method

   public List< Pair< GridPosition, SquareType > > specifics() {
      return specifics;
   }//End Method

   public GridBuilder withSpecific( GridPosition position, SquareType type ) {
      specifics.add( new Pair<>( position, type ) );
      return this;
   }//End Method
   
}//End Class
