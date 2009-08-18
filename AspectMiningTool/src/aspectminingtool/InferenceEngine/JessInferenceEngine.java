package aspectminingtool.InferenceEngine;

import java.util.ArrayList;

import jess.Deftemplate;
import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;

public class JessInferenceEngine implements InferenceEngine {

	Rete engine;
	
	public JessInferenceEngine(){
		engine = new Rete();
	}
	
	@Override
	public void executeAlgorithm(Algorithm algorithm, ArrayList facts) {

		try {
			
			engine.batch("F:\\workspace-Tesis\\workspacePlugin\\AspectMiningTool\\Fan-In.clp");

			
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			engine.addAll(facts);
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		QueryResult result;
		try {
			result = engine.runQueryStar("prueba", new ValueVector().add("Smith"));
			 while (result.next()) {
		            System.out.println("fan in total: "+result.getString("mi") + " " + result.getString("m"));
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
        
        QueryResult result1;
		try {
			result1 = engine.runQueryStar("prueba1", new ValueVector().add("Smith"));
			 while (result1.next()) {
		            System.out.println("llamados: "+result1.getString("Caller") + " " + result1.getString("Method"));
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		
	}

	@Override
	public void persistFacts(ArrayList facts) {

		
	}

}
