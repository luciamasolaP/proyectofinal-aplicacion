package aspectminingtool.JessIntegrationModel.RedireccionFinder;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jess.JessException;
import jess.QueryResult;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;
import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.JessIntegrationModel.FanIn.final_fan_in_metric;

public class RedirectionFinderModel implements IResultsModel{


	private InferenceEngine inferenceEngine = null;
	private ProjectModel projectModel = null;
	private Map<JessIntegrationModel.Class, RedirectorMethods> clasesRedireccionadoras = new HashMap<JessIntegrationModel.Class, RedirectorMethods>();
	private List<RedirectorClass> redirectionClases = new ArrayList<RedirectorClass>();

	

	public RedirectionFinderModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();
		contructModel(pm);
	}

	
	
	private void contructModel(ProjectModel projectModel){
		
		setProjectModel(projectModel);
		createRedirectionClases();
		


		
	}


	public void setProjectModel(ProjectModel projectModel) {
		
		this.projectModel = projectModel;
	}


	private void createRedirectionClases(){
		
		Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("cantRedirectorMethods", new ValueVector().add(""));
			 while (result.next()) {
				 String classId = result.getString("idClase");
				 String cantMetodo = result.getString("cant");
				 QueryResult result1 = jessEngine.runQueryStar("Clase", new ValueVector().add(new Value(classId, RU.STRING)));
				 RedirectorClass redirectorClass = new RedirectorClass(classId,result1.getString("nombre"),cantMetodo); 
				 this.redirectionClases.add(redirectorClass);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void generateArchive(BufferedWriter archive) {


		
	}


	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ProjectModel getProjectModel() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
