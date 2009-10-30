package aspectminingtool.InferenceEngine;

import java.util.List;

import aspectminingtool.Algorithms.Algorithm;

public abstract class InferenceEngine {

	Algorithm algorithm;
	
	
	public void setAlgorithm(Algorithm algorithm){
		
		this.algorithm = algorithm;
		
	}
	
	public void execute(List facts){
		
		confirgureAlgorithm();
		persistFacts(facts);
		executeAlgorithm();
		
	}
	
	public abstract void clearEngine();	
	
	protected abstract void confirgureAlgorithm();
	protected abstract void persistFacts(List facts);
	protected abstract void executeAlgorithm();
	
}
