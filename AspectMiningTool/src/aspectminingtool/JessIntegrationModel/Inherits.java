package model;

public class Inherits {

	String child_id;   
    String father_id;
    
    public Inherits(String child_id, String father_id){
    	this.child_id = child_id;
    	this.father_id = father_id;
    }
    
	public String getChild_id() {
		return child_id;
	}
	public void setChild_id(String child_id) {
		this.child_id = child_id;
	}
	public String getFather_id() {
		return father_id;
	}
	public void setFather_id(String father_id) {
		this.father_id = father_id;
	}
    
	public String toString(){
		String st = "Inherits("+this.child_id+","+this.father_id+")";
		return st;
	}
	
}
