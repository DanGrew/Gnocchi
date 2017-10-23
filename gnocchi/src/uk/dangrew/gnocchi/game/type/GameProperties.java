package uk.dangrew.gnocchi.game.type;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GameProperties {

   static final String PROPERTY_MOVES = "Moves Allowed";
   
   public static final int DEFAULT_MOVES = 25;
   
   private final ObjectProperty< Integer > movesRemaining;
   
   public GameProperties() {
      this.movesRemaining = new SimpleObjectProperty<>( 0 );
      this.reset();
   }//End Constructor
   
   public GameProperties withMoves( int moves ) {
      movesRemaining.set( moves );
      return this;
   }//End Method
   
   public ObjectProperty< Integer > movesRemaining() {
      return movesRemaining;
   }//End Method

   public void increaseMoves( int m ) {
      movesRemaining.set( movesRemaining.get() + m );
   }//End Method

   public void moveUsed() {
      moveUsed( 1 );
   }//End Method
   
   public void moveUsed( int movesUsed ) {
      movesRemaining.set( movesRemaining.get() - movesUsed );
   }//End Method

   public void reset() {
      this.movesRemaining.set( DEFAULT_MOVES );
   }//End Method
   
   public void configureInformation( GameInformation information ) {
      information.putProperty( PROPERTY_MOVES, movesRemaining().get().toString() );
   }//End Method

}//End Class
