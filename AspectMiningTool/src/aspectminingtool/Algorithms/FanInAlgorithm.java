package aspectminingtool.Algorithms;

import JessIntegrationModel.Call;
import JessIntegrationModel.Implements;
import JessIntegrationModel.Inherits;
import JessIntegrationModel.Interface;
import JessIntegrationModel.Method;



public class FanInAlgorithm extends Algorithm {

	public FanInAlgorithm() {

		archive = "Fan-In.clp";

	}


	@Override
	public boolean filerFact(Object fact) {
		if (fact instanceof Method || fact instanceof JessIntegrationModel.Class || fact instanceof Inherits || fact instanceof Implements 
				|| fact instanceof Call || fact instanceof Interface) 
			return false;
		return true;
	}

}
