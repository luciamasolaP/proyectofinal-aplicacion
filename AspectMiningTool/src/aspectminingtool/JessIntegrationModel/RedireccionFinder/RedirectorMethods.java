package aspectminingtool.JessIntegrationModel.RedireccionFinder;

import java.util.HashMap;
import java.util.Map;

import JessIntegrationModel.Method;

public class RedirectorMethods {

	String claseLlamadora;
	String claseLlamada;
	String cant;
	Map<Method,Method> llamados = new HashMap<Method,Method>();
	
	public RedirectorMethods(String claseLlamadora, String claseLlamada,
			String cant, Map<Method, Method> llamados) {
		super();
		this.claseLlamadora = claseLlamadora;
		this.claseLlamada = claseLlamada;
		this.cant = cant;
		this.llamados = llamados;
	}
	
	public RedirectorMethods() {
		super();
		this.claseLlamadora = "";
		this.claseLlamada = "";
		this.cant = "";
		this.llamados = null;
	}
	

	public String getClaseLlamadora() {
		return claseLlamadora;
	}
	public void setClaseLlamadora(String claseLlamadora) {
		this.claseLlamadora = claseLlamadora;
	}
	public String getClaseLlamada() {
		return claseLlamada;
	}
	public void setClaseLlamada(String claseLlamada) {
		this.claseLlamada = claseLlamada;
	}
	public String getCant() {
		return cant;
	}
	public void setCant(String cant) {
		this.cant = cant;
	}
	public Map<Method, Method> getLlamados() {
		return llamados;
	}
	public void setLlamados(Map<Method, Method> llamados) {
		this.llamados = llamados;
	}
	
	
	
}
