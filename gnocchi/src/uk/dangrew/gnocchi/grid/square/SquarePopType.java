package uk.dangrew.gnocchi.grid.square;

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
import uk.dangrew.gnocchi.ui.resources.Images;

public enum SquarePopType implements SquareType {

   Regular( 
            new SquareProperties().withMatcher( new FloodFill() )
   ),
   
   HorizontalBlast( 
            new SquareProperties()
               .withMatcher( new RowMatcher() )
               .withImage( new Images().horizontalBlastImage() )
               .withComboEnabled( true ) 
   ),
   VerticalBlast( 
            new SquareProperties()
               .withMatcher( new ColumnMatcher() )
               .withImage( new Images().verticalBlastImage() )
               .withComboEnabled( true ) 
   ),
   CrossBlast( 
            new SquareProperties()
               .withMatcher( new CrossMatcher() )
               .withImage( new Images().crossBlastImage() )
               .withComboEnabled( true )
   ),
   BombBlast( 
            new SquareProperties()
               .withMatcher( new BombMatcher() )
               .withImage( new Images().bombBlastImage() )
               .withComboEnabled( true ) 
   ),
   MassBlast( 
            new SquareProperties()
               .withMatcher( new MassColourMatcher() )
               .withImage( new Images().massMatchImage() )
               .withComboEnabled( true ) 
   ),
   
   BombHorizontal( 
            new SquareProperties()
               .withMatcher( new BombHorizontalMatcher() )
               .withImage( new Images().bombHorizontalImage() )
               .withComboEnabled( false ) 
   ),
   BombVertical( 
            new SquareProperties()
               .withMatcher( new BombVerticalMatcher() )
               .withImage( new Images().bombVerticalImage() )
               .withComboEnabled( false ) 
   ),
   BombCross( 
            new SquareProperties()
               .withMatcher( new BombCrossMatcher() )
               .withImage( new Images().bombCrossImage() )
               .withComboEnabled( false ) 
   ),
   BombBomb( 
            new SquareProperties()
               .withMatcher( new BombMatcher( 4 ) )
               .withImage( new Images().bombBombImage() )
               .withComboEnabled( false ) 
   ),
   
   MassHorizontal( 
            new SquareProperties()
               .withMatcher( new MassCompositeMatcher( new RowMatcher() ) )
               .withImage( new Images().massHorizontalImage() )
               .withComboEnabled( false ) 
   ),
   MassVertical( 
            new SquareProperties()
               .withMatcher( new MassCompositeMatcher( new ColumnMatcher() ) )
               .withImage( new Images().massVerticalImage() )
               .withComboEnabled( false ) 
   ),
   MassCross( 
            new SquareProperties()
               .withMatcher( new MassCompositeMatcher( new CrossMatcher() ) )
               .withImage( new Images().massCrossImage() )
               .withComboEnabled( false ) 
   ),
   MassBomb( 
            new SquareProperties()
               .withMatcher( new MassCompositeMatcher( new BombMatcher() ) )
               .withImage( new Images().massBombImage() )
               .withComboEnabled( false ) 
   ),
   MassMass( 
            new SquareProperties()
               .withMatcher( new CompleteMatcher() )
               .withImage( new Images().massMassImage() )
               .withComboEnabled( false )
   );
            
   private final SquareProperties properties;
   
   private SquarePopType( SquareProperties properties ) {
      this.properties = properties;
      this.properties.withPoppable( true );
   }//End Constructor
   
   @Override public SquareProperties properties(){
      return properties;
   }//End Method
}//End Enum
