package aspectminingtool.popup.actions;



import aspectminingtool.Algorithms.UniqueMethodsAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;


public class UniqueMethodPopUp extends AbstractPerformMiningPopup {

	public UniqueMethodPopUp() {

	}

	@Override
	public void setAlgorithm(InferenceEngine inferenceEngine) {

		algorithm = new UniqueMethodsAlgorithm(inferenceEngine);
	}



}
