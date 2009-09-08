package aspectminingtool.JessIntegrationModel.UniqueMethods;

public class Final_UniqueMehtods_metric {

	private String metodo;
	private String metric;

		
	public Final_UniqueMehtods_metric(String metodo, String metric) {
		super();
		this.metodo = metodo;
		this.metric = metric;
	}
	
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	
	public String toString(){
		return "metodo(" + metodo + ", " + metric + ")";
	}
	
}
