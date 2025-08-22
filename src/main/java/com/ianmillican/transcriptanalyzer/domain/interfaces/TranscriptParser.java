package com.ianmillican.transcriptanalyzer.domain.interfaces;

import java.io.File;
import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public interface TranscriptParser {

	Transcript parse(File input) throws ParsingException;
	
}
