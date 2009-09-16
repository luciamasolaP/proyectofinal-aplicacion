package aspectminingtool.JessIntegrationModel.Seeds;

import aspectminingtool.model.Call_Counted;

public class CallDescription {

	Call_Counted callCounted;
	String selected;
	String Description;
	
	public CallDescription(Call_Counted callCounted) {
		super();
		this.callCounted = callCounted;
		this.selected = "yes";
		Description = "";
	}
	
	public CallDescription() {
		super();
		this.callCounted = null;
		this.selected = "";
		Description = "";
	}
	
	public Call_Counted getCallCounted() {
		return callCounted;
	}
	public void setCallCounted(Call_Counted callCounted) {
		this.callCounted = callCounted;
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
	
	
}
