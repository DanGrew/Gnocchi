package uk.dangrew.gnocchi.grid.square;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.NoneMatcher;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;

public class SquareProperties {

   static final SquareMatcher DEFAULT_MATCHER = new NoneMatcher();
   static final Color DEFAULT_COLOUR = Color.RED;
   
   private SquareMatcher matcher;
   private Color colour;
   private Image image;
   private boolean comboEnabled;
   private boolean poppable;
   private boolean moveable;
   private boolean basic;
   
   public SquareProperties() {
      this.matcher = DEFAULT_MATCHER;
      this.colour = DEFAULT_COLOUR;
      this.image = null;
      this.comboEnabled = false;
      this.moveable = true;
      this.basic = false;
   }//End Constructor
   
   public SquareMatcher matcher() {
      return matcher;
   }//End Method

   public SquareProperties withMatcher( SquareMatcher matcher ) {
      this.matcher = matcher;
      return this;
   }//End Method

   public Image image() {
      return image;
   }//End Method

   public SquareProperties withImage( Image image ) {
      this.image = image;
      return this;
   }//End Method

   public boolean isComboEnabled() {
      return comboEnabled;
   }//End Method

   public SquareProperties withComboEnabled( boolean enabled ) {
      this.comboEnabled = enabled;
      return this;
   }//End Method

   public boolean isPoppable() {
      return poppable;
   }//End Method

   public SquareProperties withPoppable( boolean poppable ) {
      this.poppable = poppable;
      return this;
   }//End Method

   public boolean isMoveable() {
      return moveable;
   }//End Method

   public SquareProperties withMoveable( boolean moveable ) {
      this.moveable = moveable;
      return this;
   }//End Method

   public Color colour() {
      return colour;
   }//End Method

   public SquareProperties withColour( Color colour ) {
      this.colour = colour;
      return this;
   }//End Method

   public boolean isBasic() {
      return basic;
   }//End Method

   public SquareProperties withBasic( boolean basic ) {
      this.basic = basic;
      return this;
   }//End Method

}//End Class
