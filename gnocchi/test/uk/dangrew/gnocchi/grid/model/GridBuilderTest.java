package uk.dangrew.gnocchi.grid.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import javafx.util.Pair;
import uk.dangrew.gnocchi.grid.square.SquareObstacleType;
import uk.dangrew.gnocchi.grid.square.SquareBonusType;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class GridBuilderTest {

   private GridBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new GridBuilder();
   }//End Method
   
   @Test public void shouldConfigureColours() {
      assertThat( systemUnderTest.colourVariation(), is( GridBuilder.DEFAULT_COLOUR_VARIATION ) );
      assertThat( systemUnderTest.withNumberOfColours( 6 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.colourVariation(), is( 6 ) );
   }//End Method
   
   @Test public void shouldConfigureWidth() {
      assertThat( systemUnderTest.width(), is( GridBuilder.DEFAULT_WIDTH ) );
      assertThat( systemUnderTest.withWidth( 18 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.width(), is( 18 ) );
   }//End Method   
   
   @Test public void shouldConfigureHeight() {
      assertThat( systemUnderTest.height(), is( GridBuilder.DEFAULT_HEIGHT ) );
      assertThat( systemUnderTest.withHeight( 18 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.height(), is( 18 ) );
   }//End Method 
   
   @Test public void shouldProvideSpecifics() {
      assertThat( systemUnderTest.specifics(), is( new ArrayList<>() ) );
      
      GridPosition position = new GridPosition( 4, 7 );
      SquareType type = SquareObstacleType.FixedIndestructible;
      assertThat( systemUnderTest.withSpecific( position, type ), is( systemUnderTest ) );
      
      assertThat( systemUnderTest.specifics(), is( Arrays.asList( new Pair<>( position, type ) ) ) );
      
      GridPosition position2 = new GridPosition( 3, 2 );
      SquareType type2 = SquareBonusType.BombBomb;
      systemUnderTest.withSpecific( position2, type2 );
      
      assertThat( systemUnderTest.specifics(), is( Arrays.asList( 
               new Pair<>( position, type ),
               new Pair<>( position2, type2 )
      ) ) );
   }//End Method

}//End Class
