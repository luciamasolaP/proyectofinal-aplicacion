package aspectminingtool.JessIntegrationModel.GeneralSeeds;

import JessIntegrationModel.Method;

public class Description {
	
	private Method method;
	private String description;
	
	public Description(Method method, String description){
		this.method = method;
		this.description = description;
	}
	
	public Description(){
		this.method = null;
		this.description = "";
	}
	
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
