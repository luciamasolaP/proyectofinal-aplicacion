package aspectminingtool.JessIntegrationModel.RedireccionFinder;

import java.util.HashMap;
import java.util.Map;

public class RedirectorFinderResults {

	String claseLlamadora;
	String cantTotal;
	String claseLlamada;
	String cantidadRedireccionados;
	Map<String,String> llamados = new HashMap<String,String>();
	
	public RedirectorFinderResults(String claseLlamadora, String cantTotal,
			String claseLlamada, String cantidadRedireccionados,
			Map<String, String> llamados) {
		super();
		this.claseLlamadora = claseLlamadora;
		this.cantTotal = cantTotal;
		this.claseLlamada = claseLlamada;
		this.cantidadRedireccionados = cantidadRedireccionados;
		this.llamados = llamados;
	}

	public RedirectorFinderResults(String classLlamadora, String claseLlamada,
			String cantRedireccionados) {
		
		this.claseLlamadora = classLlamadora;
		this.cantidadRedireccionados = cantRedireccionados;
		this.claseLlamada = claseLlamada;
		this.cantTotal = "";
		this.claseLlamada = claseLlamada;

	}

	public String getClaseLlamadora() {
		return claseLlamadora;
	}

	public void setClaseLlamadora(String claseLlamadora) {
		this.claseLlamadora = claseLlamadora;
	}

	public String getCantTotal() {
		return cantTotal;
	}

	public void setCantTotal(String cantTotal) {
		this.cantTotal = cantTotal;
	}

	public String getClaseLlamada() {
		return claseLlamada;
	}

	public void setClaseLlamada(String claseLlamada) {
		this.claseLlamada = claseLlamada;
	}

	public String getCantidadRedireccionados() {
		return cantidadRedireccionados;
	}

	public void setCantidadRedireccionados(String cantidadRedireccionados) {
		this.cantidadRedireccionados = cantidadRedireccionados;
	}

	public Map<String, String> getLlamados() {
		return llamados;
	}

	public void setLlamados(Map<String, String> llamados) {
		this.llamados = llamados;
	}
	
	public Double getPercent(){
		return Double.valueOf(cantidadRedireccionados)/Double.valueOf(cantTotal);
	}
	



	
	
}
