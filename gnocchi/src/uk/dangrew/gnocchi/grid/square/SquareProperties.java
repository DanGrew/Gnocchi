package uk.dangrew.gnocchi.grid.square;

import javafx.scene.image.Image;
import uk.dangrew.gnocchi.algorithm.NoneMatcher;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;

public class SquareProperties {

   static final SquareMatcher DEFAULT_MATCHER = new NoneMatcher();
   
   private SquareMatcher matcher;
   private Image image;
   private boolean comboEnabled;
   private boolean poppable;
   private boolean moveable;
   
   public SquareProperties() {
      this.matcher = DEFAULT_MATCHER;
      this.image = null;
      this.comboEnabled = false;
      this.moveable = true;
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

}//End Class
