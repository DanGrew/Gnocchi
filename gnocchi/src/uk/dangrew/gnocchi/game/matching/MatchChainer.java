package uk.dangrew.gnocchi.game.matching;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.dangrew.gnocchi.algorithm.SquareMatcher;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.grid.square.SquareType;

public class MatchChainer implements SquareMatcher {

   @Override public List< Square > match( GridModel model, int w, int h ) {
      Set< Square > searched = new LinkedHashSet<>();
      
      Square source = model.at( w, h );
      if ( source == null ) {
         return new ArrayList<>();
      }
      List< Square > matches = source.type().matcher().match( model, source.position().w, source.position().h );
      for ( Square match : matches ) {
         search( model, match, searched );
      }
      
      return new ArrayList<>( searched );
   }//End Method
   
   private void search( GridModel model, Square source, Set< Square > searched ){
      if ( source == null ) {
         return;
      }
      searched.add( source );
      if ( source.type().equals( SquareType.Regular ) ) {
         return;
      }
      List< Square > matches = source.type().matcher().match( model, source.position().w, source.position().h );
      for ( Square match : matches ) {
         if ( searched.contains( match ) ) {
            continue;
         }
         
         search( model, match, searched );
      }
   }//End Method

}//End Class
