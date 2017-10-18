package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class NoneMatcher implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      return new ArrayList<>();
   }//End Method

}//End Class
