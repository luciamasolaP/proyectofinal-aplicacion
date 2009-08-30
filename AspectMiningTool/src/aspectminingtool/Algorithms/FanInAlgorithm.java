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

public class FanInAlgorithm extends Algorithm {

	public FanInAlgorithm(InferenceEngine infEngine) {

		super(infEngine);
		archive = "Fan-In.clp";

	}

	@Override
	public void viewResults() {
		
		JessInferenceEngine jessInferenceEngine = (JessInferenceEngine)inferenceEngine;
		Rete engine = jessInferenceEngine.getEngine();
		
		QueryResult result;
		try {
			result = engine.runQueryStar("fanInTotal", new ValueVector().add("Smith"));
			 while (result.next()) {
		            System.out.println("fan in total Metodo: "+result.getString("mi") + " fan-in " + result.getString("m") + " clase: " + result.getString("class"));
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
        
		// print facts
		//
		
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
