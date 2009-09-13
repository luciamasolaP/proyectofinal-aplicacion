package JessIntegrationModel;

import java.io.BufferedWriter;
import java.io.FileWriter;

public interface IResultsModel {

	ProjectModel getProjectModel();
	String getId();
	void generateArchive(BufferedWriter archive);
	
}
