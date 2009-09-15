package aspectminingtool.model;

import aspectminingtool.util.MethodFormater;

public class Call_Counted {

	private String caller_id;
	private String calle_id;
	
	public Call_Counted(String caller_id, String calle_id) {
		super();
		this.caller_id = caller_id;
		this.calle_id = calle_id;
	}

	public String getCaller_id() {
		return caller_id;
	}

	public void setCaller_id(String caller_id) {
		this.caller_id = caller_id;
	}

	public String getCalle_id() {
		return calle_id;
	}

	public void setCalle_id(String calle_id) {
		this.calle_id = calle_id;
	}
	
	public String toString(){
		
		String paquete = MethodFormater.formatPackage(caller_id);
		//class_id.substring(0, index);
		String clase = MethodFormater.formatClass(caller_id); 
		String parameters = caller_id.substring(caller_id.indexOf("///")+3); 
		parameters = MethodFormater.formatParameters(parameters);
		String name = MethodFormater.formatMethodName(caller_id);
		return name + "(" + parameters + "):" +"  Clase: "+clase+"   Paquete: "+ paquete;

//		int index = class_id.indexOf("/");
//		String paquete = class_id.substring(0, index);
//		String clase = class_id.substring(index+1, class_id.length());
//		String p = MethodFormater.formatParameters(parametros);
//		String rt = MethodFormater.formatParameters(parametros);
//		String st = this.name+"("+p+")"+":"+ rt +   "  Clase: "+clase+"   Paquete: "+ paquete;
//		return st;
		
		//return "Call_counted(" + calle_id + ", " + caller_id + ")";
	}
	
}
