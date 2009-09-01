package model;

public class Call {

	String caller_id;  
	String callee_id;
	
	public Call(String caller_id, String calle_id){
		this.caller_id = caller_id;
		this.callee_id = calle_id;
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
		String st = "Call("+this.caller_id+","+this.callee_id+")";
		return st;
	}
    
	
}
