package JessIntegrationModel;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;


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
	
	/**
	 * Returns the resource associated with the parameter name
	 * @param name
	 * @return
	 */
	public IResource getAssociatedResource(String name){
		
		name = name.replace(".", "/");
		
		IPackageFragment[] fragments;
		try {
			fragments = project.getPackageFragments();
			for (int i = 0; i < fragments.length; i++) {
				
				
				ICompilationUnit[] compUnit = fragments[i].getCompilationUnits();

				for (int y = 0; y < compUnit.length; y++) {
									
					String path = compUnit[y].getPath().toString(); 
					if (path.contains(name)){
						IResource resource = compUnit[y].getCorrespondingResource();
						return resource;
					}
				}

			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	

}
