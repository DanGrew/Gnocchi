package uk.dangrew.gnocchi.ui.square;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import uk.dangrew.gnocchi.algorithm.BonusMatcher;
import uk.dangrew.gnocchi.game.matching.MatchChainer;
import uk.dangrew.gnocchi.grid.model.GridModel;
import uk.dangrew.gnocchi.grid.square.Square;
import uk.dangrew.gnocchi.input.InputDriver;

public class HighlightModel {
   
   private final GridModel model;
   private final InputDriver inputDriver;
   private final MatchChainer matchChainer;
   private final BonusMatcher bonusMatcher;
   
   private final Map< Square, SquareWidget > widgets;
   
   private final ObjectProperty< SquareWidget > selected;
   private final ObservableSet< SquareWidget > matchingSelection;
   private final ObservableSet< SquareWidget > bonusesForSelection;
   
   private final ObjectProperty< SquareWidget > highlighted;
   private final ObservableSet< SquareWidget > matchingHighlighted;
   
   public HighlightModel( GridModel model, InputDriver inputDriver ){
      this( model, inputDriver, new MatchChainer(), new BonusMatcher() );
   }//End Constructor
   
   HighlightModel( 
            GridModel model, 
            InputDriver inputDriver, 
            MatchChainer squareMatcher,
            BonusMatcher bonusMatcher
   ) {
      this.model = model;
      this.inputDriver = inputDriver;
      this.matchChainer = squareMatcher;
      this.bonusMatcher = bonusMatcher;
      
      this.widgets = new HashMap<>();
      this.selected = new SimpleObjectProperty<>();
      this.highlighted = new SimpleObjectProperty<>();
      this.matchingSelection = FXCollections.observableSet( new LinkedHashSet<>() );
      this.matchingHighlighted = FXCollections.observableSet( new LinkedHashSet<>() );
      this.bonusesForSelection = FXCollections.observableSet( new LinkedHashSet<>() );
   }//End Constructor

   public void monitor( SquareWidget widget ) {
      widgets.put( widget.association(), widget );
      widget.setOnMouseEntered( e -> onEntered( widget ) );
      widget.setOnMouseExited( e -> onExited( widget ) );
      widget.setOnMouseClicked( e -> onClicked( widget ) );
   }//End Method
   
   public void remove( Square object ) {
      SquareWidget widget = widgets.remove( object );
      widget.setOnMouseClicked( null );
      widget.setOnMouseEntered( null );
      widget.setOnMouseExited( null );
   }//End Method
   
   private void onClicked( SquareWidget widget ) {
      if ( !widget.association().type().properties().isPoppable() ) {
         return;
      }
      if ( widget == selected.get() ) {
         inputDriver.pop( widget );
         select( null );
      } else if ( bonusesForSelection.contains( widget ) ) {
         SquareWidget selected = selected().get();
         inputDriver.combine( selected, widget );
         select( selected );
      } else {
         select( widget );
      }
   }//End Method
   
   private void select( SquareWidget widget ) {
      matchingSelection.clear();
      bonusesForSelection.clear();
      
      if ( widget != null ) {
         List< Square > matches = matchChainer.match( model, widget.association().position().w, widget.association().position().h );
         matches.forEach( s -> matchingSelection.add( widgets.get( s ) ) );
         
         List< Square > bonuses = bonusMatcher.match( model, widget.association().position().w, widget.association().position().h );
         bonuses.forEach( s -> bonusesForSelection.add( widgets.get( s ) ) );
      }
      selected.set( widget );
   }//End Method
   
   private void onEntered( SquareWidget widget ) {
      if ( !widget.association().type().properties().isPoppable() ) {
         return;
      }
      
      List< Square > matches = matchChainer.match( model, widget.association().position().w, widget.association().position().h );
      matchingHighlighted.clear();
      matches.forEach( s -> matchingHighlighted.add( widgets.get( s ) ) );
      
      highlighted.set( widget );
   }//End Method
   
   private void onExited( SquareWidget widget ) {
      highlighted.set( null );
      matchingHighlighted.clear();
   }//End Method
   
   ObjectProperty< SquareWidget > selected(){
      return selected;
   }//End Method
   
   ObjectProperty< SquareWidget > highlighted(){
      return highlighted;
   }//End Method
   
   ObservableSet< SquareWidget > matchingSelection(){
      return matchingSelection;
   }//End Method
   
   ObservableSet< SquareWidget > bonusForSelection(){
      return bonusesForSelection;
   }//End Method
   
   ObservableSet< SquareWidget > matchingHighlighted(){
      return matchingHighlighted;
   }//End Method

   boolean isSelected( SquareWidget widget ) {
      return widget == selected.get();
   }//End Method
   
   boolean isMatchingSelection( SquareWidget widget ) {
      return matchingSelection.contains( widget );
   }//End Method
   
   boolean isBonusForSelection( SquareWidget widget ) {
      return bonusesForSelection.contains( widget );
   }//End Method
   
   boolean isHighlighted( SquareWidget widget ) {
      return widget == highlighted.get();
   }//End Method
   
   boolean isMatchingHighlighted( SquareWidget widget ) {
      return matchingHighlighted.contains( widget );
   }//End Method
   
}//End Class
