package model;

public class Method {
	
	String id;
	String name;
	String returnType;

	public Method(String id, String name, String returnType){
		this.id = id;
		this.name = name;
		this.returnType = returnType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public String toString(){
		String st = "Method("+this.id+","+this.name+","+this.returnType+")";
		return st;
	}
	

}
