package uk.dangrew.gnocchi.game.type.colours;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.kode.observable.FunctionMapChangeListenerImpl;
import uk.dangrew.kode.observable.PrivatelyModifiableObservableMapImpl;

public class ColoursGtPropertiesTest {

   private ColoursGtProperties systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new ColoursGtProperties();
      systemUnderTest.decreaseRemaining( Color.RED, 100 );
      systemUnderTest.decreaseRemaining( Color.LIGHTSKYBLUE, 100 );
      systemUnderTest.moveUsed( 25 );
   }//End Method

   @Test public void shouldProvideColourTargets() {
      assertThat( systemUnderTest.targetColours(), is( Arrays.asList( Color.LIGHTSKYBLUE, Color.RED ) ) );
      
      systemUnderTest.increaseRemaining( Color.RED, 10 );
      systemUnderTest.increaseRemaining( Color.BLUE, 12 );
      systemUnderTest.increaseRemaining( Color.GREEN, 8 );
      
      assertThat( systemUnderTest.targetColours(), is( Arrays.asList( Color.LIGHTSKYBLUE, Color.RED, Color.BLUE, Color.GREEN ) ) );
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 10 ) );
      assertThat( systemUnderTest.remainingFor( Color.BLUE ), is( 12 ) );
      assertThat( systemUnderTest.remainingFor( Color.GREEN ), is( 8 ) );
   }//End Method
   
   @Test public void shouldProvideColourProgress(){
      systemUnderTest.increaseRemaining( Color.RED, 10 );
      systemUnderTest.increaseRemaining( Color.BLUE, 12 );
      systemUnderTest.increaseRemaining( Color.GREEN, 8 );
      
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 10 ) );
      assertThat( systemUnderTest.remainingFor( Color.BLUE ), is( 12 ) );
      assertThat( systemUnderTest.remainingFor( Color.GREEN ), is( 8 ) );
      assertThat( systemUnderTest.remainingFor( Color.AQUA ), is( 0 ) );
      
      systemUnderTest.increaseRemaining( Color.RED, 5 );
      systemUnderTest.increaseRemaining( Color.BLUE, 10 );
      systemUnderTest.increaseRemaining( Color.GREEN, 3 );
      systemUnderTest.increaseRemaining( Color.AQUA, 89 );
      
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 15 ) );
      assertThat( systemUnderTest.remainingFor( Color.BLUE ), is( 22 ) );
      assertThat( systemUnderTest.remainingFor( Color.GREEN ), is( 11 ) );
      assertThat( systemUnderTest.remainingFor( Color.AQUA ), is( 89 ) );
   }//End Method
   
   @Test public void shouldProvideObservableProgress(){
      assertThat( systemUnderTest.remainingColours(), is( instanceOf( PrivatelyModifiableObservableMapImpl.class ) ) );
      
      systemUnderTest.increaseRemaining( Color.RED, 10 );
      systemUnderTest.increaseRemaining( Color.BLUE, 12 );
      systemUnderTest.increaseRemaining( Color.GREEN, 8 );
      
      BiConsumer< Color, Integer > added = mock( BiConsumer.class );
      BiConsumer< Color, Integer > removed = mock( BiConsumer.class );
      FunctionMapChangeListenerImpl< Color, Integer > listener = new FunctionMapChangeListenerImpl<>( 
               systemUnderTest.remainingColours(), added, removed 
      );
      systemUnderTest.remainingColours().addListener( listener );
      
      systemUnderTest.increaseRemaining( Color.RED, 5 );
      verify( added ).accept( Color.RED, 15 );
      systemUnderTest.increaseRemaining( Color.RED, 4 );
      verify( added ).accept( Color.RED, 19 );
      
      systemUnderTest.increaseRemaining( Color.GREEN, 20 );
      verify( added ).accept( Color.GREEN, 28 );
   }//End Method
   
   @Test public void shouldProvideNumberOfMoves(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      systemUnderTest.increaseMoves( 20 );
      assertThat( systemUnderTest.movesRemaining().get(), is( 20 ) );
      
      systemUnderTest.moveUsed();
      assertThat( systemUnderTest.movesRemaining().get(), is( 19 ) );
      
      systemUnderTest.moveUsed( 10 );
      assertThat( systemUnderTest.movesRemaining().get(), is( 9 ) );
   }//End Method
   
   @Test public void shouldDecreaseRemainingColours(){
      systemUnderTest.increaseRemaining( Color.RED, 10 );
      systemUnderTest.increaseRemaining( Color.BLUE, 12 );
      systemUnderTest.increaseRemaining( Color.GREEN, 8 );
      
      systemUnderTest.decreaseRemaining( Color.RED, 5 );
      systemUnderTest.decreaseRemaining( Color.BLUE, 100 );
      systemUnderTest.decreaseRemaining( Color.AQUA, 7 );
      
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 5 ) );
      assertThat( systemUnderTest.remainingFor( Color.BLUE ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( Color.GREEN ), is( 8 ) );
      assertThat( systemUnderTest.remainingFor( Color.AQUA ), is( 0 ) );
   }//End Method
   
   @Test public void shouldProvideBuilderForProperties(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      assertThat( systemUnderTest.withMoves( 34 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.movesRemaining().get(), is( 34 ) );
      
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 0 ) );
      assertThat( systemUnderTest.withTarget( Color.RED, 23 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 23 ) );
   }//End Method
   
   @Test public void shouldResetProperties(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( Color.BLUE ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( Color.GREEN ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( Color.AQUA ), is( 0 ) );
      
      systemUnderTest.reset();
      assertThat( systemUnderTest.movesRemaining().get(), is( GameProperties.DEFAULT_MOVES ) );
      assertThat( systemUnderTest.remainingFor( Color.RED ), is( ColoursGtProperties.DEFAULT_RED ) );
      assertThat( systemUnderTest.remainingFor( Color.LIGHTSKYBLUE ), is( ColoursGtProperties.DEFAULT_LIGHTSKYBLUE ) );
      assertThat( systemUnderTest.remainingFor( Color.GREEN ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( Color.AQUA ), is( 0 ) );
   }//End Method

}//End Class
