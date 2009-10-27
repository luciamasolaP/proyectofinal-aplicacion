package aspectminingtool.JessIntegrationModel.FlowGraph;

import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.Results;

public class OutsideAfterExecutionMetric extends Results{
	
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

	@Override
	public String getSearchData() {
		return method.getName().toLowerCase();
	}

//	public List<String> getRelatedMethods() {
//		return relatedMethods;
//	}
//
//	public void setRelatedMethods(List<String> relatedMethods) {
//		this.relatedMethods = relatedMethods;
//	}

}
