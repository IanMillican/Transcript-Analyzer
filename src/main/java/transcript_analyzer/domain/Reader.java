package transcript_analyzer.domain;

import java.io.IOException;
import java.util.List;

import transcript_analyzer.domain.core.Term;

public interface Reader {

	public List<Term> read() throws IOException;
	public boolean setFilePath(String path) throws IOException;
	
}
