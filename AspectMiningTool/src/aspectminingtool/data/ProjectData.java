package aspectminingtool.data;

import java.util.ArrayList;

import org.eclipse.jdt.core.IJavaProject;

public class ProjectData {

	private static ProjectData instance = null;
	private IJavaProject project;
	private ArrayList excludesCompilations;
	
	/**
	 * Initializes the singleton
	 */
	private ProjectData(){
	}
	
	public static ProjectData getInstance(){
		if (instance == null)
			instance = new ProjectData();
		
		return instance;	
			
	}
	

	
}
