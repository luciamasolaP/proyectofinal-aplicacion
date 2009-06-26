package aspectminingtool.data;

import java.util.ArrayList;

public class Fact {

	private String name;
	private ArrayList<String> parameters;
	
	/**
	 * This method initializes a Fact
	 * @param name
	 * 				the name of the fact
	 */
	public Fact(String name){
		this.setName(name);
		parameters = new ArrayList<String>();
	}
	
	public Fact(){
		this.setName("");
		parameters = new ArrayList<String>();
	}

	/**
	 * This method sets the name of the Fact
	 * @param name
	 * 				the name of the
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the name of the Fact
	 * @return name
	 * 				the name of the fact
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the attributes of the fact
	 * @param parameters
	 * 				fact parameters
	 */
	public void setParameters(ArrayList<String> parameters) {
		this.parameters.addAll(parameters);
	}

	/**
	 * This method returns the parameters of the fact 
	 * @return parameters
	 *  				the parameters of the fact
	 */
	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	/**
	 * This method add a single parameter to the Fact
	 * @param param
	 * 				the parameter of the fact
	 */
	public void addParameter(String param){	
		parameters.add(param);
	}
	
	
	
}
