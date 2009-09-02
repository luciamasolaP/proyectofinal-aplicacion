package aspectminingtool.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IJavaProject;

import JessIntegrationModel.ProjectModel;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;

public class AspectMiningModel {

	private static List<ProjectModel> projectsModel = new ArrayList<ProjectModel>();
	
	public static InferenceEngine getInferenceEngine(){
		return new JessInferenceEngine();
	}
	
	public static ProjectModel createProjectModel(IJavaProject iproject){
		ProjectModel projectModel = new ProjectModel(iproject);
		projectsModel.add(projectModel);
		return projectModel;
	}
	
	
	
}
