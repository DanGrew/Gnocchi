package uk.dangrew.gnocchi.ui.square;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.grid.model.GridPosition;
import uk.dangrew.gnocchi.grid.square.Square;

public class SquareHighlighterTest {

   private SquareWidget sw1;
   private SquareWidget sw2;
   private SquareWidget sw3;
   
   private Square s1;
   private Square s2;
   private Square s3;
   
   @Mock private FloodFill floodFill;
   private SquareHighlighter systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      
      sw1 = spy( new SquareWidget( s1 = new Square( new GridPosition( 3, 4 ), Color.RED ), 0, 0, 0, 0 ) );
      sw2 = spy( new SquareWidget( s2 = new Square( new GridPosition( 2, 1 ), Color.RED ), 0, 0, 0, 0 ) );
      sw3 = spy( new SquareWidget( s3 = new Square( new GridPosition( 0, 0 ), Color.RED ), 0, 0, 0, 0 ) );
      systemUnderTest = new SquareHighlighter( floodFill );
      
      systemUnderTest.monitor( sw1 );
      systemUnderTest.monitor( sw2 );
      systemUnderTest.monitor( sw3 );
   }//End Method

   @Test public void shouldHighlightAnySquareFlooded() {
      when( floodFill.flood( 3, 4 ) ).thenReturn( Arrays.asList( s2, s3 ) );
      
      sw1.getOnMouseEntered().handle( new MouseEvent( null, null, null, 0, 0, 0, 0, null, 0, true, true, true, true, true, true, true, true, true, true, null ) );
      verify( sw2 ).highlight();
      verify( sw3 ).highlight();
      
      sw1.getOnMouseExited().handle( new MouseEvent( null, null, null, 0, 0, 0, 0, null, 0, true, true, true, true, true, true, true, true, true, true, null ) );
      verify( sw2 ).unhighlight();
      verify( sw3 ).unhighlight();
   }//End Method
   
   @Test public void shouldUnhighlightOnRemoval(){
      systemUnderTest.remove( s1 );
      verify( sw1 ).unhighlight();
      
      systemUnderTest.remove( s1 );
      verify( sw1 ).unhighlight();
   }//End Method

}//End Class
