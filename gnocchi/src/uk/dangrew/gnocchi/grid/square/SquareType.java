package uk.dangrew.gnocchi.grid.square;

import javafx.scene.image.Image;
import uk.dangrew.gnocchi.algorithm.BombCrossMatcher;
import uk.dangrew.gnocchi.algorithm.BombHorizontalMatcher;
import uk.dangrew.gnocchi.algorithm.BombMatcher;
import uk.dangrew.gnocchi.algorithm.BombVerticalMatcher;
import uk.dangrew.gnocchi.algorithm.ColumnMatcher;
import uk.dangrew.gnocchi.algorithm.CompleteMatcher;
import uk.dangrew.gnocchi.algorithm.CrossMatcher;
import uk.dangrew.gnocchi.algorithm.FloodFill;
import uk.dangrew.gnocchi.algorithm.MassColourMatcher;
import uk.dangrew.gnocchi.algorithm.MassCompositeMatcher;
import uk.dangrew.gnocchi.algorithm.RowMatcher;
import uk.dangrew.gnocchi.algorithm.SquareMatcher;
import uk.dangrew.gnocchi.ui.resources.Images;

public enum SquareType {

   Regular( new FloodFill(), null, false ),
   
   HorizontalBlast( new RowMatcher(), new Images().horizontalBlastImage(), true ),
   VerticalBlast( new ColumnMatcher(), new Images().verticalBlastImage(), true ),
   CrossBlast( new CrossMatcher(), new Images().crossBlastImage(), true ),
   BombBlast( new BombMatcher(), new Images().bombBlastImage(), true ),
   MassBlast( new MassColourMatcher(), new Images().massMatchImage(), true ),
   
   BombHorizontal( new BombHorizontalMatcher(), new Images().bombHorizontalImage(), false ),
   BombVertical( new BombVerticalMatcher(), new Images().bombVerticalImage(), false ),
   BombCross( new BombCrossMatcher(), new Images().bombCrossImage(), false ),
   BombBomb( new BombMatcher( 4 ), new Images().bombBombImage(), false ),
   
   MassHorizontal( new MassCompositeMatcher( new RowMatcher() ), new Images().massHorizontalImage(), false ),
   MassVertical( new MassCompositeMatcher( new ColumnMatcher() ), new Images().massVerticalImage(), false ),
   MassCross( new MassCompositeMatcher( new CrossMatcher() ), new Images().massCrossImage(), false ),
   MassBomb( new MassCompositeMatcher( new BombMatcher() ), new Images().massBombImage(), false ),
   MassMass( new CompleteMatcher(), new Images().massMassImage(), false );
   
   private final SquareMatcher matcher;
   private final Image image;
   private final boolean comboEnabled;
   
   private SquareType( SquareMatcher matcher, Image image, boolean comboEnabled ) {
      this.matcher = matcher;
      this.image = image;
      this.comboEnabled = comboEnabled;
   }//End Constructor
   
   public SquareMatcher matcher(){
      return matcher;
   }//End Method
   
   public Image image(){
      return image;
   }//End Method
   
   public boolean isComboEnabled(){
      return comboEnabled;
   }//End Method
}//End Enum
