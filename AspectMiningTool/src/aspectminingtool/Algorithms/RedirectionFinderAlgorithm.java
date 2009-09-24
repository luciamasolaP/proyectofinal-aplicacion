package aspectminingtool.Algorithms;

import JessIntegrationModel.Abstract;
import JessIntegrationModel.Call;
import JessIntegrationModel.Method;




public class RedirectionFinderAlgorithm extends Algorithm {

	public RedirectionFinderAlgorithm() {

		archive = "RedirectorFinder.clp";

	}

	@Override
	public boolean filerFact(Object fact) {

		
		if (fact instanceof Method || fact instanceof JessIntegrationModel.Class || fact instanceof Call || fact instanceof Abstract) 
			return false;
		return true;
	}

	public void viewResults() {
		
	}

}
