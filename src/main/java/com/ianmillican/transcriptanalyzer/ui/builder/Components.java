package com.ianmillican.transcriptanalyzer.ui.builder;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.beans.property.SimpleObjectProperty;

public final class Components {
	
	private Components() {throw new AssertionError("Components cannot be instantiated");}

	public static Button button(String name, Runnable method) {
		Button result = new Button(name);
		result.setOnAction(e -> {method.run();});
		return result;
	}
	
	public static Button button(String name, Runnable method, String styleClass) {
		Button result = new Button(name);
		result.setOnAction(e -> {method.run();});
		result.getStyleClass().add(styleClass);
		return result;
	}
	
	public static <E> Button button(String name, String styleClass, Consumer<E> method, E methodInput) {
		Button result = new Button(name);
		result.setOnAction(e -> {method.accept(methodInput);});
		result.getStyleClass().add(styleClass);
		return result;
	}
	
	public static <E> Button button(String name, String styleClass, List<Consumer<E>> methods, E methodInput) {
		Button result = new Button(name);
		result.setOnAction(e -> {
			Consumer<E> chainedConsumer = null;
			if(!methods.isEmpty()) {
				chainedConsumer = methods.get(0);
				for(int i=1; i<methods.size(); i++) {
					chainedConsumer = chainedConsumer.andThen(methods.get(i));
				}
			}
		});
		result.getStyleClass().add(styleClass);
		return result;
	}
	
	public static Label label(String text, String styleClass) {
		Label result = new Label(text);
		result.getStyleClass().add(styleClass);
		return result;
	}
	
	public static Label boundLabel(String label, Property<String> val, String styleSheet) {
		Label result = new Label(label);
		result.textProperty().bindBidirectional(val);
		result.getStyleClass().add(styleSheet);
		return result;
	}
	
	public static <S,T> TableColumn<S,T> createColumn(String title, Function<S,T> populationMethod) {
		TableColumn<S,T> result = new TableColumn<>(title);
		result.setCellValueFactory(data -> {
			return new SimpleObjectProperty<T>(populationMethod.apply(data.getValue()));
		});
		return result;
	}
	
}
