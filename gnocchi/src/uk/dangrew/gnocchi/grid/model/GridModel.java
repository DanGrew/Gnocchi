package uk.dangrew.gnocchi.grid.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.square.Square;

public class GridModel {

   private final GridBuilder builder;
   private final Map< Square, GridPosition > objects;
   private final Map< GridPosition, Square > positions;
   
   public GridModel( GridBuilder builder ) {
      this.builder = builder;
      this.objects = new HashMap<>();
      this.positions = new HashMap<>();
   }//End Constructor
   
   public GridModel( int colourVariation, int width, int height ) {
      this( 
               new GridBuilder()
                  .withNumberOfColours( colourVariation )
                  .withWidth( width )
                  .withHeight( height )
      );
   }//End Constructor
   
   public Square at( int w, int h ) {
      if ( w < 0 || w >= width() ) {
         return null;
      }
      if ( h < 0 || h >= height() ) {
         return null;
      }
      return positions.get( new GridPosition( w, h ) );
   }//End Method
   
   public GridPosition of( Square object ) {
      return objects.get( object );
   }//End Method
   
   public void set( Square object, int w, int h ) {
      GridPosition currentPosition = objects.get( object );
      if ( currentPosition != null ) {
         positions.put( currentPosition, null );
      }
      
      GridPosition position = new GridPosition( w, h );
      Square existing = positions.remove( position );
      objects.remove( existing );
      
      if ( object == null ) {
         positions.put( position, null );
      } else {
         object.moveTo( position );
         objects.put( object, position );
         positions.put( position, object );
      }
   }//End Method
   
   public boolean isEmpty( int w, int h ) {
      return at( w, h ) == null;
   }//End Method
   
   public void remove( Square object ) {
      GridPosition position = objects.get( object );
      if ( position != null ) {
         positions.remove( position );
      }
      objects.remove( object );
   }//End Method
   
   public void reset() {
      Collection< Square > contents = new ArrayList<>( objects.keySet() );
      contents.forEach( this::remove );
      
      builder.specifics().forEach( p -> {
         Square s = new Square( p.getKey(), p.getValue() );
         set( s, p.getKey().w, p.getKey().h );
      } );
   }//End Method
   
   public int colourVariation() {
      return builder.colourVariation();
   }//End Method
   
   public int width() {
      return builder.width();
   }//End Method

   public int height() {
      return builder.height();
   }//End Method
   
   public int lastWidthIndex(){
      return width() - 1;
   }//End Method
   
   public int lastHeightIndex(){
      return height() - 1;
   }//End Method
   
   public Iterator< GridEntry > bottomUpIterator(){
      return new BottomUpGridIterator( this );
   }//End Method
   
   public Iterator< GridEntry > rowIterator( int h ) {
      return new RowGridIterator( this, h );
   }//End Method
   
   public Iterator< GridEntry > columnIterator( int w ) {
      return new ColumnGridIterator( this, w );
   }//End Method

   public GridSnapshot snapshot() {
      return new GridSnapshot( this );
   }//End Method

}//End Class
