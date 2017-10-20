package uk.dangrew.gnocchi.ui.square;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareRegularType;
import uk.dangrew.gnocchi.input.InputDriver;

public class SquareHighlighterTest {

   private SquareWidget sw1;
   private SquareWidget sw2;
   private SquareWidget sw3;
   
   @Mock private SquareHighlighting highlighting;
   private HighlightModel highlightModel;
   private SquareHighlighter systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      highlightModel = new HighlightModel( mock( GridModel.class ), mock( InputDriver.class ) );
      sw1 = new SquareWidget( new Square( new GridPosition( 3, 4 ), SquareRegularType.Primary ), 0, 0, 0, 0 );
      sw2 = new SquareWidget( new Square( new GridPosition( 2, 1 ), SquareRegularType.Primary ), 0, 0, 0, 0 );
      sw3 = new SquareWidget( new Square( new GridPosition( 0, 0 ), SquareRegularType.Primary ), 0, 0, 0, 0 );
      systemUnderTest = new SquareHighlighter( highlighting, highlightModel );
   }//End Method

   @Test public void shouldSelect(){
      highlightModel.selected().set( sw1 );
      verify( highlighting ).styleSelected( sw1, new ArrayList<>() );
      
      highlightModel.selected().set( sw2 );
      verify( highlighting ).styleSelected( sw1, new ArrayList<>() );
      verify( highlighting ).styleSelected( sw2, new ArrayList<>() );
   }//End Method
   
   @Test public void shouldSelectMatches(){
      highlightModel.matchingSelection().add( sw1 );
      verify( highlighting ).styleMatchingSelection( sw1 );
      
      highlightModel.matchingSelection().add( sw2 );
      verify( highlighting ).styleMatchingSelection( sw2 );
      
      highlightModel.matchingSelection().remove( sw1 );
      verify( highlighting ).styleMatchingSelection( sw1 );
   }//End Method
   
   @Test public void shouldSelectBonuses(){
      highlightModel.bonusForSelection().add( sw1 );
      verify( highlighting ).styleBonusForSelection( sw1 );
      
      highlightModel.bonusForSelection().add( sw2 );
      verify( highlighting ).styleBonusForSelection( sw2 );
      
      highlightModel.bonusForSelection().remove( sw1 );
      verify( highlighting ).styleBonusForSelection( sw1 );
   }//End Method
   
   @Test public void shouldHighlight(){
      highlightModel.highlighted().set( sw1 );
      verify( highlighting ).styleHighlighted( sw1 );
      
      highlightModel.highlighted().set( sw2 );
      verify( highlighting ).styleHighlighted( sw1 );
      verify( highlighting ).styleHighlighted( sw2 );
   }//End Method
   
   @Test public void shouldHighlightMatches(){
      highlightModel.matchingHighlighted().add( sw1 );
      verify( highlighting ).styleMatchingHighlighted( sw1 );
      
      highlightModel.matchingHighlighted().add( sw2 );
      verify( highlighting ).styleMatchingHighlighted( sw2 );
      
      highlightModel.matchingHighlighted().remove( sw1 );
      verify( highlighting ).styleMatchingHighlighted( sw1 );
   }//End Method
   
   @Test public void shouldPrioritizeHighlighting(){
      highlightModel.matchingHighlighted().add( sw1 );
      verify( highlighting ).styleMatchingHighlighted( sw1 );
      
      highlightModel.highlighted().set( sw1 );
      verify( highlighting ).styleHighlighted( sw1 );
      
      highlightModel.matchingSelection().add( sw1 );
      verify( highlighting ).styleMatchingSelection( sw1 );
      
      highlightModel.bonusForSelection().add( sw1 );
      verify( highlighting ).styleBonusForSelection( sw1 );
      
      highlightModel.selected().set( sw1 );
      verify( highlighting ).styleSelected( sw1, Arrays.asList( sw1 ) );
   }//End Method
}//End Class
