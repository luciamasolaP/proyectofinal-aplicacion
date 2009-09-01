package JessIntegrationModel;

import org.eclipse.jdt.core.IJavaProject;

public class ProjectModel {

	IJavaProject project;
	String name;
	
	public ProjectModel(IJavaProject iproject) {
		project = iproject;
		name = project.getElementName();
	}

	public IJavaProject getProject() {
		return project;
	}

	public void setProject(IJavaProject project) {
		this.project = project;
	}
	
	public String getName(){
		return name;
	}
	
	

}
