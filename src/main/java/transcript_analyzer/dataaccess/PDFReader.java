package transcript_analyzer.dataaccess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import transcript_analyzer.domain.Reader;
import transcript_analyzer.domain.core.Term;

public class PDFReader implements Reader{
	
	private String filePath;
	private boolean fileSet;
	private final String[] TYPES = {"pdf"};
	
	public PDFReader() {
		filePath = null;
		fileSet = false;
	}

	@Override
	public List<Term> read() throws IOException{
		if(!fileSet) {
			throw new IOException("The file path hasn't been set");
		}
		try(PDDocument document = PDDocument.load(new File(filePath))) {
			PDFTextStripper stripper = new PDFTextStripper();
			String fullText = stripper.getText(document);
			System.out.println(fullText);
		}
		List<Term> terms = new ArrayList<>();
		
		return terms;
	}

	@Override
	public boolean setFilePath(String path) throws IOException {
		boolean valid = false;
		for(String s : TYPES) {
			if(path.endsWith(s)) {
				this.filePath = path;
				valid = true;
				fileSet = true;
				break;
			}
		}
		if(!valid) {
			throw new IOException("Invalid file type");
		}
		return valid;
	}

}
