package aspectminingtool.Algorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;

public class UniqueMethodsAlgorithm extends Algorithm {

	public UniqueMethodsAlgorithm(InferenceEngine infEngine) {

		super(infEngine);
		archive = "UniqueMethods.clp";

	}

	@Override
	public void viewResults() {
		
		JessInferenceEngine jessInferenceEngine = (JessInferenceEngine)inferenceEngine;
		Rete engine = jessInferenceEngine.getEngine();
		
		
		QueryResult result2;
		try {
			result2 = engine.runQueryStar("UniqueMethods", new ValueVector().add("void"));
			 while (result2.next()) {
		            System.out.println("Unique Methods: "+result2.getString("mi") + " fan-in " + result2.getString("m") + " clase: " + result2.getString("class") + " return type: " + result2.getString("ln"));
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		try 
	    {
	        BufferedWriter outfile = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\hechos-parciales.txt"));
	        for (Iterator i = engine.listFacts(); i.hasNext() ; ){
	        	Fact f = (Fact)i.next();
	        	outfile.write(f.toString());
				outfile.newLine();


			}
	       
	     
	        outfile.close();
	    }
	    catch (IOException e)    {    }
		

	}

}
