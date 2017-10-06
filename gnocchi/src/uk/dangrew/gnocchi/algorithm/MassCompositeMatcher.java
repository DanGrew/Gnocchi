package uk.dangrew.gnocchi.algorithm;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;

public class MassCompositeMatcher implements SquareMatcher {

   private final MassColourMatcher massMatcher;
   private final SquareMatcher composite;
   
   public MassCompositeMatcher( SquareMatcher composite ) {
      this.massMatcher = new MassColourMatcher();
      this.composite = composite;
   }//End Constructor
   
   @Override public List< Square > match( GridModel model, int w, int h ) {
      Set< Square > compositeMatches = new LinkedHashSet<>();
      
      List< Square > matches = massMatcher.match( model, w, h );
      for ( Square square : matches ) {
         compositeMatches.addAll( composite.match( model, square.position().w, square.position().h ) );
      }
      
      return new ArrayList<>( compositeMatches );
   }//End Method

}//End Class
