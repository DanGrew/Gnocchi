package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.type.colours.ColoursGtFeeder;
import uk.dangrew.gnocchi.grid.controls.Filler;
import uk.dangrew.gnocchi.grid.model.GridBuilder;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareObstacleType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class GridModelTest {
   
   private static final int COLOURS = 4;
   private static final int WIDTH = 10;
   private static final int HEIGHT = 20;

   private GridBuilder builder;
   private GridModel systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      builder = new GridBuilder()
               .withNumberOfColours( COLOURS )
               .withWidth( WIDTH )
               .withHeight( HEIGHT );
      systemUnderTest = new GridModel( builder );
   }//End Method

   @Test public void shouldProvideDimensions(){
      assertThat( systemUnderTest.colourVariation(), is( COLOURS ) );
      assertThat( systemUnderTest.width(), is( WIDTH ) );
      assertThat( systemUnderTest.height(), is( HEIGHT ) );
   }//End Method
   
   @Test public void shouldBeEmptyInitially() {
      for ( int i = 0; i < WIDTH; i++ ) {
         for ( int j = 0; j < HEIGHT; j++ ) {
            assertThat( systemUnderTest.at( i, j ), is( nullValue() ) );
         }   
      }
   }//End Method
   
   @Test public void shouldSupportRequestsOutsideBoundaries() {
      assertThat( systemUnderTest.at( 34958734, 12373 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( -34958734, -12373 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( -34958734, 12373 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( 34958734, -12373 ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldPutObjectsInGrid(){
      assertThat( systemUnderTest.at( 5, 6 ), is( nullValue() ) );
      Square object = new Square( SquareRegularType.Secondary );
      systemUnderTest.set( object, 5, 6 );
      assertThat( systemUnderTest.at( 5, 6 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 5, 6 ) ) );
   }//End Method
   
   @Test public void shouldMoveObjectWhenPositionSet(){
      Square object = new Square( SquareRegularType.Secondary );
      systemUnderTest.set( object, 4, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 4, 5 ) ) );
      assertThat( object.position(), is( new GridPosition( 4, 5 ) ) );
      
      systemUnderTest.set( object, 3, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( nullValue() ) );
      assertThat( systemUnderTest.at( 3, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 3, 5 ) ) );
      assertThat( object.position(), is( new GridPosition( 3, 5 ) ) );
   }//End Method
   
   @Test public void shouldNotMoveObjectWhenSetToNull(){
      Square object = new Square( SquareRegularType.Secondary );
      systemUnderTest.set( object, 4, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 4, 5 ) ) );
      assertThat( object.position(), is( new GridPosition( 4, 5 ) ) );
      
      systemUnderTest.set( null, 4, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( nullValue() ) );
      assertThat( systemUnderTest.of( object ), is( nullValue() ) );
      assertThat( object.position(), is( new GridPosition( 4, 5 ) ) );
   }//End Method
   
   @Test public void shouldRemoveObject(){
      Square object = new Square( SquareRegularType.Secondary );
      systemUnderTest.set( object, 4, 5 );
      assertThat( systemUnderTest.at( 4, 5 ), is( object ) );
      assertThat( systemUnderTest.of( object ), is( new GridPosition( 4, 5 ) ) );
      
      systemUnderTest.remove( object );
      assertThat( systemUnderTest.at( 4, 5 ), is( nullValue() ) );
      assertThat( systemUnderTest.of( object ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldIgnoreRemoveOnObjectOutside(){
      Square object = new Square( SquareRegularType.Secondary );
      systemUnderTest.remove( object );
   }//End Method
   
   @Test public void shouldDetermineEmptyLocation(){
      Square object = new Square( SquareRegularType.Secondary );
      
      assertThat( systemUnderTest.isEmpty( 5, 6 ), is( true ) );
      systemUnderTest.set( object, 5, 6 );
      assertThat( systemUnderTest.isEmpty( 5, 6 ), is( false ) );
      
      systemUnderTest.set( object, 3, 2 );
      assertThat( systemUnderTest.isEmpty( 5, 6 ), is( true ) );
      assertThat( systemUnderTest.isEmpty( 3, 2 ), is( false ) );
   }//End Method
   
   @Test public void shouldProvideIndexConvenience(){
      assertThat( systemUnderTest.lastWidthIndex(), is( systemUnderTest.width() - 1 ) );
      assertThat( systemUnderTest.lastHeightIndex(), is( systemUnderTest.height() - 1 ) );
   }//End Method
   
   @Test public void shouldProvideIterators(){
      fail();
   }//End Method

   @Test public void shouldProvideSnapshot(){
      fail();
   }//End Method
   
   @Test public void shouldNotPlaceSpecifics(){
      builder.withSpecific( new GridPosition( 4, 9 ), SquareObstacleType.FixedIndestructible );
      systemUnderTest = new GridModel( builder );
      assertThat( systemUnderTest.at( 4, 9 ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldResetSpecifics(){
      builder.withSpecific( new GridPosition( 4, 9 ), SquareObstacleType.FixedIndestructible );
      systemUnderTest = new GridModel( builder );
      new Filler( systemUnderTest, new ColoursGtFeeder( systemUnderTest ) ).fill();
      assertThat( systemUnderTest.at( 4, 9 ).type(), is( not( SquareObstacleType.FixedIndestructible ) ) );
      
      systemUnderTest.reset();
      for ( int w = 0; w < systemUnderTest.width(); w++ ) {
         for ( int h = 0; h < systemUnderTest.height(); h++ ) {
            if ( w == 4 && h == 9 ) {
               continue;
            }
            assertThat( systemUnderTest.isEmpty( w, h ), is( true ) );
         }
      }
      assertThat( systemUnderTest.at( 4, 9 ).type(), is( SquareObstacleType.FixedIndestructible ) );
   }//End Method
}//End Method
