package model;

public class Define_In {

    
    String method_id;    
    String class_id;
    
    public Define_In(String method_id, String class_id){
    	this.method_id = method_id;
    	this.class_id = class_id;
    }
    
	public String getMethod_id() {
		return method_id;
	}
	public void setMethod_id(String method_id) {
		this.method_id = method_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
    
	public String toString(){
		String st = "Define_In("+this.method_id+","+this.class_id+")";
		return st;
	}
    
	
}
