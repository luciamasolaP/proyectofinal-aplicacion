package aspectminingtool.JessIntegrationModel.FlowGraph;

public class InsideFirstExecution {

	private String call_id;
	private String method_id;
	
	public InsideFirstExecution(String call_id, String method_id) {
		this.call_id = call_id;
		this.method_id = method_id;
	}

	public String getCall_id() {
		return call_id;
	}

	public void setCall_id(String call_id) {
		this.call_id = call_id;
	}

	public String getMethod_id() {
		return method_id;
	}

	public void setMethod_id(String method_id) {
		this.method_id = method_id;
	}

}
