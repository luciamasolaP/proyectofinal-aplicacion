package aspectminingtool.parser;

import aspectminingtool.data.ProjectData;

public class ProjectInspector {

	private ProjectData projectData;
	private Filter filter;
	
	public ProjectInspector(){
		projectData = ProjectData.getInstace();
		this.filter = null;
	}
	                     
	
	public ProjectInspector(Filter f){
		
		projectData = ProjectData.getInstace();
		setFilter(f);
		
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Filter getFilter() {
		return filter;
	}
	
	public void runMining(){
		//aca va recorrer el proyecto, preguntar al filtro si corresponde o no, y llamar a AbstractASTInspector
	}
	
	
	
}
