package uk.dangrew.gnocchi.mechanics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;

public class ColorGenerator {
   
   public static final Color COLOUR_1 = Color.LIGHTSKYBLUE;
   public static final Color COLOUR_2 = Color.RED;
   public static final Color COLOUR_3 = Color.GREEN;
   public static final Color COLOUR_4 = Color.ORANGE;
   public static final Color COLOUR_5 = Color.MEDIUMPURPLE;
   public static final Color COLOUR_6 = Color.PEACHPUFF;
   public static final Color COLOUR_7 = Color.DEEPPINK;
   
   private final Random random;
   private final Map< Integer, Color > colours;
   
   public ColorGenerator() {
      this( new Random() );
   }//End Constructor
   
   ColorGenerator( Random random ) {
      this.random = random;
      this.colours = new HashMap<>();
      this.colours.put( 0, COLOUR_1 );
      this.colours.put( 1, COLOUR_2 );
      this.colours.put( 2, COLOUR_3 );
      this.colours.put( 3, COLOUR_4 );
      this.colours.put( 4, COLOUR_5 );
      this.colours.put( 5, COLOUR_6 );
      this.colours.put( 6, COLOUR_7 );
   }//End Constructor
   
   public Color next( int colourVariation ){
      int next = random.nextInt( colourVariation );
      return colours.get( next );
   }//End Method

}//End Class
