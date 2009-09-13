package aspectminingtool.JessIntegrationModel.FlowGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jess.Filter;
import jess.Rete;

import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;

import JessIntegrationModel.Call;
import JessIntegrationModel.Method;

public class FlowGraphModel implements IResultsModel {

	List<OutsideBeforeExecution> outsideBeforeExecutionRelations = new ArrayList<OutsideBeforeExecution>();
	List<OutsideAfterExecution> outsideAfterExecutionRelations = new ArrayList<OutsideAfterExecution>();	
	List<InsideFirstExecution> insideFirstExecutionRelations = new ArrayList<InsideFirstExecution>();
	List<InsideLastExecution> insideLastExecutionRelations = new ArrayList<InsideLastExecution>();
	
	List<OutsideBeforeExecutionMetric> flowGraphResult = new ArrayList<OutsideBeforeExecutionMetric>();
	
	public List<OutsideBeforeExecutionMetric> getFlowGraphResult() {
		return flowGraphResult;
	}

	public void setFlowGraphResult(List<OutsideBeforeExecutionMetric> flowGraphResult) {
		this.flowGraphResult = flowGraphResult;
	}

	Map<String,Call> calls;
	Map<String,Method> methods;
	
	ProjectModel projectModel;	
	InferenceEngine inferenceEngine = null;
	
