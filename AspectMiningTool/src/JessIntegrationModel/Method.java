package JessIntegrationModel;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.util.MethodFormater;

public class Method extends Results{
	
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

		//int index = class_id.indexOf("/");
		String paquete = MethodFormater.formatPackageFromMethodId(id);
		//class_id.substring(0, index);
		String clase =MethodFormater.formatClassFromMethodId(id); 
			//class_id.substring(index+1, class_id.length());
		String p = MethodFormater.formatParameters(parametros);
		String rt;
		if (returnType != null)
			rt = MethodFormater.formatParameters(returnType);
		else
			rt = "";
		String st = this.name+"("+p+")"+":"+ rt +   "  Clase: "+clase+"   Paquete: "+ paquete;
		return st;
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

	@Override
	public String getSearchData() {
		return name.toLowerCase();
	}

	@Override
	public String getOpenableData() {
		return class_id;
	}

	

}
