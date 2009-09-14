package JessIntegrationModel;

public class Method {
	
	private String id;
	private String name;
	private String returnType;
	private String class_id;
	private String parametros;

	
	public Method(String id, String name, String returnType, String class_id, String params){
		this.id = id;
		this.name = name;
		this.returnType = returnType;
		this.class_id = class_id;
		this.parametros = params;
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

		int index = class_id.indexOf("/");
		String paquete = class_id.substring(0, index);
		String clase = class_id.substring(index+1, class_id.length());
		String p = formatParameters(parametros);
		String rt = formatParameters(returnType);
		String st = this.name+"("+p+")"+":"+ rt +   "  Clase: "+clase+"   Paquete: "+ paquete;
		return st;
	}

	private String formatParameters(String param) {
		String resul = "";
		String aux = param;
		while (aux.length() > 0 ){
			int index = aux.indexOf("-");
			String aux1;
			if (index > 0)
				aux1 = aux.substring(0, index);
			else
				aux1 = aux;
				
			String p1 = aux1.substring(aux1.lastIndexOf(".")+1);
			resul = resul + "," + p1;
			if (index > 0)
				aux = aux.substring(index+1);
			else
				aux = "";
		}
		resul = resul.replaceFirst(",", "");
		return resul;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	
	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	

}
