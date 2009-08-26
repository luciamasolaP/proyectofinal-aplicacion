package model;

public class Method {
	
	String id;
	String name;
	String returnType;
	String class_id;

	
	public Method(String id, String name, String returnType, String class_id){
		this.id = id;
		this.name = name;
		this.returnType = returnType;
		this.class_id = class_id;
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
		String st = "Method("+this.id+","+this.name+","+this.returnType+","+this.class_id+")";
		return st;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}


}
