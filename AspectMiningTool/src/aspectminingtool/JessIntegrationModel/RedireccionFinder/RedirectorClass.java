package aspectminingtool.JessIntegrationModel.RedireccionFinder;

public class RedirectorClass {

	String id;
	String nombre;
	String cantM�todos;
	
	public RedirectorClass(String id, String nombre, String cantM�todos) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.cantM�todos = cantM�todos;
	}


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getCantM�todos() {
		return cantM�todos;
	}

	public void setCantM�todos(String cantM�todos) {
		this.cantM�todos = cantM�todos;
	}

	
	
}
