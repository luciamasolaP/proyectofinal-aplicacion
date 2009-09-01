package aspectminingtool.popup.actions;



import JessIntegrationModel.IResultsModel;
import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.Algorithms.UniqueMethodsAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;


public class UniqueMethodPopUp extends AbstractPerformMiningPopup {

	public UniqueMethodPopUp() {

	}

	@Override
	public Algorithm getAlgorithm() {

		return new UniqueMethodsAlgorithm();
	}

	@Override
	protected void showResults(IResultsModel results2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IResultsModel getResults() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
