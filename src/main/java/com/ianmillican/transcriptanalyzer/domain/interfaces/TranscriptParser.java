package com.ianmillican.transcriptanalyzer.domain.interfaces;

import java.io.File;
import com.ianmillican.transcriptanalyzer.domain.model.ParsedTranscript;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public interface TranscriptParser {

	ParsedTranscript parse(File input) throws ParsingException;
	
}
