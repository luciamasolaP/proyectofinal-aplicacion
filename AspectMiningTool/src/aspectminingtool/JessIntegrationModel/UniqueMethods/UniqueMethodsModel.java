package aspectminingtool.JessIntegrationModel.UniqueMethods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import aspectminingtool.model.Call_Counted;



public class UniqueMethodsModel implements IResultsModel{

	Map<String,List<Call_Counted>> calls;
	Map<String,Final_UniqueMehtods_metric> metrics;
	Map<String,Method> methods;
	ProjectModel projectModel;
	List<UniqueMethods_Result> resultadoUniqueMethods = new ArrayList<UniqueMethods_Result>();
	InferenceEngine inferenceEngine = null;
	

	public UniqueMethodsModel(Map<String,Final_UniqueMehtods_metric> metrics,
			Map<String,Method> methods, Map<String,List<Call_Counted>> calls, ProjectModel pm) {
		super();
		this.metrics = metrics;
		this.methods = methods;
		this.calls = calls;
		this.projectModel = pm;

		
	}

	public UniqueMethodsModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();
		this.metrics = new HashMap<String,Final_UniqueMehtods_metric>();
		this.methods = new HashMap<String,Method>();
		this.calls = new HashMap<String,List<Call_Counted>>();
		this.projectModel = null;
		this.inferenceEngine = inferenceEngine;
		contructModel(pm);
	}
	
	public Map<String,Final_UniqueMehtods_metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(Map<String,Final_UniqueMehtods_metric> metrics) {
		this.metrics = metrics;
	}

	public Map<String,Method> getMethods() {
		return methods;
	}

	public void setMethods(Map<String,Method> methods) {
		this.methods = methods;
	}

	public Map<String,List<Call_Counted>> getCalls() {
		return calls;
	}

	public void setCalls(Map<String,List<Call_Counted>> calls) {
		this.calls = calls;
	}


	public void addCallCounted(Call_Counted cc){
		String id = cc.getCalle_id();
		List<Call_Counted> call = calls.get(id);

		if (call == null){
			call = new ArrayList<Call_Counted>();
		}
		

		call.add(cc);
		
		
		calls.remove(id);
		calls.put(id, call);
	}
	
	public void addFanInMetric(Final_UniqueMehtods_metric ff){

		this.metrics.put(ff.getMetodo(), ff);
		
	}
	
	public void addMethod(Method m){
		this.methods.put(m.getId(),m);
	}

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
	
	
	private void contructModel(ProjectModel projectModel){
		
		setProjectModel(projectModel);
		contructResul(inferenceEngine);
		contructCalls(inferenceEngine);
		

		
	}

	/**
	 * Constructs the Method, Metrics and Final Results.
	 * @param engine
	 */
	private void contructResul(InferenceEngine engine) {
		
		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("UniqueMethods", new ValueVector().add(""));
			 while (result.next()) {
				 
				    String methodId = result.getString("mi");
				    Method method = new Method(methodId, result.getString("name"), result.getString("*x*"), result.getString("class"), result.getString("parametros"));
				    addMethod(method);
				    
				    String fanInValue = result.getString("m");
				    Final_UniqueMehtods_metric ff = new Final_UniqueMehtods_metric(methodId, fanInValue);
				    
				    addFanInMetric(ff);
				    
				    UniqueMethods_Result fir = new UniqueMethods_Result(method,fanInValue);
					this.resultadoUniqueMethods.add(fir);
				    
				 //Call_Counted cc = new Call_Counted(result.getString("Caller"), result.getString("Method"));
		         //addCallCounted(cc);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
	    {
	        BufferedWriter outfile1 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\llamados.txt"));
	        
	        for (Iterator i = calls.keySet().iterator(); i.hasNext() ; ){
	        	String nombre = (String)i.next();
	        	List<Call_Counted> llamadas = calls.get(nombre);
	        	for (Iterator ii = llamadas.iterator(); ii.hasNext() ; ){
	        		
	        		Call_Counted ll = (Call_Counted)ii.next();
	        		outfile1.write(ll.toString());
					outfile1.newLine();       		
	        		
	        	}

			}
	       
	     
	        outfile1.close();
	    }
	    catch (IOException e)    {    }
		

	}
	
	/**
	 * Constructs the calls HashTable with all the calls made to unique Methods.
	 * @param engine
	 * @return
	 */
	private Map<String, List<Call_Counted>> contructCalls(InferenceEngine engine) {
		
		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("llamados", new ValueVector().add(""));
			 while (result.next()) {
				 Call_Counted cc = new Call_Counted(result.getString("Caller"), result.getString("Method"));
		         addCallCounted(cc);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
	    {
	        BufferedWriter outfile1 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\llamados.txt"));
	        
	        for (Iterator i = calls.keySet().iterator(); i.hasNext() ; ){
	        	String nombre = (String)i.next();
	        	List<Call_Counted> llamadas = calls.get(nombre);
	        	for (Iterator ii = llamadas.iterator(); ii.hasNext() ; ){
	        		
	        		Call_Counted ll = (Call_Counted)ii.next();
	        		outfile1.write(ll.toString());
					outfile1.newLine();       		
	        		
	        	}

			}
	       
	     
	        outfile1.close();
	    }
	    catch (IOException e)    {    }
		
		return calls;
	}
	
	public List<UniqueMethods_Result> getResultadoFanIn() {
		return resultadoUniqueMethods;
	}

	public void setResultadoFanIn(List<UniqueMethods_Result> resultadoFanIn) {
		this.resultadoUniqueMethods = resultadoFanIn;
	}


	@Override
	public void generateArchive(BufferedWriter archive) {
		try 
	    {
			for (Iterator<UniqueMethods_Result> i = resultadoUniqueMethods.iterator(); i.hasNext() ;){
				UniqueMethods_Result umr = i.next(); 
				Method m = umr.getMetodo();
				String metric = umr.getMetric();
				archive.write("Método: " + m.toString() + "    FanIn: "+ metric);
				archive.newLine();
				if (!metric.equals("0")){
					archive.write("         Llamadas:");
					archive.newLine();
					List<Call_Counted> list = calls.get(m.getId());
					for (Iterator<Call_Counted> ii = list.iterator() ; ii.hasNext() ;){
						archive.write("                 " + ii.next().toString());
						archive.newLine();
					}
				}
				archive.newLine();
					
			}

	        archive.close();
	    }
	    catch (IOException e)    {    }
		
	}
	
	
}
