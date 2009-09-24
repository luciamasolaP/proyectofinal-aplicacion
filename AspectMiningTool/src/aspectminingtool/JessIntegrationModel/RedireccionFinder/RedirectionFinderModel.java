package aspectminingtool.JessIntegrationModel.RedireccionFinder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class RedirectionFinderModel implements IResultsModel{


	private InferenceEngine inferenceEngine = null;
	private ProjectModel projectModel = null;
	private List<RedirectorFinderResults> redirectorFinderResults = new ArrayList<RedirectorFinderResults>(); 

	public RedirectionFinderModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();
		this.inferenceEngine = inferenceEngine;
		contructModel(pm);
	}

	
	
	private void contructModel(ProjectModel projectModel){
		setProjectModel(projectModel);
		createRedirectionResult();
	}


	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}

	private void createRedirectionResult() {
			
		
		Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("cantRedirectorMethods", new ValueVector().add(""));
			 while (result.next()) {
				 String classLlamadora = result.getString("classIdeLlamador");
				 String claseLlamada = result.getString("classIdLlamada");
				 String cant = result.getString("Cant");
				 
				 RedirectorFinderResults redirecMethod = new RedirectorFinderResults(classLlamadora,claseLlamada,cant);
				 
//				 ValueVector params = new ValueVector();
//				 params.add(new Value(classLlamadora, RU.STRING));
//				 params.add(new Value(claseLlamada, RU.STRING));
//				 QueryResult result1 = jessEngine.runQueryStar("redirMethods", params);
//				 
//				 Map<String,String> llamados = new HashMap<String,String>();
//				 
//				 while (result1.next()){
//					  
//					 llamados.put(result1.getString("MetodoLlamador"), result1.getString("MetodoLlamado"));
//				 }
//				 redirecMethod.setLlamados(llamados);
				 this.redirectorFinderResults.add(redirecMethod);
		        }
			 
			 addCantidadTotalMetodos();
			 
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void addCantidadTotalMetodos() {
		
		for (Iterator<RedirectorFinderResults> i = redirectorFinderResults.iterator(); i.hasNext() ; ){
			
			RedirectorFinderResults rm = i.next();
			
			Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
			QueryResult result;
			try {
				result = jessEngine.runQueryStar("cantMetodos", new ValueVector().add(new Value(rm.getClaseLlamadora(), RU.STRING)));
				result.next();
				rm.setCantTotal(result.getString("cant"));
			} catch (JessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



	@Override
	public void generateArchive(BufferedWriter archive) {

		try 
	    {
			for (Iterator<RedirectorFinderResults> i = redirectorFinderResults.iterator(); i.hasNext() ;){
				RedirectorFinderResults rm = i.next(); 
				archive.write("Clase Base: " +rm.getClaseLlamadora()+ "    Clase redireccionada: " + rm.getClaseLlamada() + "    %:" + rm.getPercent());
//				Map<String,String> llamados = rm.getLlamados();
//				for (Iterator<String> ii = llamados.keySet().iterator(); ii.hasNext(); ){
//						archive.newLine();
//						String metodo = ii.next();
//						archive.write("                       : " +MethodFormater.formatMethodId(metodo)+ " -> " + MethodFormater.formatMethodId(llamados.get(metodo)));
//					}
				}
				archive.newLine();
		        archive.close();
					
			}

	    catch (IOException e)    {    }

		
	}

	@Override
	public String getId() {
		return projectModel.getName();
	}


	@Override
	public ProjectModel getProjectModel() {
		return this.projectModel;
	}



	public List<RedirectorFinderResults> getRedirectorFinderResults() {
		return redirectorFinderResults;
	}



	public void setRedirectorFinderResults(
			List<RedirectorFinderResults> redirectorFinderResults) {
		this.redirectorFinderResults = redirectorFinderResults;
	}


	
}
