package aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;


public class SeedClassDescription extends Results{

	private RedirectorFinderResults redirResul = null;
	private String description 	= "";
	private String algoritmo = "";


	public SeedClassDescription(RedirectorFinderResults redirResul, String description, String algoritmo){
		this.redirResul = redirResul;
		this.description = description;
		this.algoritmo = algoritmo;
	}
	
	public SeedClassDescription(){
		this.redirResul = null;
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

	
	public RedirectorFinderResults getRedirResul() {
		return redirResul;
	}

	public void setRedirResul(RedirectorFinderResults redirResul) {
		this.redirResul = redirResul;
	}

	public String getAlgoritmo() {
		return algoritmo;
	}


	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}


	public String toString(){
		return redirResul.toString() + "          Descripción: " + description + "          Algoritmo:" + algoritmo;
	}

	@Override
	public String getSearchData() {
		return redirResul.getSearchData();
	}

	@Override
	public String getOpenableData() {
		return redirResul.getOpenableData();
	}

}
