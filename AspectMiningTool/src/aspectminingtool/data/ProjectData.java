package aspectminingtool.data;

public class ProjectData {

	private static ProjectData instance = null;
	
	private ProjectData(){
	}
	
	public static ProjectData getInstace(){
		if ( instance == null)
			instance = new ProjectData();
		
		return instance;
	}
	
}
