package uk.dangrew.gnocchi.game.type;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.dangrew.gnocchi.game.GameBuilder;

public class GameInformation {

   static final String PROPERTY_SIZE = "Size";
   static final String PROPERTY_COLOURS = "Colours";
   
   private final Map< String, String > information;
   
   public GameInformation(){
      this.information = new LinkedHashMap<>();
   }//End Constructor
   
   public void configure( GameBuilder builder ) {
      this.information.put( PROPERTY_SIZE, builder.gridBuilder().width() + " x " + builder.gridBuilder().height() );
      this.information.put( PROPERTY_COLOURS, Integer.toString( builder.gridBuilder().colourVariation() ) );
      
      builder.properties().configureInformation( this );
   }//End Constructor
   
   public List< String > keys(){
      return new ArrayList<>( information.keySet() );
   }//End Method
   
   public String valueFor( String key ) {
      return information.get( key );
   }//End Method

   public void putProperty( String k, String v ) {
      this.information.put( k, v );
   }//End Method

}//End Class
