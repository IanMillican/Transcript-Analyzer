package transcript_analyzer.dataaccess;

import java.io.IOException;
import java.util.List;

import transcript_analyzer.domain.Reader;
import transcript_analyzer.domain.core.Term;

public class PDFReader implements Reader{
	
	private String filePath;
	private final String[] TYPES = {"pdf"};

	@Override
	public List<Term> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setFilePath(String path) throws IOException {
		boolean valid = false;
		for(String s : TYPES) {
			if(path.endsWith(s)) {
				this.filePath = path;
				valid = true;
				break;
			}
		}
		if(!valid) {
			throw new IOException("Invalid file type");
		}
		return valid;
	}

}
