package aspectminingtool.util;


	public class MethodFormater {
	
	public static String formatParameters(String param) {
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
	
	public static String formatClass(String class_id){
		
		int index = class_id.indexOf("/");
		if (index >=0)
			return class_id.substring(0, index);
		return "";
		
		
		
	}

	public static String formatPackage(String class_id){
		int index = class_id.indexOf("/");
		int index2 = class_id.indexOf("//");
		if (index >= 0 && index2 >= 0)
			return class_id.substring(index+1, index2);
		return "";
		
	}
	
	public static String formatMethodName(String class_id){
		
		int index = class_id.indexOf("//");
		if (index >=0)
			return class_id.substring(class_id.indexOf("//")+2,class_id.indexOf("///"));
		return "";
		
	}
}

	
	