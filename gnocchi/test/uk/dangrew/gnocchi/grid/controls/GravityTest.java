package uk.dangrew.gnocchi.grid.controls;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.game.mechanics.Feeder;
import uk.dangrew.gnocchi.game.type.colours.ColoursGtFeeder;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareObstacleType;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class GravityTest {

   private GridModel grid;
   private Filler filler;
   private Feeder coloursGtFeeder;
   private Gravity systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new GridModel( 4, 10, 10 );
      coloursGtFeeder = new ColoursGtFeeder( grid );
      filler = new Filler( grid, coloursGtFeeder );
      systemUnderTest = new Gravity( grid );
   }//End Method

   @Test public void shouldIgnorNothingToPullDown(){
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0 );
   }//End Method
   
   @Test public void shouldPullSingleDown(){
      coloursGtFeeder.feed( 0 );
      assertThatObjectsInColumn( 0, 9 );
      
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 0 );
   }//End Method
   
   @Test public void shouldPullMultipleDown(){
      coloursGtFeeder.feed( 0 );
      grid.set( new Square( SquareRegularType.Primary ), 0, 8 );
      assertThatObjectsInColumn( 0, 9, 8 );
      
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 0, 1 );
   }//End Method
   
   @Test public void shouldPullMultipleDownToFillMultipleGaps(){
      for ( int h = 0; h < grid.height(); h++ ) {
         grid.set( new Square( SquareRegularType.Primary ), 0, h );
      }
      
      grid.set( null, 0, 3 );
      grid.set( null, 0, 7 );
      
      systemUnderTest.pullDown( 0 );
      
      assertThatObjectsInColumn( 0, 0, 1, 2, 3, 4, 5, 6, 7 );
   }//End Method
   
   @Test public void shouldNotMoveUnmoveable(){
      Square unmoveable = Square.randomSquare();
      unmoveable.setType( SquareObstacleType.FixedIndestructible );
      grid.set( unmoveable, 0, 4 );
      assertThatObjectsInColumn( 0, 4 );
      
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 4 );
   }//End Method
   
   @Test public void shouldNotMoveThroughUnmoveable(){
      Square unmoveable = Square.randomSquare();
      unmoveable.setType( SquareObstacleType.FixedIndestructible );
      grid.set( unmoveable, 0, 4 );
      
      coloursGtFeeder.feed( 0 );
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 4, 5 );
   }//End Method
   
   @Test public void shouldMoveBelowUnmoveableAndAboveUnmoveable(){
      Square unmoveable = Square.randomSquare();
      unmoveable.setType( SquareObstacleType.FixedIndestructible );
      grid.set( unmoveable, 0, 4 );
      
      Square unmoveable2 = Square.randomSquare();
      unmoveable2.setType( SquareObstacleType.FixedIndestructible );
      grid.set( unmoveable2, 0, 7 );
      
      grid.set( Square.randomSquare(), 0, 3 );
      grid.set( Square.randomSquare(), 0, 6 );
      grid.set( Square.randomSquare(), 0, 9 );
      
      systemUnderTest.pullDown( 0 );
      assertThatObjectsInColumn( 0, 0, 4, 5, 7, 8 );
   }//End Method
   
   private void assertThatObjectsInColumn( int column, Integer... rows ) {
      List< Integer > expectedPresentRows = Arrays.asList( rows );
      for ( int h = 0; h < grid.height(); h++ ) {
         if ( expectedPresentRows.contains( h ) ) {
            assertThat( grid.at( column, h ), is( notNullValue() ) );
         } else {
            assertThat( grid.at( column, h ), is( nullValue() ) );
         }
      }
   }//End Method
   
   @Test public void shouldSlideOnlyOptionFromLeft(){
      filler.fill();
      
      for ( int h = 0; h < grid.height(); h++ ){
         grid.set( null, 3, h );
         grid.set( null, 4, h );
      }
      
      grid.set( null, 2, 9 );
      grid.set( null, 2, 8 );
      grid.set( null, 2, 7 );
      
      Square firstExpectedSlide = grid.at( 2, 1 );
      Square aboveFirstExpectedSlide = grid.at( 2, 2 );
      Square secondExpectedSlide = grid.at( 2, 3 );
      Square aboveSecondExpectedSlide = grid.at( 2, 4 );
      Square thirdExpectedSlide = grid.at( 2, 5 );
      Square aboveThirdExpectedSlide = grid.at( 2, 6 );
      systemUnderTest.slideForGaps( 3 );
      
      assertThat( grid.at( 3, 0 ), is( firstExpectedSlide ) );
      assertThat( grid.at( 2, 1 ), is( aboveFirstExpectedSlide ) );
      assertThat( grid.at( 3, 1 ), is( secondExpectedSlide ) );
      assertThat( grid.at( 2, 2 ), is( aboveSecondExpectedSlide ) );
      assertThat( grid.at( 3, 2 ), is( thirdExpectedSlide ) );
      assertThat( grid.at( 2, 3 ), is( aboveThirdExpectedSlide ) );
   }//End Method
   
   @Test public void shouldNotSlideFromLeftForFirstColumn(){
      fail();
   }//End Method
   
   @Test public void shouldNotSlideFromLeftForTopRow(){
      fail();
   }//End Method
   
   @Test public void shouldSlideMoveableOnlyFromLeft(){
      fail();
   }//End Method
   
   @Test public void shouldSlideOnlyOptionFromRight(){
      filler.fill();
      
      for ( int h = 0; h < grid.height(); h++ ){
         grid.set( null, 3, h );
         grid.set( null, 4, h );
      }
      
      Square firstExpectedSlide = grid.at( 5, 1 );
      Square aboveFirstExpectedSlide = grid.at( 5, 2 );
      Square secondExpectedSlide = grid.at( 5, 3 );
      Square aboveSecondExpectedSlide = grid.at( 5, 4 );
      Square thirdExpectedSlide = grid.at( 5, 5 );
      Square aboveThirdExpectedSlide = grid.at( 5, 6 );
      systemUnderTest.slideForGaps( 4 );
      
      assertThat( grid.at( 4, 0 ), is( firstExpectedSlide ) );
      assertThat( grid.at( 5, 1 ), is( aboveFirstExpectedSlide ) );
      assertThat( grid.at( 4, 1 ), is( secondExpectedSlide ) );
      assertThat( grid.at( 5, 2 ), is( aboveSecondExpectedSlide ) );
      assertThat( grid.at( 4, 2 ), is( thirdExpectedSlide ) );
      assertThat( grid.at( 5, 3 ), is( aboveThirdExpectedSlide ) );
   }//End Method

}//End Class
