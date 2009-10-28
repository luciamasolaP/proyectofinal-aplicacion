package aspectminingtool.JessIntegrationModel.GeneralSeeds;

import aspectminingtool.JessIntegrationModel.Results;
import JessIntegrationModel.Method;


public class SeedDescription extends Results{

	private Method method = null;
	private String description 	= "";
	private String algoritmo = "";


	public SeedDescription(Method method, String description, String algoritmo){
		this.method = method;
		this.description = description;
		this.algoritmo = algoritmo;
	}
	
	public SeedDescription(){
		this.method = null;
		this.description = "";
		this.algoritmo = "";
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

	@Override
	public String getSearchData() {
		return method.getName().toLowerCase();
	}

	@Override
	public String getOpenableData() {
		return method.getClass_id();
	}

}
