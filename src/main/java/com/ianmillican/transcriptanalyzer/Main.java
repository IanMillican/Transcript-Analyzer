package com.ianmillican.transcriptanalyzer;

import com.ianmillican.transcriptanalyzer.dataaccess.parser.PDFTranscriptParser;
import com.ianmillican.transcriptanalyzer.domain.service.TranscriptService;
import com.ianmillican.transcriptanalyzer.domain.interfaces.Navigator;
import com.ianmillican.transcriptanalyzer.ui.builder.LandingPageBuilder;
import com.ianmillican.transcriptanalyzer.ui.navigation.RootSwapNavigator;
import com.ianmillican.transcriptanalyzer.ui.viewmodel.LandingPageViewModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	private int width = 1000;
	private int height = 700;
	private String title = "Transcript Analyzer";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Services/Parsers/VMs
		PDFTranscriptParser parser = new PDFTranscriptParser();
		TranscriptService tService = new TranscriptService(parser);
		LandingPageViewModel vm = new LandingPageViewModel(tService);
		
		StackPane appRoot = new StackPane();
		Scene scene = new Scene(appRoot, width, height);
	    scene.getStylesheets().addAll(
	            getClass().getResource("/ui/css/dropzone.css").toExternalForm(),
	            getClass().getResource("/ui/css/buttons.css").toExternalForm(),
	            getClass().getResource("/ui/css/coursetable.css").toExternalForm()
	    );
		

		Navigator nav = new RootSwapNavigator(appRoot);
		LandingPageBuilder builder = new LandingPageBuilder(vm, nav);
		Region landingRoot = builder.build();
	    appRoot.getChildren().setAll(landingRoot);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle(title);
		primaryStage.show();
	}

}
