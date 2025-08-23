package com.ianmillican.transcriptanalyzer.ui.viewmodel;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.domain.service.TranscriptService;
import com.ianmillican.transcriptanalyzer.errors.InvalidInputException;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;

public class LandingPageViewModel {
	
	//Properties
	private final ObjectProperty<File> transcriptFileProperty = new SimpleObjectProperty<>();
	private final SimpleStringProperty filePathProperty = new SimpleStringProperty();
	private final SimpleStringProperty errorProperty = new SimpleStringProperty("");
	private final BooleanProperty fileSetProperty = new SimpleBooleanProperty(false);
	
	//Service/Transcript
	private final TranscriptService tService;
	private Transcript transcript;

	public LandingPageViewModel(TranscriptService tService) {
		this.tService = tService;
		filePathProperty.addListener((obs, oldVal, newVal) -> {
			parseTranscript();
		});
		this.transcript = null;
	}
	
	private void parseTranscript() {
		try {
			transcript = tService.getTranscript(filePathProperty.get());
			fileSetProperty.set(true);
		} catch (ParsingException e) {
			errorProperty.set(e.getMessage());
		} catch (InvalidInputException e) {
			String path = filePathProperty.get();
			String fileName = Path.of(path).getFileName().toString();
			errorProperty.set(fileName+" is not a PDF");
		}
	}
	
	public void selectTranscriptFile() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select Your Transcript");
		File file = chooser.showOpenDialog(null);
		if (file != null) {
			filePathProperty.set(file.getAbsolutePath());
		}
	}
	
	public void dragEntered(DragEvent e) {
	    if (e.getDragboard().hasFiles()) {
	        Node n = (Node) e.getSource();
	        ObservableList<String> classes = n.getStyleClass();
	        if (!classes.contains("drag-over")) {
	            classes.add("drag-over");
	        }
	    }
	    e.consume();
	}
	
	public void dragExited(DragEvent e) {
		Node n = (Node) e.getSource();
		ObservableList<String> classes = n.getStyleClass();
		if(classes.contains("drag-over")) {
			classes.remove("drag-over");
		}
		e.consume();
	}
	
	public void dragDropped(DragEvent e) {
		Dragboard db = e.getDragboard();
        boolean success = false;
        if(db.hasFiles()) {
        	List<File> files = db.getFiles();
        	if(files.size() == 1) {
        		filePathProperty.set(files.get(0).getAbsolutePath());
        	} else if (files.size() != 0) {
        		for(File file : files) {
        			System.out.println(file.getAbsolutePath());
        		}
        	} else {
        		System.out.println("No file was given");
        	}
            success = true;
        }
        e.setDropCompleted(success);
		Node n = (Node) e.getSource();
		ObservableList<String> classes = n.getStyleClass();
		if(classes.contains("drag-over")) {
			classes.remove("drag-over");
		}
        e.consume();
	}
	
	public void dragOver(DragEvent e) {
		Dragboard db = e.getDragboard();
		if(db.hasFiles()) {
			e.acceptTransferModes(TransferMode.COPY);
		} 
		e.consume();
	}
	
	public Transcript getTranscript() {
		return transcript;
	}
	
	public TranscriptService getTService() {
		return tService;
	}
	
	//Property Methods
	public ObjectProperty<File> transcriptFileProperty() {
		return transcriptFileProperty;
	}
	
	public File getTranscriptFileProperty() {
		return transcriptFileProperty.get();
	}
	
	public SimpleStringProperty filePathProperty() {
		return filePathProperty;
	}
	
	public String filePath() {
		return filePathProperty.get();
	}
	
	public SimpleStringProperty errorProperty() {
		return errorProperty;
	}
	
	public String getError() {
		return errorProperty.get();
	}
	
	public BooleanProperty fileSetProperty() {
		return fileSetProperty;
	}
	
	public boolean fileSet() {
		return fileSetProperty.get();
	}
	
}
