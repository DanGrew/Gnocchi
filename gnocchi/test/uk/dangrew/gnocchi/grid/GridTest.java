package uk.dangrew.gnocchi.grid;

import org.junit.Before;

public class GridTest {

   private static final int WIDTH = 10;
   private static final int HEIGHT = 10;
   
   private Grid systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new Grid( WIDTH, HEIGHT );
   }//End Method

}//End Class
