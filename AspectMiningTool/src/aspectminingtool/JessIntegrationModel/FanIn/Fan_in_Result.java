package aspectminingtool.JessIntegrationModel.FanIn;

import JessIntegrationModel.Method;

public class Fan_in_Result {

	private Method metodo;
	private String metric;

		
	public Fan_in_Result(Method metodo, String metric) {
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
		return metodo.toString();
	}
	
}
