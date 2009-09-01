package aspectminingtool.Algorithms;

import aspectminingtool.InferenceEngine.InferenceEngine;

public abstract class Algorithm {
	
	protected String archive;
	protected InferenceEngine inferenceEngine = null;
	
	public String getArchive(){
		
		return this.archive;
		
	}
	
}
