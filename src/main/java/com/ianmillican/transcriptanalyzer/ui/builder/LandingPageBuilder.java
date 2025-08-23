package com.ianmillican.transcriptanalyzer.ui.builder;


import com.ianmillican.transcriptanalyzer.ui.navigation.RootSwapNavigator;
import com.ianmillican.transcriptanalyzer.ui.viewmodel.AnalysisViewModel;
import com.ianmillican.transcriptanalyzer.ui.viewmodel.LandingPageViewModel;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.util.Builder;

public class LandingPageBuilder implements Builder<Region> {

	private final LandingPageViewModel model;
	private final RootSwapNavigator nav;
	private Insets padding = new Insets(24);
	
	public LandingPageBuilder(LandingPageViewModel vm, RootSwapNavigator nav) {
		model = vm;
		this.nav = nav;
	}

	@Override
	public Region build() {
		Region dragAndDrop = dragAndDrop();
		VBox result = new VBox(dragAndDrop);
		result.setPadding(padding);
		VBox.setVgrow(dragAndDrop, Priority.ALWAYS);
		model.fileSetProperty().addListener((obs, oldVal, newVal) -> {
			AnalysisViewModel newModel = new AnalysisViewModel(model.getTranscript(), model.getTService());
			AnalysisBuilder newBuilder = new AnalysisBuilder(newModel, nav);
			Region analysisRoot = newBuilder.build();
			nav.show(analysisRoot);
		});
		return result;
	}
	
	private Region dragAndDrop() {
		StackPane result = new StackPane();
		result.getStyleClass().add("drop-zone");
		result.setPrefSize(320, 160);
        result.setPickOnBounds(true);

        result.getChildren().addAll(selectFileWithError());
        
		result.setOnDragOver(e -> {
			model.dragOver(e);
		});
		
		result.setOnDragEntered(e -> {
			model.dragEntered(e);
		});
		
		result.setOnDragExited(e -> {
			model.dragExited(e);
		});
		
		result.setOnDragDropped(e -> {
			model.dragDropped(e);
		});
		return result;
	}
	
	private Region selectFileWithError() {
        Region prompt = Components.label("Drop the transcript here or click below\nTranscript must be a PDF", "drop-prompt");
        prompt.setMouseTransparent(true);
        VBox result = new VBox();
        Region browseButton = Components.genericButton("Browse", model::selectTranscriptFile, "browse-button");
        result.getChildren().addAll(prompt, browseButton, errorLabel());
        result.setAlignment(Pos.CENTER);
        result.setPadding(padding);
        result.setSpacing(24);
        return result;
	}
	
	private Region errorLabel() {
		return Components.boundLabel("", model.errorProperty(), "error-text");
	}

}
