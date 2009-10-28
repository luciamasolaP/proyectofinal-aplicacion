package aspectminingtool.JessIntegrationModel.GeneralSeeds;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.util.MethodFormater;


public class RelatedMethodDescription extends Results{

	String relatedMethod;
	String selected;
	String Description;
	
	public RelatedMethodDescription(String relatedMethod) {
		super();
		this.relatedMethod = relatedMethod;
		this.selected = "yes";
		Description = "";
	}
	
	public RelatedMethodDescription() {
		super();
		this.relatedMethod = null;
		this.selected = "";
		Description = "";
	}

	
	public String getRelatedMethod() {
		return relatedMethod;
	}

	public void setRelatedMethod(String relatedMethod) {
		this.relatedMethod = relatedMethod;
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
		
		return relatedMethod + "\n" +  "                      Seleccionado: " + selected + "\n" +  "                      Descripción: " + Description; 
		
	}

	@Override
	public String getOpenableData() {
		return MethodFormater.getClassIdFromMethodId(relatedMethod);
	}

	@Override
	public String getSearchData() {
		return null;
	}
	
}
