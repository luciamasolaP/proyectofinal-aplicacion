package aspectminingtool.views.Sinergia.Seeds;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.JessIntegrationModel.Sinergia.Seed;

public class SeedDescription extends Results{

	Seed sinergiaSeed;
	String description;
	
	public SeedDescription(Seed sinergiaSeed) {
		super();
		this.sinergiaSeed = sinergiaSeed; 
		this.description = "";
	}
	
	public SeedDescription() {
		super();
		this.sinergiaSeed = null;
		this.description = "";
	}

	public String getMethod() {
		return sinergiaSeed.getMethod();
	}

	public void setMethod(String method) {
		sinergiaSeed.setMethod(method);
	}

	public String getTrust() {
		return sinergiaSeed.getTrust();
	}

	public void setTrust(String trust) {
		sinergiaSeed.setTrust(trust);
	}
	
	public String toString(){
		return sinergiaSeed.toString() + " Description: " + description; 
	}

	@Override
	public String getOpenableData() {
		return sinergiaSeed.getOpenableData();
	}

	@Override
	public String getSearchData() {
		return sinergiaSeed.getSearchData();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
