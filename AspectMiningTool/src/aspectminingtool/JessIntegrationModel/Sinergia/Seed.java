package aspectminingtool.JessIntegrationModel.Sinergia;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.util.MethodFormater;

public class Seed extends Results{

	String method;
	String trust;
	
	public Seed(String method, String trust) {
		super();
		this.method = method;
		this.trust = trust;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTrust() {
		return trust;
	}

	public void setTrust(String trust) {
		this.trust = trust;
	}
	
	public String toString(){
		return "Seed Méthod: " + MethodFormater.formatMethodIdToString(method) + " Trust Value: " + trust; 
	}

	@Override
	public String getOpenableData() {
		return MethodFormater.getClassIdFromMethodId(method);
	}

	@Override
	public String getSearchData() {
		return MethodFormater.formatMethodName(method).toLowerCase();
	}
	
}
