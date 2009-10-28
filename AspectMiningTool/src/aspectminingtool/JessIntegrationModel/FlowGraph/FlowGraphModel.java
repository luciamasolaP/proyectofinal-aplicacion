package aspectminingtool.JessIntegrationModel.FlowGraph;

import java.io.BufferedWriter;
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
import JessIntegrationModel.Call;
import JessIntegrationModel.ISelectAsSeedModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.JessIntegrationModel.MetricMethodResult;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;

public class FlowGraphModel implements ISelectAsSeedModel {

	List<OutsideBeforeExecution> outsideBeforeExecutionRelations = new ArrayList<OutsideBeforeExecution>();
	List<OutsideAfterExecution> outsideAfterExecutionRelations = new ArrayList<OutsideAfterExecution>();	
	List<InsideFirstExecution> insideFirstExecutionRelations = new ArrayList<InsideFirstExecution>();
	List<InsideLastExecution> insideLastExecutionRelations = new ArrayList<InsideLastExecution>();
	
	List<MetricMethodResult> outsideBeforeExecutionResult = new ArrayList<MetricMethodResult>();
	List<MetricMethodResult> outsideAfterExecutionResult = new ArrayList<MetricMethodResult>();
	List<MetricMethodResult> insideFirstExecutionResult = new ArrayList<MetricMethodResult>();
	List<MetricMethodResult> insideLastExecutionResult = new ArrayList<MetricMethodResult>();
	
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
		queryOutsideBeforeExecutionMetric(engine);
		//calculateOutsideBeforeExecutionMetric();
		constructOutsideAfterExecutionRelations(engine);
		queryOutsideAfterExecutionMetric(engine);
		//calculateOutsideAfterExecutionMetric();
		constructInsideFirstExecutionRelations(engine);
		queryInsideFirstExecutionMetric(engine);
		//calculateInsideFirstExecutionMetric();
		constructInsideLastExecutionRelations(engine);
		queryInsideLastExecutionMetric(engine);
		//calculateInsideLastExecutionMetric();
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
		
		Rete jessEngine = ((JessInferenceEngine) this.inferenceEngine).getEngine();
		Iterator facts = jessEngine.listFacts();
		
