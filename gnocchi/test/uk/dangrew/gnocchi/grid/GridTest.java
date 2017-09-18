package uk.dangrew.gnocchi.grid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.gnocchi.grid.controls.Filler;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.model.GridSnapshot;

public class GridTest {

   private static final int WIDTH = 10;
   private static final int HEIGHT = 10;
   
   @Mock private GridModel model;
   @Mock private Filler filler;
   private Grid systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new Grid( model, filler );
   }//End Method
   
   @Test public void shouldConstructOwnGrid(){
      systemUnderTest = new Grid( WIDTH, HEIGHT );
      assertThat( systemUnderTest.model().width(), is( WIDTH ) );
   }//End Method
   
   @Test public void shouldProvideModel(){
      assertThat( systemUnderTest.model(), is( model ) );
   }//End Method
   
   @Test public void shouldProvideSnapshot(){
      GridSnapshot snapshot = mock( GridSnapshot.class );
      when( model.snapshot() ).thenReturn( snapshot );
      
      assertThat( systemUnderTest.snapshot(), is( snapshot ) );
   }//End Method
   
   @Test public void shouldUseFillerToFill(){
      systemUnderTest.fill();
      verify( filler ).fill();
   }//End Method

}//End Class
