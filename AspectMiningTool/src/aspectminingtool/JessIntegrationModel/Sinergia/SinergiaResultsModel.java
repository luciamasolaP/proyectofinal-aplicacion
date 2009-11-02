package aspectminingtool.JessIntegrationModel.Sinergia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

public class SinergiaResultsModel implements IResultsModel{

	List<Seed> seeds;
	Map<Seed,List<String>> algorithmSeed;
	ProjectModel projectModel;
	InferenceEngine inferenceEngine = null;
	
	
	public SinergiaResultsModel(List<Seed> seeds,
			Map<Seed,List<String>> algorithmSeed, ProjectModel pm) {
		super();
		this.seeds = seeds;
		this.algorithmSeed = algorithmSeed;
		this.projectModel = pm;
		
	}

	public SinergiaResultsModel(ProjectModel pm, InferenceEngine inferenceEngine) {
		super();
		this.seeds = new ArrayList<Seed>();
		this.algorithmSeed = new HashMap<Seed,List<String>>();
		this.projectModel = pm;
		this.inferenceEngine = inferenceEngine;
		contructModel(pm);
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
		
		Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("getSeeds", new ValueVector().add(""));
			 while (result.next()) {
				 String methodName = result.getString("method");
				 Seed seed = new Seed(methodName,result.getString("trust"));
				 seeds.add(seed);			 
				 constructAlgorithmSeed(methodName, jessEngine, seed);
	 
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void constructAlgorithmSeed(String methodName, Rete jessEngine, Seed seed){
		
		List<String> resulAlgorithm = new ArrayList<String>();
		resulAlgorithm.add("No");
		resulAlgorithm.add("No");
		resulAlgorithm.add("No");
		QueryResult result1;
		QueryResult result2;
		QueryResult result3;
		ValueVector valueVector;
		try {
			valueVector = new ValueVector().add(new Value(methodName, RU.STRING));
			result1 = jessEngine.runQueryStar("getFanInSeeds", valueVector);
			if (result1.next())
				resulAlgorithm.set(0, "Si");
			
			result2 = jessEngine.runQueryStar("getUniqueMethodsSeeds", valueVector);
			while (result2.next())
				resulAlgorithm.set(1, "Si");
			
			result3 = jessEngine.runQueryStar("getFlowGraphSeeds", valueVector);
			if (result3.next())
				resulAlgorithm.set(2, "Si");
			
			algorithmSeed.put(seed, resulAlgorithm);
			
		} catch (JessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public List<String> getAlgorithmsResults(Seed seed){
		return algorithmSeed.get(seed);		
	}

	@Override
	public void generateArchive(BufferedWriter archive) {

		try 
	    {

			for (Iterator<Seed> i = seeds.iterator(); i.hasNext() ;){
				Seed seed = i.next(); 
				archive.write(seed.toString());
				archive.newLine();
				List<String> algorithms = algorithmSeed.get(seed);
				archive.write("         Algoritmos:");
				archive.write("           Fan-in-> " + algorithms.get(0) + "      Unique Methods-> " + algorithms.get(1) + "      Flow Graph-> " + algorithms.get(2));
				archive.newLine();
			}

	        archive.close();
	    }
	    catch (IOException e)    {    }
		
	}

	public List<Seed> getSeeds() {
		return seeds;
	}
	
}
