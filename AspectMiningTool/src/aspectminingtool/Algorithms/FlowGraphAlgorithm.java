package aspectminingtool.Algorithms;

import aspectminingtool.InferenceEngine.InferenceEngine;

public class FlowGraphAlgorithm extends Algorithm{
	
	protected String archive;
	protected InferenceEngine inferenceEngine = null;
	
	public FlowGraphAlgorithm() {

		archive = "FlowGraph.clp";

	}
	
	public String getArchive(){
		
		return this.archive;
		
	}

	@Override
	public boolean filerFact(Object fact) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
