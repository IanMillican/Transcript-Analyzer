package com.ianmillican.transcriptanalyzer.domain.interfaces;

import java.util.List;

import com.ianmillican.transcriptanalyzer.domain.model.Term;
import com.ianmillican.transcriptanalyzer.errors.ParsingException;

public interface TermParser {

	public List<Term> parse(List<String> transcript) throws ParsingException;
	
}
