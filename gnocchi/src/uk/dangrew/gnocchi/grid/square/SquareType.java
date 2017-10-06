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

   Regular( new FloodFill(), null ),
   
   HorizontalBlast( new RowMatcher(), new Images().horizontalBlastImage() ),
   VerticalBlast( new ColumnMatcher(), new Images().verticalBlastImage() ),
   CrossBlast( new CrossMatcher(), new Images().crossBlastImage() ),
   BombBlast( new BombMatcher(), new Images().bombBlastImage() ),
   MassMatcher( new MassColourMatcher(), new Images().massMatchImage() ),
   
   BombHorizontal( new BombHorizontalMatcher(), new Images().bombHorizontalImage() ),
   BombVertical( new BombVerticalMatcher(), new Images().bombVerticalImage() ),
   BombCross( new BombCrossMatcher(), new Images().bombCrossImage() ),
   BombBomb( new BombMatcher( 4 ), new Images().bombBombImage() ),
   
   MassHorizontal( new MassCompositeMatcher( new RowMatcher() ), new Images().massHorizontalImage() ),
   MassVertical( new MassCompositeMatcher( new ColumnMatcher() ), new Images().massVerticalImage() ),
   MassCross( new MassCompositeMatcher( new CrossMatcher() ), new Images().massCrossImage() ),
   MassBomb( new MassCompositeMatcher( new BombMatcher() ), new Images().massBombImage() ),
   MassMass( new CompleteMatcher(), new Images().massMassImage() );
   
   private final SquareMatcher matcher;
   private final Image image;
   
   private SquareType( SquareMatcher matcher, Image image ) {
      this.matcher = matcher;
      this.image = image;
   }//End Constructor
   
   public SquareMatcher matcher(){
      return matcher;
   }//End Method
   
   public Image image(){
      return image;
   }//End Method
}//End Enum
