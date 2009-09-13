package aspectminingtool.JessIntegrationModel.Seeds;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.model.Call_Counted;

public class SeedsModel implements IResultsModel{

	Map<String,List<Call_Counted>> calls;
	List<Description> description;
	ProjectModel projectModel;
	
	
	public SeedsModel(Map<String,List<Call_Counted>> calls, List<Description> description, ProjectModel pm) {
		super();
		this.calls = calls;
		this.projectModel = pm;
		this.description = description;
		
	}

	public SeedsModel(ProjectModel pm) {
		this.calls = new HashMap<String,List<Call_Counted>>();
		this.projectModel = pm;
		this.description = new ArrayList<Description>();
		//contructModel(pm);
	}
	
	public List<Description> getDescription() {
		return description;
	}

	public void setDescription(List<Description> description) {
		this.description = description;
	}

	public Map<String,List<Call_Counted>> getCalls() {
		return calls;
	}

	public void setCalls(Map<String,List<Call_Counted>> calls) {
		this.calls = calls;
	}


//	public void addCallCounted(Call_Counted cc){
//		String id = cc.getCalle_id();
//		List<Call_Counted> call = calls.get(id);
//
//		if (call == null){
//			call = new ArrayList<Call_Counted>();
//		}
//		
//		call.add(cc);
//		
//		calls.remove(id);
//		calls.put(id, call);
//	}

	@Override
	public String getId() {
		return projectModel.getName();
	}


	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}


	@Override
	public void generateArchive(BufferedWriter archive) {
		// TODO Auto-generated method stub
		
	}
	
	
}