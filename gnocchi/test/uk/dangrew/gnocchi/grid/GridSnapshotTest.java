package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;
import uk.dangrew.gnocchi.grid.square.Square;

public class GridSnapshotTest {

   private Grid grid;
   private GridSnapshot systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      grid = new Grid( 10, 10 );
   }//End Method

   @Test public void shouldProvideSnapshotOfItems() {
      systemUnderTest = grid.snapshot();
      
      assertThatSnapshotMatchesGrid();
      grid.fill();
      
      for ( int w = 0; w < grid.model().width(); w++ ) {
         for ( int h = 0; h < grid.model().height(); h++ ) {
            assertThatPositionMatches( w, h, null );
         }   
      }
   }//End Method
   
   @Test public void shouldRepresentIndividualChanges() {
      grid.fill();
      systemUnderTest = grid.snapshot();
      grid.model().set( new Square( Color.RED ), 3, 4 );
      
      for ( int w = 0; w < grid.model().width(); w++ ) {
         for ( int h = 0; h < grid.model().height(); h++ ) {
            
            if ( w == 3 && h == 4 ) {
               assertThat( systemUnderTest.at( w, h ), is( not( grid.model().at( w, h ) ) ) );   
            } else {
               assertThatPositionMatches( w, h, grid.model().at( w, h ) );
            }
         }   
      }
   }//End Method
   
   private void assertThatSnapshotMatchesGrid(){
      for ( int w = 0; w < grid.model().width(); w++ ) {
         for ( int h = 0; h < grid.model().height(); h++ ) {
            assertThat( systemUnderTest.at( w, h ), is( grid.model().at( w, h ) ) );
         }   
      } 
   }//End Method
   
   private void assertThatPositionMatches( int w, int h, Object expected ){
      assertThat( systemUnderTest.at( w, h ), is( expected ) );
      if ( expected != null ) {
         assertThat( systemUnderTest.of( expected ), is( new GridPosition( w, h ) ) );
      }
   }//End Method

}//End Class
