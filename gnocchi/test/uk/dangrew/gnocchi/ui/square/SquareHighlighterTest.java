package uk.dangrew.gnocchi.ui.square;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;
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
      sw1 = new SquareWidget( new Square( new GridPosition( 3, 4 ), Color.RED ), 0, 0, 0, 0 );
      sw2 = new SquareWidget( new Square( new GridPosition( 2, 1 ), Color.RED ), 0, 0, 0, 0 );
      sw3 = new SquareWidget( new Square( new GridPosition( 0, 0 ), Color.RED ), 0, 0, 0, 0 );
      systemUnderTest = new SquareHighlighter( highlighting, highlightModel );
   }//End Method

   @Test public void shouldSelect(){
      highlightModel.selected().set( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.Selected );
      
      highlightModel.selected().set( sw2 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.None );
      verify( highlighting ).widgetStateChange( sw2, SquareHighlightType.Selected );
   }//End Method
   
   @Test public void shouldSelectMatches(){
      highlightModel.matchingSelection().add( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.MatchingSelection );
      
      highlightModel.matchingSelection().add( sw2 );
      verify( highlighting ).widgetStateChange( sw2, SquareHighlightType.MatchingSelection );
      
      highlightModel.matchingSelection().remove( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.None );
   }//End Method
   
   @Test public void shouldSelectBonuses(){
      highlightModel.bonusForSelection().add( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.BonusForSelection );
      
      highlightModel.bonusForSelection().add( sw2 );
      verify( highlighting ).widgetStateChange( sw2, SquareHighlightType.BonusForSelection );
      
      highlightModel.bonusForSelection().remove( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.None );
   }//End Method
   
   @Test public void shouldHighlight(){
      highlightModel.highlighted().set( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.Highlighted );
      
      highlightModel.highlighted().set( sw2 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.None );
      verify( highlighting ).widgetStateChange( sw2, SquareHighlightType.Highlighted );
   }//End Method
   
   @Test public void shouldHighlightMatches(){
      highlightModel.matchingHighlighted().add( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.MatchingHighlighted );
      
      highlightModel.matchingHighlighted().add( sw2 );
      verify( highlighting ).widgetStateChange( sw2, SquareHighlightType.MatchingHighlighted );
      
      highlightModel.matchingHighlighted().remove( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.None );
   }//End Method
   
   @Test public void shouldPrioritizeHighlighting(){
      highlightModel.matchingHighlighted().add( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.MatchingHighlighted );
      
      highlightModel.highlighted().set( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.Highlighted );
      
      highlightModel.matchingSelection().add( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.MatchingSelection );
      
      highlightModel.bonusForSelection().add( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.BonusForSelection );
      
      highlightModel.selected().set( sw1 );
      verify( highlighting ).widgetStateChange( sw1, SquareHighlightType.Selected );
   }//End Method
}//End Class
