package aspectminingtool.JessIntegrationModel.RedireccionFinder;

import java.util.ArrayList;
import java.util.List;


import aspectminingtool.model.Call_Counted;
import aspectminingtool.util.MethodFormater;
import aspectminingtool.JessIntegrationModel.Results;

public class RedirectorFinderResults extends Results{

	String claseLlamadora;
	String cantTotal;
	String claseLlamada;
	String cantidadRedireccionados;
	List<Call_Counted> llamados = new ArrayList<Call_Counted>();

	public RedirectorFinderResults(String claseLlamadora, String cantTotal,
			String claseLlamada, String cantidadRedireccionados,
			List<Call_Counted> llamados) {
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

	public List<Call_Counted> getLlamados() {
		return llamados;
	}

	public void setLlamados(List<Call_Counted> llamados) {
		this.llamados = llamados;
	}

	public Double getPercent() {
		return Double.valueOf(cantidadRedireccionados)
				/ Double.valueOf(cantTotal);
	}

	@Override
	public String getSearchData() {
		return MethodFormater.getClassNameFromClassId(claseLlamadora).toLowerCase();
	}

	@Override
	public String getOpenableData() {
		return claseLlamadora;
	}
	
	public String toString(){
		return "Clase Base: " +MethodFormater.formatClassIdToString(claseLlamadora)+ "    Clase redireccionada: " + MethodFormater.formatClassIdToString(claseLlamada) + "    %:" + getPercent();
	}

	public boolean equals(Object obj){
		RedirectorFinderResults r2 = (RedirectorFinderResults)obj;
		if (	claseLlamadora.equals(r2.getClaseLlamadora()) &&
				claseLlamada.equals(r2.getClaseLlamada()) &&
				cantidadRedireccionados.equals(r2.getCantidadRedireccionados()) &&
				getPercent().equals(r2.getPercent()) )
			return true;
		return false;
	}


}
