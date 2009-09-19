package aspectminingtool.JessIntegrationModel.RedireccionFinder;

import java.io.BufferedWriter;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.InferenceEngine.InferenceEngine;

public class RedirectionFinderModel implements IResultsModel{


	private InferenceEngine inferenceEngine = null;
	private ProjectModel projectModel = null;
	

	public RedirectionFinderModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();
		contructModel(pm);
	}

	
	private void contructModel(ProjectModel projectModel){
		
		setProjectModel(projectModel);
		


		
	}


	public void setProjectModel(ProjectModel projectModel) {
		
		this.projectModel = projectModel;
	}


	@Override
	public void generateArchive(BufferedWriter archive) {


		
	}


	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ProjectModel getProjectModel() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
