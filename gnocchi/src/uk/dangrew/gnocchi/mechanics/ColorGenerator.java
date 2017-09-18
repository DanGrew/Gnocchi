package uk.dangrew.gnocchi.mechanics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;

public class ColorGenerator {
   
   private final Random random;
   private final Map< Integer, Color > colours;
   
   public ColorGenerator() {
      this.random = new Random();
      this.colours = new HashMap<>();
      this.colours.put( 0, Color.LIGHTSKYBLUE );
      this.colours.put( 1, Color.RED );
      this.colours.put( 2, Color.GREEN );
      this.colours.put( 3, Color.ORANGE );
   }//End Constructor
   
   public Color next(){
      int next = random.nextInt( 4 );
      return colours.get( next );
   }//End Method

}//End Class
