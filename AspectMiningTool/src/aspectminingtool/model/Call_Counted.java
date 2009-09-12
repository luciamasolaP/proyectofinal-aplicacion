package aspectminingtool.model;

public class Call_Counted {

	private String caller_id;
	private String calle_id;
	
	public Call_Counted(String caller_id, String calle_id) {
		super();
		this.caller_id = caller_id;
		this.calle_id = calle_id;
	}

	public String getCaller_id() {
		return caller_id;
	}

	public void setCaller_id(String caller_id) {
		this.caller_id = caller_id;
	}

	public String getCalle_id() {
		return calle_id;
	}

	public void setCalle_id(String calle_id) {
		this.calle_id = calle_id;
	}
	
	public String toString(){
		return "Call_counted(" + calle_id + ", " + caller_id + ")";
	}
	
}
