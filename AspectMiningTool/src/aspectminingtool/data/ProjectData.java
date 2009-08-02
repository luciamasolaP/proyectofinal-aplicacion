package aspectminingtool.data;

public class ProjectData {

	private static ProjectData instance = null;
	
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
