package uk.dangrew.gnocchi.input;

import uk.dangrew.gnocchi.engine.GameEngine;
import uk.dangrew.gnocchi.engine.SystemWideGameEngine;
import uk.dangrew.gnocchi.ui.square.SquareWidget;

public class InputDriver {
   
   private final GameEngine engine;
   
   public InputDriver() {
      this( new SystemWideGameEngine().get() );
   }//End Constructor
   
   InputDriver( GameEngine engine ) {
      this.engine = engine;
   }//End Constructor
   
   public void popAction( SquareWidget widget ) {
      widget.setOnMouseClicked( e -> engine.pop( widget.association() ) );
   }//End Method

}//End Class
