package uk.dangrew.gnocchi.grid.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GridModel {

   private final int width;
   private final int height;
   private final Map< Object, GridPosition > objects;
   private final Map< GridPosition, Object > positions;
   
   public GridModel( int width, int height ) {
      this.width = width;
      this.height = height;
      
      this.objects = new HashMap<>();
      this.positions = new HashMap<>();
   }//End Constructor
   
   public Object at( int w, int h ) {
      if ( w < 0 || w >= width ) {
         return null;
      }
      if ( h < 0 || h >= height ) {
         return null;
      }
      return positions.get( new GridPosition( w, h ) );
   }//End Method
   
   public GridPosition of( Object object ) {
      return objects.get( object );
   }//End Method
   
   public void set( Object object, int w, int h ) {
      GridPosition currentPosition = objects.get( object );
      if ( currentPosition != null ) {
         positions.put( currentPosition, null );
      }
      
      GridPosition position = new GridPosition( w, h );
      objects.put( object, position );
      positions.put( position, object );
   }//End Method
   
   public boolean isEmpty( int w, int h ) {
      return at( w, h ) == null;
   }//End Method
   
   public void remove( Object object ) {
      GridPosition position = objects.get( object );
      if ( position != null ) {
         positions.remove( position );
      }
      objects.remove( object );
   }//End Method
   
   public int width() {
      return width;
   }//End Method

   public int height() {
      return height;
   }//End Method
   
   public int lastWidthIndex(){
      return width - 1;
   }//End Method
   
   public int lastHeightIndex(){
      return height - 1;
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
