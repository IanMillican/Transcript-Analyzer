package com.ianmillican.transcriptanalyzer.domain.service;

import java.io.File;

import com.ianmillican.transcriptanalyzer.domain.interfaces.TranscriptParser;
import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.errors.InvalidInputException;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public class TranscriptService {

	private final TranscriptParser parser;
	
	public TranscriptService(TranscriptParser parser) {
		this.parser = parser;
	}
	
	public Transcript getTranscript(String path) throws ParsingException, InvalidInputException {
		if(!path.endsWith(".pdf") ) {
			throw new InvalidInputException("File type must be a PDF");
		}
		File f = new File(path);
		return parser.parse(f);
	}
	
}
