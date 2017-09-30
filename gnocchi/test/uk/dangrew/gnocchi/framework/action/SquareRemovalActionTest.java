package uk.dangrew.gnocchi.framework.action;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.game.Game;
import uk.dangrew.gnocchi.game.mechanics.Logic;
import uk.dangrew.gnocchi.game.type.GameProperties;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class SquareRemovalActionTest {

   @Mock private Logic logic;
   @Mock private GridModel model;
   @Mock private GameProperties properties;
   @Mock private Game game;
   
   private List< Square > popSquares;
   private SquareRemovalAction systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      when( game.logic() ).thenReturn( logic );
      when( game.model() ).thenReturn( model );
      when( game.properties() ).thenReturn( properties );
      
      popSquares = Arrays.asList( Square.randomSquare(), Square.randomSquare(), Square.randomSquare() );
      systemUnderTest = new SquareRemovalAction( game, popSquares );
   }//End Method

   @Test public void shouldTriggerLogic() {
      systemUnderTest.execute();
      verify( logic ).popAll( popSquares );
   }//End Method
   
   @Test public void shouldRemoveSquaresFromModel(){
      systemUnderTest.execute();
      for ( Square s : popSquares ) {
         verify( model ).remove( s );
      }
   }//End Method
   
   @Test public void shouldRecordMove(){
      systemUnderTest.execute();
      verify( properties ).moveUsed();
   }//End Method

}//End Class
