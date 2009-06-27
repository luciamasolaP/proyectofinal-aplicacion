package aspectminingtool.data;

import java.util.ArrayList;

public class Fact {

	private String name;
	private ArrayList<String> parameters;
	
	/**
	 * Initializes a Fact
	 * @param name
	 * 				the name of the fact
	 */
	public Fact(String name){
		this.setName(name);
		parameters = new ArrayList<String>();
	}
	
	/**
	 * Initializes a Fact
	 */
	public Fact(){
		this.setName("");
		parameters = new ArrayList<String>();
	}

	/**
	 * Sets the name of the Fact
	 * @param name
	 * 				the name of the
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the Fact
	 * @return name
	 * 				the name of the fact
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the attributes of the fact
	 * @param parameters
	 * 				fact parameters
	 */
	public void setParameters(ArrayList<String> parameters) {
		this.parameters.addAll(parameters);
	}

	/**
	 * Returns the parameters of the fact 
	 * @return parameters
	 *  				the parameters of the fact
	 */
	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	/**
	 * Add a single parameter to the Fact
	 * @param param
	 * 				the parameter of the fact
	 */
	public void addParameter(String param){	
		parameters.add(param);
	}
	
	
	
}
