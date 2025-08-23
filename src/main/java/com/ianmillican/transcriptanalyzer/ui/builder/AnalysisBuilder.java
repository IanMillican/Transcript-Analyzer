package com.ianmillican.transcriptanalyzer.ui.builder;

import com.ianmillican.transcriptanalyzer.ui.navigation.RootSwapNavigator;
import com.ianmillican.transcriptanalyzer.ui.viewmodel.AnalysisViewModel;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class AnalysisBuilder implements Builder<Region> {
	
	private final AnalysisViewModel model;
	private final RootSwapNavigator nav;
	private Insets padding = new Insets(24);
	
	public AnalysisBuilder(AnalysisViewModel model, RootSwapNavigator nav) {
		this.model = model;
		this.nav = nav;
	}

	@Override
	public Region build() {
		VBox result = new VBox(new Label("Test test"));
		return result;
	}

}
