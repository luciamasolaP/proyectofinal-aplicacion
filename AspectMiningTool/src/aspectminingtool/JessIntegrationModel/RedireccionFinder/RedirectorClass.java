package aspectminingtool.JessIntegrationModel.RedireccionFinder;

public class RedirectorClass {

	String id;
	String nombre;
	String cantMétodos;
	
	public RedirectorClass(String id, String nombre, String cantMétodos) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.cantMétodos = cantMétodos;
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



	public String getCantMétodos() {
		return cantMétodos;
	}

	public void setCantMétodos(String cantMétodos) {
		this.cantMétodos = cantMétodos;
	}

	
	
}
