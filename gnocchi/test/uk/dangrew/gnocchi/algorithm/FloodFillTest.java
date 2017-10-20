package uk.dangrew.gnocchi.algorithm;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;

public class FloodFillTest {

   private Square s1;
   private Square s2;
   private Square s3;
   private Square s4;
   private Square s5;
   private Square s6;
   private Square s7;
   private Square s8;
   private Square s9;
   
   private GridModel grid;
   private FloodFill systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      s1 = new Square( SquareRegularType.Secondary );
      s2 = new Square( SquareRegularType.Secondary );
      s3 = new Square( SquareRegularType.Secondary );
      s4 = new Square( SquareRegularType.Secondary );
      s5 = new Square( SquareRegularType.Secondary );
      s6 = new Square( SquareRegularType.Secondary );
      s7 = new Square( SquareRegularType.Secondary );
      s8 = new Square( SquareRegularType.Secondary );
      s9 = new Square( SquareRegularType.Secondary );
      
      grid = new GridModel( 4, 10, 10 );
      systemUnderTest = new FloodFill();
   }//End Method

   @Test public void shouldMatchImmediatelyConnected() {
      for ( int w = 0; w < grid.width(); w++ ) {
         for ( int h = 0; h < grid.width(); h++ ) {
            grid.set( new Square( SquareRegularType.Primary ), w, h );
         }   
      }
      
      grid.set( s1, 5, 5 );
      
      grid.set( s2, 6, 5 );
      grid.set( s3, 4, 5 );
      grid.set( s4, 5, 6 );
      grid.set( s5, 5, 4 );
      
      assertThat( systemUnderTest.flood( grid, 5, 5 ), containsInAnyOrder( 
               s1, s2, s3, s4, s5 
      ) );
   }//End Method
   
   @Test public void shouldNotMatchNotConnected() {
      for ( int w = 0; w < grid.width(); w++ ) {
         for ( int h = 0; h < grid.width(); h++ ) {
            grid.set( new Square( SquareRegularType.Primary ), w, h );
         }   
      }
      
      grid.set( s1, 5, 5 );
      
      grid.set( s2, 7, 5 );
      grid.set( s3, 3, 5 );
      grid.set( s4, 5, 7 );
      grid.set( s5, 5, 3 );
      
      assertThat( systemUnderTest.flood( grid, 5, 5 ), containsInAnyOrder( 
               s1 
      ) );
   }//End Method
   
   @Test public void shouldMatchCrossShape() {
      for ( int w = 0; w < grid.width(); w++ ) {
         for ( int h = 0; h < grid.width(); h++ ) {
            grid.set( new Square( SquareRegularType.Primary ), w, h );
         }   
      }
      
      grid.set( s1, 5, 5 );
      
      grid.set( s2, 6, 5 );
      grid.set( s3, 7, 5 );
      grid.set( s4, 4, 5 );
      grid.set( s5, 3, 5 );
      grid.set( s6, 5, 6 );
      grid.set( s7, 5, 7 );
      grid.set( s8, 5, 4 );
      grid.set( s9, 5, 3 );
      
      assertThat( systemUnderTest.flood( grid, 5, 5 ), containsInAnyOrder( 
               s1, s2, s3, s4, s5, s6, s7, s8, s9 
      ) );
   }//End Method
   
   @Test public void shouldMatchPattern() {
      for ( int w = 0; w < grid.width(); w++ ) {
         for ( int h = 0; h < grid.width(); h++ ) {
            grid.set( new Square( SquareRegularType.Primary ), w, h );
         }   
      }
      
      grid.set( s1, 0, 0 );
      grid.set( s2, 1, 0 );
      grid.set( s3, 2, 0 );
      grid.set( s4, 2, 1 );
      grid.set( s5, 2, 2 );
      grid.set( s6, 2, 3 );
      grid.set( s7, 2, 4 );
      grid.set( s8, 3, 4 );
      grid.set( s9, 3, 5 );
      
      assertThat( systemUnderTest.flood( grid, 0, 0 ), containsInAnyOrder( 
               s1, s2, s3, s4, s5, s6, s7, s8, s9 
      ) );
   }//End Method
   
   @Test public void shouldIgnoreNullEntries(){
      fail();
   }//End Method
   
   @Test public void shouldIgnoreNullFlood(){
      fail();
   }//End Method
   
   @Test public void shouldClearMatchesIfLessThanLimit(){
      for ( int w = 0; w < grid.width(); w++ ) {
         for ( int h = 0; h < grid.width(); h++ ) {
            grid.set( new Square( SquareRegularType.Primary ), w, h );
         }   
      }
      
      grid.set( s1, 5, 5 );
      assertThat( systemUnderTest.match( grid, 5, 5 ), is( empty() ) );
      
      grid.set( s2, 6, 5 );
      assertThat( systemUnderTest.match( grid, 5, 5 ), is( empty() ) );
      grid.set( s3, 4, 5 );
      assertThat( systemUnderTest.match( grid, 5, 5 ), containsInAnyOrder( 
               s1, s2, s3
      ) );
      grid.set( s4, 5, 6 );
      assertThat( systemUnderTest.match( grid, 5, 5 ), containsInAnyOrder( 
               s1, s2, s3, s4
      ) );
      grid.set( s5, 5, 4 );
      assertThat( systemUnderTest.flood( grid, 5, 5 ), containsInAnyOrder( 
               s1, s2, s3, s4, s5 
      ) );
   }//End Method

}//End Class
