package com.ianmillican.transcriptanalyzer.ui.builder;

import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class Components {

	public static Region genericButton(String name, Runnable method) {
		Button result = new Button(name);
		result.setOnAction(e -> {method.run();});
		return result;
	}
	
	public static Region genericButton(String name, Runnable method, String styleSheet) {
		Button result = new Button(name);
		result.setOnAction(e -> {method.run();});
		result.getStyleClass().add(styleSheet);
		return result;
	}
	
	public static Region label(String text, String styleSheet) {
		Label result = new Label(text);
		result.getStyleClass().add(styleSheet);
		return result;
	}
	
	public static Region boundLabel(String label, Property<String> val) {
		Label result = new Label(label);
		result.textProperty().bindBidirectional(val);
		return result;
	}
	
	public static Region boundLabel(String label, Property<String> val, String styleSheet) {
		Label result = new Label(label);
		result.textProperty().bindBidirectional(val);
		result.getStyleClass().add(styleSheet);
		return result;
	}
	
}
