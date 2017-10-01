package uk.dangrew.gnocchi.algorithm;

import java.util.List;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public interface SquareMatcher {

   public List< Square > match( GridModel model, int w, int h );
   
}//End Interface
