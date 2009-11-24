package aspectminingtool.Algorithms;

import JessIntegrationModel.Call;
import JessIntegrationModel.Implements;
import JessIntegrationModel.Inherits;
import JessIntegrationModel.Method;

public class UniqueMethodsAlgorithm extends Algorithm {

	public UniqueMethodsAlgorithm() {

		archive = "UniqueMethods.clp";

	}

	
	@Override
	public boolean filerFact(Object fact) {
		if (fact instanceof Method || fact instanceof JessIntegrationModel.Class || fact instanceof Inherits || fact instanceof Implements || fact instanceof Call ) 
			return false;
		return true;
	}

}
