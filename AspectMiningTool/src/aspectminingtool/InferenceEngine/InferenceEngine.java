package aspectminingtool.InferenceEngine;

import java.util.ArrayList;

import aspectminingtool.Algorithms.Algorithm;

public abstract class InferenceEngine {

	Algorithm algorithm;
	
	
	public void setAlgorithm(Algorithm algorithm){
		
		this.algorithm = algorithm;
		
	}
	
	public void execute(ArrayList facts){
		
		confirgureAlgorithm();
		persistFacts(facts);
		executeAlgorithm();
		
	}
	
	protected abstract void confirgureAlgorithm();
	protected abstract void persistFacts(ArrayList facts);
	protected abstract void executeAlgorithm();
		
	
}
