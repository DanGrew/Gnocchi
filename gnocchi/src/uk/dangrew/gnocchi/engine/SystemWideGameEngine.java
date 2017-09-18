package uk.dangrew.gnocchi.engine;

import uk.dangrew.kode.system.SystemWideObject;

public class SystemWideGameEngine extends SystemWideObject< GameEngine >{
   
   private static final GameEngine engine = new GameEngine();
   
   public SystemWideGameEngine() {
      super( engine );
   }//End Constructor

}//End Class
