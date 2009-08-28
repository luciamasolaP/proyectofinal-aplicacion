package aspectminingtool.popup.actions;


import aspectminingtool.Algorithms.FanInAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;


public class FanInPopUp extends AbstractPerformMiningPopup {

	public FanInPopUp() {

	}

	public void setAlgorithm(InferenceEngine inferenceEngine) {
		
		algorithm = new FanInAlgorithm(inferenceEngine);
		
	}


}
