package aspectminingtool.InferenceEngine;

import java.util.ArrayList;
import java.util.Iterator;

import model.Interface;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;

public class JessInferenceEngine implements InferenceEngine {

	Rete engine;
	
	public JessInferenceEngine(){
		engine = new Rete(new Interface(null, null));
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

		try {
			engine.run();
		} catch (JessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		QueryResult result;
		try {
			result = engine.runQueryStar("fanInTotal", new ValueVector().add("Smith"));
			 while (result.next()) {
		            System.out.println("fan in total: "+result.getString("mi") + " " + result.getString("m"));
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
        
        QueryResult result1;
		try {
			result1 = engine.runQueryStar("llamados", new ValueVector().add("Smith"));
			 while (result1.next()) {
		            System.out.println("llamados: "+result1.getString("Caller") + " " + result1.getString("Method"));
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		for (Iterator i = engine.listFacts(); i.hasNext() ; ){
			
			Fact f = (Fact)i.next();

			System.out.println(f);

		}
		
	}

	@Override
	public void persistFacts(ArrayList facts) {

		
	}

}
