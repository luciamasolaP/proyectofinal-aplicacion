package JessIntegrationModel;

public class Call {

	String caller_id;  
	String callee_id;
	String precedence;
	String id;
	
	public String getPrecedence() {
		return precedence;
	}

	public void setPrecedence(String precedence) {
		this.precedence = precedence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Call(String caller_id, String calle_id, String precedence, String id){
		this.caller_id = caller_id;
		this.callee_id = calle_id;
		this.precedence = precedence;
		this.id = id;
		System.out.println("Call("+this.caller_id+","+this.callee_id+", "+precedence +", "+id+")");
    }
	
    public String getCaller_id() {
		return caller_id;
	}
	public void setCaller_id(String caller_id) {
		this.caller_id = caller_id;
	}
	public String getCallee_id() {
		return callee_id;
	}
	public void setCallee_id(String callee_id) {
		this.callee_id = callee_id;
	}
	
	public String toString(){
		String st = "Call("+this.caller_id+","+this.callee_id+", "+precedence +", "+id+")";
		return st;
	}
    
	
}
