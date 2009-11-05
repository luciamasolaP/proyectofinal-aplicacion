package aspectminingtool.Algorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;
import JessIntegrationModel.Call;
import JessIntegrationModel.Implements;
import JessIntegrationModel.Inherits;
import JessIntegrationModel.Interface;
import JessIntegrationModel.Method;
import aspectminingtool.InferenceEngine.JessInferenceEngine;



public class FanInAlgorithm extends Algorithm {

	public FanInAlgorithm() {

		archive = "Fan-In.clp";

	}


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
	    
	    QueryResult result2;
		try {
			result2 = engine.runQueryStar("fanInTotalBorderDec", new ValueVector().add("Smith"));
			 while (result2.next()) {
		            System.out.println( "Fan-in Total: "+result2.getString("m") + " Fan in acum " + result2.getString("m1") + " Fan in propio " + result2.getString("m2"));

			 
			 }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
		
		
		try 
	    {
	        BufferedWriter outfile1 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\llamados.txt"));
		    
	        	 QueryResult result3;
	 			try {
	 				result3 = engine.runQueryStar("llamadosBorderDec", new ValueVector().add("Smith"));
	 				 while (result3.next()) {

	 					 	String l = "metodo llamador: " + result3.getString("Caller") + " metodo llamado " + result3.getString("*metodo*");
	 					 	outfile1.write(l);
	 						outfile1.newLine();
	 				 
	 				 }
	 			} catch (JessException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	        	

	        
	      
	     
	        outfile1.close();
	    }
	    catch (IOException e)    {    }
	    
	    try 
	    {
	        BufferedWriter outfile2 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\llamados_no_directos.txt"));
		    
	        	 QueryResult result3;
	 			try {
	 				result3 = engine.runQueryStar("llamadosNoDirectos", new ValueVector().add("Smith"));
	 				 while (result3.next()) {

	 					 	String l = "metodo llamador: " + result3.getString("Caller") + " metodo llamado " + result3.getString("*metodo*");
	 					 	outfile2.write(l);
	 						outfile2.newLine();
	 				 
	 				 }
	 			} catch (JessException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	        	

	        
	      
	     
	        outfile2.close();
	    }
	    catch (IOException e)    {    }
		

	}


	@Override
	public boolean filerFact(Object fact) {
		if (fact instanceof Method || fact instanceof JessIntegrationModel.Class || fact instanceof Inherits || fact instanceof Implements 
				|| fact instanceof Call || fact instanceof Interface) 
			return false;
		return true;
	}

}
