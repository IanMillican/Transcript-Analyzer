package com.ianmillican.transcriptanalyzer.app;

import java.io.File;

import com.ianmillican.transcriptanalyzer.dataaccess.parser.PDFTranscriptParser;
import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main {//extends Application{

	public static void main(String[] args) {
		String path = "/Users/ian/Desktop/tempTranscriptStuff/IanMillicanTranscript.pdf";
		PDFTranscriptParser parser = new PDFTranscriptParser();
		Transcript t = null;
		try {
			t = parser.parse(new File(path));
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(t);
		//launch(args);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

}
