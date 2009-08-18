package aspectminingtool.InferenceEngine;

import java.util.ArrayList;

public interface InferenceEngine {

	public void persistFacts(ArrayList facts);
	public void executeAlgorithm(Algorithm algorithm,ArrayList facts);
	
	
}
