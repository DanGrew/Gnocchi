package uk.dangrew.gnocchi.game.type.colours;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import uk.dangrew.gnocchi.game.type.GameInformation;
import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;
import uk.dangrew.gnocchi.grid.square.SquareType;
import uk.dangrew.kode.observable.PrivatelyModifiableObservableMapImpl;

public class ColoursGtProperties extends GameProperties {

   static final int DEFAULT_PRIMARY = 100;
   static final int DEFAULT_SECONDARY = 100;
   
   private final ColoursGtPropertyInformationProvider infomationProvider;
   private final ObservableMap< SquareType, Integer > modifiableTypesRemaining;
   private final ObservableMap< SquareType, Integer > publicColoursRemaining;
   
   public ColoursGtProperties() {
      this( new ColoursGtPropertyInformationProvider() );
   }//End Constructor
   
   ColoursGtProperties( ColoursGtPropertyInformationProvider informationProvider ) {
      this.infomationProvider = informationProvider;
      this.modifiableTypesRemaining = FXCollections.observableMap( new LinkedHashMap<>() );
      this.publicColoursRemaining = new PrivatelyModifiableObservableMapImpl<>( modifiableTypesRemaining );
      this.reset();
   }//End Constructor
   
   @Override public ColoursGtProperties withMoves( int moves ) {
      super.withMoves( moves );
      return this;
   }//End Method
   
   public ColoursGtProperties withTarget( SquareType type, int target ) {
      increaseRemaining( type, target );
      return this;
   }//End Method
   
   public List< SquareType > targetTypes() {
      return new ArrayList<>( modifiableTypesRemaining.keySet() );
   }//End Method

   public int remainingFor( SquareType type ) {
      if ( modifiableTypesRemaining.get( type ) == null ) {
         return 0;
      }
      return modifiableTypesRemaining.get( type );
   }//End Method

   public void increaseRemaining( SquareType type, int count ) {
      Integer existingProgress = modifiableTypesRemaining.get( type );
      if ( existingProgress == null ) {
         existingProgress = 0;
      }
      modifiableTypesRemaining.put( type, existingProgress + count );
   }//End Method
   
   public void decreaseRemaining( SquareType type, int count ) {
      Integer existingProgress = modifiableTypesRemaining.get( type );
      if ( existingProgress == null ) {
         return;
      }
      existingProgress -= count;
      existingProgress = Math.max( existingProgress, 0 );
      modifiableTypesRemaining.put( type, existingProgress );
   }//End Method

   public ObservableMap< SquareType, Integer > remainingColours() {
      return publicColoursRemaining;
   }//End Method
   
   @Override public void reset() {
      super.reset();
      if ( this.modifiableTypesRemaining != null ) {
         this.modifiableTypesRemaining.clear();
         this.increaseRemaining( SquareRegularType.Primary, DEFAULT_PRIMARY );
         this.increaseRemaining( SquareRegularType.Secondary, DEFAULT_SECONDARY );
      }
   }//End Method

   @Override public void configureInformation( GameInformation information ) {
      infomationProvider.configure( this, information );
   }//End Method

}//End Class
