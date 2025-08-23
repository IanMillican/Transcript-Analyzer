package com.ianmillican.transcriptanalyzer.ui.viewmodel;

import com.ianmillican.transcriptanalyzer.domain.model.Transcript;
import com.ianmillican.transcriptanalyzer.domain.service.TranscriptService;

public class AnalysisViewModel {
	
	private final Transcript transcript;
	private final TranscriptService service;
	
	public AnalysisViewModel(Transcript transcript, TranscriptService service) {
		this.transcript = transcript;
		this.service = service;
	}
	
	
	
}
