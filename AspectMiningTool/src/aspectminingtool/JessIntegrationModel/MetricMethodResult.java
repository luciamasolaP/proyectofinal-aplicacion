package aspectminingtool.JessIntegrationModel;

import JessIntegrationModel.Method;

public class MetricMethodResult extends Results{

	private Method metodo;
	private String metric;

		
	public MetricMethodResult(Method metodo, String metric) {
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

	@Override
	public String getSearchData() {
		return metodo.getName().toLowerCase();
	}
	
}
