package aspectminingtool.JessIntegrationModel.FlowGraph;

public class OutsideAfterExecution {

	private String call_id;
	private String call_id2;
	
	public OutsideAfterExecution(String call_id, String call_id2) {
		this.call_id = call_id;
		this.call_id2 = call_id2;
	}

	public String getCall_id() {
		return call_id;
	}

	public void setCall_id(String call_id) {
		this.call_id = call_id;
	}

	public String getCall_id2() {
		return call_id2;
	}

	public void setCall_id2(String call_id2) {
		this.call_id2 = call_id2;
	}
	
	public String toString(){
		return call_id + " <- " + call_id2;
	}

}
