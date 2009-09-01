package JessIntegrationModel;

public class Abstract {

	String id;
	String name;
		
	public Abstract(String id, String name){
		this.id = id;
		this.name = name;
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
	
	public String toString(){
		String st = "Abstract("+this.id+","+this.name+")";
		return st;
	}
	
}
