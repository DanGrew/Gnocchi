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

import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;
import uk.dangrew.gnocchi.grid.square.SquareType;
import uk.dangrew.kode.observable.FunctionMapChangeListenerImpl;
import uk.dangrew.kode.observable.PrivatelyModifiableObservableMapImpl;

public class ColoursGtPropertiesTest {

   private ColoursGtProperties systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new ColoursGtProperties();
      systemUnderTest.decreaseRemaining( SquareRegularType.Primary, 100 );
      systemUnderTest.decreaseRemaining( SquareRegularType.Secondary, 100 );
      systemUnderTest.moveUsed( 25 );
   }//End Method

   @Test public void shouldProvideColourTargets() {
      assertThat( systemUnderTest.targetTypes(), is( Arrays.asList( SquareRegularType.Primary, SquareRegularType.Secondary ) ) );
      
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 10 );
      systemUnderTest.increaseRemaining( SquareRegularType.Secondary, 12 );
      systemUnderTest.increaseRemaining( SquareRegularType.Tertiary, 8 );
      
      assertThat( systemUnderTest.targetTypes(), is( Arrays.asList( SquareRegularType.Primary, SquareRegularType.Secondary, SquareRegularType.Tertiary ) ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 10 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Secondary ), is( 12 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Tertiary ), is( 8 ) );
   }//End Method
   
   @Test public void shouldProvideColourProgress(){
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 10 );
      systemUnderTest.increaseRemaining( SquareRegularType.Secondary, 12 );
      systemUnderTest.increaseRemaining( SquareRegularType.Tertiary, 8 );
      
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 10 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Secondary ), is( 12 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Tertiary ), is( 8 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Quaternary ), is( 0 ) );
      
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 5 );
      systemUnderTest.increaseRemaining( SquareRegularType.Secondary, 10 );
      systemUnderTest.increaseRemaining( SquareRegularType.Tertiary, 3 );
      systemUnderTest.increaseRemaining( SquareRegularType.Quaternary, 89 );
      
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 15 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Secondary ), is( 22 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Tertiary ), is( 11 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Quaternary ), is( 89 ) );
   }//End Method
   
   @Test public void shouldProvideObservableProgress(){
      assertThat( systemUnderTest.remainingColours(), is( instanceOf( PrivatelyModifiableObservableMapImpl.class ) ) );
      
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 10 );
      systemUnderTest.increaseRemaining( SquareRegularType.Secondary, 12 );
      systemUnderTest.increaseRemaining( SquareRegularType.Tertiary, 8 );
      
      BiConsumer< SquareType, Integer > added = mock( BiConsumer.class );
      BiConsumer< SquareType, Integer > removed = mock( BiConsumer.class );
      FunctionMapChangeListenerImpl< SquareType, Integer > listener = new FunctionMapChangeListenerImpl<>( 
               systemUnderTest.remainingColours(), added, removed 
      );
      systemUnderTest.remainingColours().addListener( listener );
      
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 5 );
      verify( added ).accept( SquareRegularType.Primary, 15 );
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 4 );
      verify( added ).accept( SquareRegularType.Primary, 19 );
      
      systemUnderTest.increaseRemaining( SquareRegularType.Tertiary, 20 );
      verify( added ).accept( SquareRegularType.Tertiary, 28 );
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
      systemUnderTest.increaseRemaining( SquareRegularType.Primary, 10 );
      systemUnderTest.increaseRemaining( SquareRegularType.Secondary, 12 );
      systemUnderTest.increaseRemaining( SquareRegularType.Tertiary, 8 );
      
      systemUnderTest.decreaseRemaining( SquareRegularType.Primary, 5 );
      systemUnderTest.decreaseRemaining( SquareRegularType.Secondary, 100 );
      systemUnderTest.decreaseRemaining( SquareRegularType.Quaternary, 7 );
      
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 5 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Secondary ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Tertiary ), is( 8 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Quaternary ), is( 0 ) );
   }//End Method
   
   @Test public void shouldProvideBuilderForProperties(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      assertThat( systemUnderTest.withMoves( 34 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.movesRemaining().get(), is( 34 ) );
      
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 0 ) );
      assertThat( systemUnderTest.withTarget( SquareRegularType.Primary, 23 ), is( systemUnderTest ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 23 ) );
   }//End Method
   
   @Test public void shouldResetProperties(){
      assertThat( systemUnderTest.movesRemaining().get(), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Secondary ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Tertiary ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Quaternary ), is( 0 ) );
      
      systemUnderTest.reset();
      assertThat( systemUnderTest.movesRemaining().get(), is( GameProperties.DEFAULT_MOVES ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Primary ), is( ColoursGtProperties.DEFAULT_PRIMARY ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Secondary ), is( ColoursGtProperties.DEFAULT_SECONDARY ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Tertiary ), is( 0 ) );
      assertThat( systemUnderTest.remainingFor( SquareRegularType.Quaternary ), is( 0 ) );
   }//End Method

}//End Class
