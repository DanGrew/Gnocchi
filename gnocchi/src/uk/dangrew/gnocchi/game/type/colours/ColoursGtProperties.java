package uk.dangrew.gnocchi.game.type.colours;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import uk.dangrew.kode.observable.PrivatelyModifiableObservableMapImpl;

public class ColoursGtProperties {

   private final ObservableMap< Color, Integer > modifiableColoursRemaining;
   private final ObservableMap< Color, Integer > publicColoursRemaining;
   
   private final ObjectProperty< Integer > movesRemaining;
   
   public ColoursGtProperties() {
      this.modifiableColoursRemaining = FXCollections.observableHashMap();
      this.publicColoursRemaining = new PrivatelyModifiableObservableMapImpl<>( modifiableColoursRemaining );
      
      this.movesRemaining = new SimpleObjectProperty<>( 0 );
      
      this.increaseRemaining( Color.RED, 100 );
      this.increaseRemaining( Color.LIGHTSKYBLUE, 100 );
      this.increaseMoves( 25 );
   }//End Constructor
   
   public ColoursGtProperties withMoves( int moves ) {
      movesRemaining.set( moves );
      return this;
   }//End Method
   
   public ColoursGtProperties withTarget( Color colour, int target ) {
      increaseRemaining( colour, target );
      return this;
   }//End Method
   
   public List< Color > targetColours() {
      return new ArrayList<>( modifiableColoursRemaining.keySet() );
   }//End Method

   public int remainingFor( Color colour ) {
      if ( modifiableColoursRemaining.get( colour ) == null ) {
         return 0;
      }
      return modifiableColoursRemaining.get( colour );
   }//End Method

   public void increaseRemaining( Color colour, int count ) {
      Integer existingProgress = modifiableColoursRemaining.get( colour );
      if ( existingProgress == null ) {
         existingProgress = 0;
      }
      modifiableColoursRemaining.put( colour, existingProgress + count );
   }//End Method
   
   public void decreaseRemaining( Color colour, int count ) {
      Integer existingProgress = modifiableColoursRemaining.get( colour );
      if ( existingProgress == null ) {
         return;
      }
      existingProgress -= count;
      existingProgress = Math.max( existingProgress, 0 );
      modifiableColoursRemaining.put( colour, existingProgress );
   }//End Method

   public ObservableMap< Color, Integer > remainingColours() {
      return publicColoursRemaining;
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
