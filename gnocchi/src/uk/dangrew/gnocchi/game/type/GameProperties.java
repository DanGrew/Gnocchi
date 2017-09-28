package uk.dangrew.gnocchi.game.type;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GameProperties {

   private final ObjectProperty< Integer > movesRemaining;
   
   public GameProperties() {
      this.movesRemaining = new SimpleObjectProperty<>( 0 );
      this.increaseMoves( 25 );
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

}//End Class
