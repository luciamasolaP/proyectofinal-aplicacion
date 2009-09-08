package aspectminingtool.Algorithms;

import aspectminingtool.InferenceEngine.InferenceEngine;

public class FlowGraphAlgorithm extends Algorithm{
	
	protected String archive;
	protected InferenceEngine inferenceEngine = null;
	
	public FlowGraphAlgorithm() {

		archive = "Fan-In.clp";

	}
	
	public String getArchive(){
		
		return this.archive;
		
	}
	
}
