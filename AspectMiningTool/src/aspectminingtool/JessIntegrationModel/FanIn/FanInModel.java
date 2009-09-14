package aspectminingtool.JessIntegrationModel.FanIn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jess.Filter;
import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;
import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.model.Call_Counted;

public class FanInModel implements IResultsModel{

	Map<String,List<Call_Counted>> calls;
	Map<String,final_fan_in_metric> metrics;
	Map<String,Method> methods;
	ProjectModel projectModel;
	List<Fan_in_Result> resultadoFanIn = new ArrayList<Fan_in_Result>();
	InferenceEngine inferenceEngine = null;
	
	
	public FanInModel(Map<String,final_fan_in_metric> metrics,
			Map<String,Method> methods, Map<String,List<Call_Counted>> calls, ProjectModel pm) {
		super();
		this.metrics = metrics;
		this.methods = methods;
		this.calls = calls;
		this.projectModel = pm;
		
	}

	public FanInModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();
		this.metrics = new HashMap<String,final_fan_in_metric>();
		this.methods = new HashMap<String,Method>();
		this.calls = new HashMap<String,List<Call_Counted>>();
		this.projectModel = null;
		this.inferenceEngine = inferenceEngine;
		contructModel(pm);
	}

	public List<Call_Counted> getCalls(String methodId){
		return calls.get(methodId);
	}
	
	public Map<String,final_fan_in_metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(Map<String,final_fan_in_metric> metrics) {
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
	
	public void addFanInMetric(final_fan_in_metric ff){

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
		
		setMethods(constructMethods(inferenceEngine));
		setCalls(contructCalls(inferenceEngine));
		setMetrics(constructMetrics(inferenceEngine));
		createFanInResul();

		
	}

	private void createFanInResul() {
		
		for (Iterator i = this.methods.keySet().iterator() ; i.hasNext() ; ){
			
			String key = (String)i.next();
			Fan_in_Result fir = new Fan_in_Result(methods.get(key),metrics.get(key).getMetric());
			this.resultadoFanIn.add(fir);
			
		}
		
	}

	public List<Fan_in_Result> getResultadoFanIn() {
		return resultadoFanIn;
	}

	public void setResultadoFanIn(List<Fan_in_Result> resultadoFanIn) {
		this.resultadoFanIn = resultadoFanIn;
	}

	private Map<String, final_fan_in_metric> constructMetrics(InferenceEngine engine) {
		
		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("FinalfanIn", new ValueVector().add(""));
			 while (result.next()) {
				 final_fan_in_metric ff = new final_fan_in_metric(result.getString("metodo"), result.getString("m"));
		         addFanInMetric(ff);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
	    {
	        BufferedWriter outfile = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\fan-in result.txt"));
	        
	        for (Iterator i = metrics.keySet().iterator(); i.hasNext() ; ){
	        	String nombre = (String)i.next();
	        	outfile.write(((final_fan_in_metric)metrics.get(nombre)).toString());
				outfile.newLine();


			}
	       
	     
	        outfile.close();
	    }
	    catch (IOException e)    {    }
		
		return metrics;
	}

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

	private Map<String,Method> constructMethods(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator methodsResult = jessEngine.getObjects(new Filter.ByClass(Method.class));
		
		for (;methodsResult.hasNext();){
			Method m = (Method)methodsResult.next();
			addMethod(m);
		}
		
		
		try 
	    {
	        BufferedWriter outfile2 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\metodos.txt"));
	        
	        for (Iterator i = methods.keySet().iterator(); i.hasNext() ; ){
	        	String nombre = (String)i.next();
	        	Method m = methods.get(nombre);
	        	
	        	outfile2.write(m.toString());
				outfile2.newLine();       		
	        		
	        	}
 
	        outfile2.close();
	    }
	    catch (IOException e)    {    }
		
		return methods;
	}

	@Override
	public void generateArchive(BufferedWriter archive) {
		//TODO
		try 
	    {
			for (Iterator<Fan_in_Result> i = resultadoFanIn.iterator(); i.hasNext() ;){
				Fan_in_Result fir = i.next(); 
				Method m = fir.getMetodo();
				String metric = fir.getMetric();
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
