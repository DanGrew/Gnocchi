package uk.dangrew.gnocchi.framework;

public interface GnocchiAnimation {

   public void animate();

   public Runnable onCompletion();

   public void setOnCompletion( Runnable onCompletion );

}//End Interface

