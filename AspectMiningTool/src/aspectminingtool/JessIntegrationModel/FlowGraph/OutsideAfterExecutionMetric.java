package aspectminingtool.JessIntegrationModel.FlowGraph;

import java.util.List;
import java.util.ArrayList;

import JessIntegrationModel.Method;

public class OutsideAfterExecutionMetric {
	
	private Method method;
	private int metric;
	//private List<String> relatedMethods;
	
	public OutsideAfterExecutionMetric(Method method, int metric){
		this.method = method;
		this.metric = metric;
		//this.relatedMethods = new ArrayList();
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public int getMetric() {
		return metric;
	}

	public void setMetric(int metric) {
		this.metric = metric;
	}

//	public List<String> getRelatedMethods() {
//		return relatedMethods;
//	}
//
//	public void setRelatedMethods(List<String> relatedMethods) {
//		this.relatedMethods = relatedMethods;
//	}

}
