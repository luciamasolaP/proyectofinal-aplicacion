package aspectminingtool.JessIntegrationModel.Sinergia;

public class OutsideAfterExecutionMetric {

	String method;
	String metric;
	
	public OutsideAfterExecutionMetric(String metodo, String metrica) {
		super();
		this.method = metodo;
		this.metric = metrica;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}



}
