package aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.model.Call_Counted;


public class RelatedCallCountedDescription extends Results{

	Call_Counted relatedCall;
	String selected;
	String Description;
	
	public RelatedCallCountedDescription(Call_Counted relatedCall) {
		super();
		this.relatedCall = relatedCall;
		this.selected = "yes";
		Description = "";
	}
	
	public RelatedCallCountedDescription() {
		super();
		this.relatedCall = null;
		this.selected = "";
		Description = "";
	}

	
	public Call_Counted getRelatedCall() {
		return relatedCall;
	}

	public void setRelatedCall(Call_Counted relatedMethod) {
		this.relatedCall = relatedMethod;
	}

	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public void changeSelected(){
		if (selected.equals("yes"))
			selected = "no";
		else
		selected = "yes";
	}
	

	public String toString(){
		
		return relatedCall.toString() + "\n" +  "                      Seleccionado: " + selected + "\n" +  "                      Descripción: " + Description; 
		
	}

	@Override
	public String getOpenableData() {
		return relatedCall.getOpenableData();
	}

	@Override
	public String getSearchData() {
		return null;
	}
	
}