		for(;facts.hasNext();){			
			System.out.println(facts.next().toString());
		}/*
		
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
		}
		
		Method m = methods.get("/TestClass_2//method_2_1///");
		if(m != null){
			List<Method> result = this.getInsideFirstExecutionMethods(m);
			System.out.println(m.getId());
			System.out.println();
			for(int i=0;i<result.size();i++){
				System.out.println(result.get(i).getId());
			}			
		}*/
		
	}
	
	/*
	 * Dado un método, devuelve una lista con los métodos que participan con este en relaciones 
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
				if(methods.get(call_2.getCallee_id()) != null)
					result.add(methods.get(call_2.getCallee_id()));				
			}
		}				
					
		return result;
		
	}
	
	/*
	 * Dado un método, devuelve una lista con los métodos que participan con este en relaciones
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
				if(methods.get(call_2.getCallee_id()) != null)
					result.add(methods.get(call_2.getCallee_id()));				
			}
		}				
					
		return result;
		
	}
	
	/*
	 * Dado un método, devuelve una lista con los métodos que participan con este en relaciones
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
	 * Dado un método, devuelve una lista con los métodos que participan con este en relaciones
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
	
	public void calculateOutsideBeforeExecutionMetric(){
		outsideBeforeExecutionResult.clear();
		List<Method> resultMethods;
		for (Iterator<String> i = methods.keySet().iterator() ; i.hasNext() ; ){
			Method mm = methods.get(i.next());
			resultMethods = getOutsideBeforeExecutionMethods(mm);
//			List<String> relatedMethods = new ArrayList<String>();
//			for(int j=0;j<resultMethods.size();j++){
//				relatedMethods.add(resultMethods.get(j).getId());				
//			}	
			MetricMethodResult element = new MetricMethodResult(mm,String.valueOf(resultMethods.size()));
			//element.setRelatedMethods(relatedMethods);
			outsideBeforeExecutionResult.add(element);
		}		
	}
	
	public void calculateOutsideAfterExecutionMetric(){
		outsideAfterExecutionResult.clear();
		List<Method> resultMethods;
		for (Iterator<String> i = methods.keySet().iterator() ; i.hasNext() ; ){
			Method mm = methods.get(i.next());
			resultMethods = getOutsideAfterExecutionMethods(mm);
//			List<String> relatedMethods = new ArrayList<String>();
//			for(int j=0;j<resultMethods.size();j++){
//				relatedMethods.add(resultMethods.get(j).getId());				
//			}	
			MetricMethodResult element = new MetricMethodResult(mm,String.valueOf(resultMethods.size()));
			//element.setRelatedMethods(relatedMethods);
			outsideAfterExecutionResult.add(element);
		}		
	}
	
	public void calculateInsideFirstExecutionMetric(){
		insideFirstExecutionResult.clear();
		List<Method> resultMethods;
		
		for (Iterator<String> i = methods.keySet().iterator() ; i.hasNext() ; ){
			Method mm = methods.get(i.next());
			resultMethods = getInsideFirstExecutionMethods(mm);
//			List<String> relatedMethods = new ArrayList<String>();
//			for(int j=0;j<resultMethods.size();j++){
//				relatedMethods.add(resultMethods.get(j).getId());				
//			}
			MetricMethodResult element = new MetricMethodResult(mm,String.valueOf(resultMethods.size()));
			//element.setRelatedMethods(relatedMethods);
			insideFirstExecutionResult.add(element);
		}
	}
	
	public void calculateInsideLastExecutionMetric(){
		insideLastExecutionResult.clear();
		List<Method> resultMethods;
		
		for (Iterator<String> i = methods.keySet().iterator() ; i.hasNext() ; ){
			Method mm = methods.get(i.next());
			resultMethods = getInsideLastExecutionMethods(mm);
//			List<String> relatedMethods = new ArrayList<String>();
//			for(int j=0;j<resultMethods.size();j++){
//				relatedMethods.add(resultMethods.get(j).getId());				
//			}
			MetricMethodResult element = new MetricMethodResult(mm,String.valueOf(resultMethods.size()));
			//element.setRelatedMethods(relatedMethods);
			insideLastExecutionResult.add(element);
		}
	}
	
	public void queryOutsideBeforeExecutionMetric(InferenceEngine engine){
		
		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("get_OutsideBeforeExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 MetricMethodResult metricRelation = new MetricMethodResult(methods.get(result.getString("method")), result.getString("metric"));
				 outsideBeforeExecutionResult.add(metricRelation);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void queryOutsideAfterExecutionMetric(InferenceEngine engine){
		
		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("get_OutsideAfterExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 MetricMethodResult metricRelation = new MetricMethodResult(methods.get(result.getString("method")), result.getString("metric"));
				 outsideAfterExecutionResult.add(metricRelation);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void queryInsideFirstExecutionMetric(InferenceEngine engine){
		
		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("get_InsideFirstExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 MetricMethodResult metricRelation = new MetricMethodResult(methods.get(result.getString("method")), result.getString("metric"));
				 insideFirstExecutionResult.add(metricRelation);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void queryInsideLastExecutionMetric(InferenceEngine engine){
	
	Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
	QueryResult result;
	try {
		result = jessEngine.runQueryStar("get_InsideLastExecution_Metric", new ValueVector().add(""));
		 while (result.next()) {
			 MetricMethodResult metricRelation = new MetricMethodResult(methods.get(result.getString("method")), String.valueOf(result.getInt("metric")));
			 insideLastExecutionResult.add(metricRelation);
	        }
	} catch (JessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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

	@Override
	public void generateArchive(BufferedWriter archive) {
		// TODO Auto-generated method stub
		
	}

	public List<MetricMethodResult> getOutsideBeforeExecutionResult() {
		return outsideBeforeExecutionResult;
	}

	public void setOutsideBeforeExecutionResult(
			List<MetricMethodResult> outsideBeforeExecutionResult) {
		this.outsideBeforeExecutionResult = outsideBeforeExecutionResult;
	}

	public List<MetricMethodResult> getOutsideAfterExecutionResult() {
		return outsideAfterExecutionResult;
	}

	public void setOutsideAfterExecutionResult(
			List<MetricMethodResult> outsideAfterExecutionResult) {
		this.outsideAfterExecutionResult = outsideAfterExecutionResult;
	}

	public List<MetricMethodResult> getInsideFirstExecutionResult() {
		return insideFirstExecutionResult;
	}

	public void setInsideFirstExecutionResult(
			List<MetricMethodResult> insideFirstExecutionResult) {
		this.insideFirstExecutionResult = insideFirstExecutionResult;
	}

	public List<MetricMethodResult> getInsideLastExecutionResult() {
		return insideLastExecutionResult;
	}

	public void setInsideLastExecutionResult(
			List<MetricMethodResult> insideLastExecutionResult) {
		this.insideLastExecutionResult = insideLastExecutionResult;
	}

	@Override
	public List<RelatedMethodDescription> getRelatedMethods(Method method) {
		// TODO Auto-generated method stub
		return null;
	}

}