	public FlowGraphModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();		
		this.calls = new HashMap<String,Call>();
		this.methods = new HashMap<String,Method>();
		this.inferenceEngine = inferenceEngine;
		contructModel(pm);
	}
	
	private void contructModel(ProjectModel projectModel){
		
		setProjectModel(projectModel);
		
		constructMethods(inferenceEngine);
		contructCalls(inferenceEngine);	
		constructExecutionRelations(inferenceEngine);
		
	}
	
	
	private void constructMethods(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator methodsResult = jessEngine.getObjects(new Filter.ByClass(Method.class));
		
		for (;methodsResult.hasNext();){
			Method m = (Method)methodsResult.next();
			addMethod(m);			
		}
	}
	
	private void contructCalls(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator callsResult = jessEngine.getObjects(new Filter.ByClass(Call.class));
		
		for (;callsResult.hasNext();){
			Call c = (Call)callsResult.next();
			addCall(c);			
		}
	}
	
	private void constructExecutionRelations(InferenceEngine engine) {
		
		constructOutsideBeforeExecutionRelations(engine);
		constructOutsideAfterExecutionRelations(engine);		
		constructInsideFirstExecutionRelations(engine);
		constructInsideLastExecutionRelations(engine);
		
	}
	
	private void constructOutsideBeforeExecutionRelations(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator relationsResult = jessEngine.getObjects(new Filter.ByClass(OutsideBeforeExecution.class));
		
		for (;relationsResult.hasNext();){
			OutsideBeforeExecution relation = (OutsideBeforeExecution)relationsResult.next();
			outsideBeforeExecutionRelations.add(relation);
		}
	}
	
	private void constructOutsideAfterExecutionRelations(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator relationsResult = jessEngine.getObjects(new Filter.ByClass(OutsideAfterExecution.class));
		
		for (;relationsResult.hasNext();){
			OutsideAfterExecution relation = (OutsideAfterExecution)relationsResult.next();
			outsideAfterExecutionRelations.add(relation);
		}
	}
		
	private void constructInsideFirstExecutionRelations(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator relationsResult = jessEngine.getObjects(new Filter.ByClass(InsideFirstExecution.class));
		
		for (;relationsResult.hasNext();){
			InsideFirstExecution relation = (InsideFirstExecution)relationsResult.next();
			insideFirstExecutionRelations.add(relation);
		}
	}
	
	private void constructInsideLastExecutionRelations(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator relationsResult = jessEngine.getObjects(new Filter.ByClass(InsideLastExecution.class));
		
		for (;relationsResult.hasNext();){
			InsideLastExecution relation = (InsideLastExecution)relationsResult.next();
			insideLastExecutionRelations.add(relation);
		}
	}
	
	public void listResults(){
		
		/*Rete jessEngine = ((JessInferenceEngine) this.inferenceEngine).getEngine();
		Iterator facts = jessEngine.listFacts();
		
		for(;facts.hasNext();){			
			System.out.println(facts.next().toString());
		}
		
		System.out.println();
		int i;
		for(i=0;i<outsideBeforeExecutionRelations.size();i++)
		{
			System.out.println(outsideBeforeExecutionRelations.get(i).toString());
		}
		System.out.println();
		for(i=0;i<outsideAfterExecutionRelations.size();i++)
		{
			System.out.println(outsideAfterExecutionRelations.get(i).toString());
		}
		System.out.println();
		for(i=0;i<insideFirstExecutionRelations.size();i++)
		{
			System.out.println(insideFirstExecutionRelations.get(i).toString());
		}
		System.out.println();
		for(i=0;i<insideLastExecutionRelations.size();i++)
		{
			System.out.println(insideLastExecutionRelations.get(i).toString());
		}*/
		
		Method m = methods.get("/TestClass_2//method_2_1///");
		if(m != null){
			List<Method> result = this.getInsideFirstExecutionMethods(m);
			System.out.println(m.getId());
			System.out.println();
			for(int i=0;i<result.size();i++){
				System.out.println(result.get(i).getId());
			}			
		}
		
	}
	
	/*
	 * Dado un m�todo, devuelve una lista con los m�todos que participan con este en relaciones 
	 * de este tipo.
	 */
	public List<Method> getOutsideBeforeExecutionMethods(Method method){
		
		List<Method> result = new ArrayList<Method>();
		
		Call call_1;
		Call call_2;
		
		for(int i=0;i<outsideBeforeExecutionRelations.size();i++){
			call_1 = calls.get(outsideBeforeExecutionRelations.get(i).getCall_id());
			if(call_1.getCallee_id().equals(method.getId())){
				call_2 = calls.get(outsideBeforeExecutionRelations.get(i).getCall_id2());
				result.add(methods.get(call_2.getCallee_id()));				
			}
		}				
					
		return result;
		
	}
	
	/*
	 * Dado un m�todo, devuelve una lista con los m�todos que participan con este en relaciones
	 * de este tipo.
	 */
	public List<Method> getOutsideAfterExecutionMethods(Method method){
		
		List<Method> result = new ArrayList<Method>();
		
		Call call_1;
		Call call_2;
		
		for(int i=0;i<outsideAfterExecutionRelations.size();i++){
			call_1 = calls.get(outsideAfterExecutionRelations.get(i).getCall_id());
			if(call_1.getCallee_id().equals(method.getId())){
				call_2 = calls.get(outsideAfterExecutionRelations.get(i).getCall_id2());
				result.add(methods.get(call_2.getCallee_id()));				
			}
		}				
					
		return result;
		
	}
	
	/*
	 * Dado un m�todo, devuelve una lista con los m�todos que participan con este en relaciones
	 * de este tipo.
	 */
	public List<Method> getInsideFirstExecutionMethods(Method method){
		
		List<Method> result = new ArrayList<Method>();
		
		Call call_1;		
		
		for(int i=0;i<insideFirstExecutionRelations.size();i++){
			call_1 = calls.get(insideFirstExecutionRelations.get(i).getCall_id());
			if(call_1.getCallee_id().equals(method.getId())){
				result.add(methods.get(insideFirstExecutionRelations.get(i).getMethod_id()));
			}							
			
		}				
					
		return result;
		
	}
	
	/*
	 * Dado un m�todo, devuelve una lista con los m�todos que participan con este en relaciones
	 * de este tipo.
	 */
	public List<Method> getInsideLastExecutionMethods(Method method){
		
		List<Method> result = new ArrayList<Method>();
		
		Call call_1;		
		
		for(int i=0;i<insideLastExecutionRelations.size();i++){
			call_1 = calls.get(insideLastExecutionRelations.get(i).getCall_id());
			if(call_1.getCallee_id().equals(method.getId())){
				result.add(methods.get(insideLastExecutionRelations.get(i).getMethod_id()));
			}							
			
		}				
					
		return result;
		
	}	
	
	public void calculateFlowGraphMetric(){
		List<Method> resultMethods;
		for(int i=0;i<methods.size();i++){
			resultMethods = getOutsideBeforeExecutionMethods(methods.get(i));
			List<String> relatedMethods = new ArrayList();
			for(int j=0;j<resultMethods.size();j++){
				relatedMethods.add(resultMethods.get(j).getId());				
			}	
			OutsideBeforeExecutionMetric element = new OutsideBeforeExecutionMetric(methods.get(i),resultMethods.size());
			element.setRelatedMethods(relatedMethods);
			flowGraphResult.add(element);
		}	
		//para ver qu� devuelve
		System.out.println("Lalala");
		for(int k=0;k<flowGraphResult.size();k++){
			System.out.println(flowGraphResult.get(k).getMethod().getId()+ " ::: "+flowGraphResult.get(k).getMetric());			
		}
	}
	
	public void addMethod(Method m){
		this.methods.put(m.getId(),m);
	}
	
	public void addCall(Call c){
		this.calls.put(c.getId(),c);
	}

	@Override
	public String getId() {
		return projectModel.getName();
	}

	@Override
	public ProjectModel getProjectModel() {
		return projectModel;
	}
	
	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}

}
