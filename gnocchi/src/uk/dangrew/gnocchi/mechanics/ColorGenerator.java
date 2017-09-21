package uk.dangrew.gnocchi.mechanics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;

public class ColorGenerator {
   
   private final Random random;
   private final Map< Integer, Color > colours;
   
   public ColorGenerator() {
      this( new Random() );
   }//End Constructor
   
   ColorGenerator( Random random ) {
      this.random = random;
      this.colours = new HashMap<>();
      this.colours.put( 0, Color.LIGHTSKYBLUE );
      this.colours.put( 1, Color.RED );
      this.colours.put( 2, Color.GREEN );
      this.colours.put( 3, Color.ORANGE );
      this.colours.put( 4, Color.MEDIUMPURPLE );
      this.colours.put( 5, Color.PEACHPUFF );
   }//End Constructor
   
   public Color next( int colourVariation ){
      int next = random.nextInt( colourVariation );
      return colours.get( next );
   }//End Method

}//End Class
