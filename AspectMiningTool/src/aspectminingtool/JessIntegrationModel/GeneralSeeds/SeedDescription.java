package aspectminingtool.JessIntegrationModel.GeneralSeeds;

import JessIntegrationModel.Method;


public class SeedDescription {

	private Method method = null;
	private String description 	= "";
	private String algoritmo = "";


	public SeedDescription() {
		
		super();
		
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
	
	public String getAlgoritmo() {
		return algoritmo;
	}


	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}


	public String toString(){
		return method.toString() + "          Descripción: " + description + "          Algoritmo:" + algoritmo;
	}

}
