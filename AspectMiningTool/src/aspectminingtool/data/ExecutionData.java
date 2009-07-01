package aspectminingtool.data;

public class ExecutionData {

	private static ExecutionData instance = null;
	
	/**
	 * Initializes the singleton
	 */
	private ExecutionData(){
	}
	
	public static ExecutionData getInstance(){
		if (instance == null)
			instance = new ExecutionData();
		
		return instance;	
			
	}
	
}


