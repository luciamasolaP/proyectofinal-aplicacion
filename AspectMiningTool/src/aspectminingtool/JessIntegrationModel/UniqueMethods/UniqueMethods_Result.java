package aspectminingtool.JessIntegrationModel.UniqueMethods;

import aspectminingtool.JessIntegrationModel.Results;
import JessIntegrationModel.Method;

public class UniqueMethods_Result extends Results{

	private Method metodo;
	private String metric;

		
	public UniqueMethods_Result(Method metodo, String metric) {
		super();
		this.metodo = metodo;
		this.metric = metric;
	}
	
	public Method getMetodo() {
		return metodo;
	}
	public void setMetodo(Method metodo) {
		this.metodo = metodo;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	
	public String toString(){
		return metodo.toString() + " FanIn Metric " + metric + ")";
	}

	@Override
	public String getSearchData() {
		return metodo.getName().toLowerCase();
	}
	
}
