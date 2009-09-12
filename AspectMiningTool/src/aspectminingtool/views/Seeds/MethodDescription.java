package aspectminingtool.views.Seeds;

import JessIntegrationModel.Method;


public class MethodDescription {

	private Method method = null;
	private String description 	= "";


	/**
	 * Create a task with an initial description
	 * 
	 * @param string
	 */
	public MethodDescription(String string) {
		
		super();
		setDescription(string);
	}


	/**
	 * @return String task description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Set the 'description' property
	 * 
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}


	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
