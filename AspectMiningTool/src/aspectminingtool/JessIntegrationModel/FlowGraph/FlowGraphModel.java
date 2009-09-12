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
import aspectminingtool.JessIntegrationModel.FanIn.Call_Counted;
import aspectminingtool.JessIntegrationModel.FanIn.final_fan_in_metric;
import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;

import JessIntegrationModel.Call;
import JessIntegrationModel.Method;

public class FlowGraphModel implements IResultsModel {

	List<OutsideBeforeExecution> outsideBeforeExecutionRelations = new ArrayList<OutsideBeforeExecution>();
	List<OutsideAfterExecution> outsideAfterExecutionRelations = new ArrayList<OutsideAfterExecution>();	
	List<InsideFirstExecution> insideFirstExecutionRelations = new ArrayList<InsideFirstExecution>();
	List<InsideLastExecution> insideLastExecutionRelations = new ArrayList<InsideLastExecution>();
	
	List<Call> calls = new ArrayList<Call>();
	List<Method> methods = new ArrayList<Method>();
	
	ProjectModel projectModel;	
	InferenceEngine inferenceEngine = null;
	
	public FlowGraphModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();		
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
			methods.add(m);
		}
	}
	
	private void contructCalls(InferenceEngine engine) {

		Rete jessEngine = ((JessInferenceEngine) engine).getEngine();
		Iterator callsResult = jessEngine.getObjects(new Filter.ByClass(Call.class));
		
		for (;callsResult.hasNext();){
			Call c = (Call)callsResult.next();
			calls.add(c);
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
		
		Rete jessEngine = ((JessInferenceEngine) this.inferenceEngine).getEngine();
		Iterator facts = jessEngine.listDeffacts();
		for(;facts.hasNext();){			
			System.out.print(facts.next().toString());
		}
		/*
		for(int i=0;i<outsideBeforeExecutionRelations.size();i++)
		{
			System.out.print(outsideBeforeExecutionRelations.get(i).toString());
		}		*/
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
