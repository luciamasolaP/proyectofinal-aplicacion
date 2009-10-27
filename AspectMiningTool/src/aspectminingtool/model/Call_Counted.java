package aspectminingtool.model;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.util.MethodFormater;

public class Call_Counted extends Results{

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
		
		return MethodFormater.formatMethodIdToString(caller_id)+ " -> " + MethodFormater.formatMethodIdToString(calle_id);

	}
	
	public String callerToString(){
		return MethodFormater.formatMethodIdToString(caller_id);
	}
	
	public String calleeToString(){
		return MethodFormater.formatMethodIdToString(calle_id);
	}

	@Override
	public String getOpenableData() {
		return MethodFormater.getClassIdFromMethodId(caller_id);
	}

	@Override
	public String getSearchData() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
