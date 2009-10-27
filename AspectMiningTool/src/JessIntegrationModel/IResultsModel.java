package JessIntegrationModel;

import java.io.BufferedWriter;

public interface IResultsModel {

	ProjectModel getProjectModel();
	String getId();
	void generateArchive(BufferedWriter archive);
	
}
