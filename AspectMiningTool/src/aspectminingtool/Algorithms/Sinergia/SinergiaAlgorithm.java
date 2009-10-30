package aspectminingtool.Algorithms.Sinergia;

import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.JessIntegrationModel.Sinergia.FanInMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.InsideFirstExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.InsideLastExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.OutsideAfterExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.OutsideBeforeExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.UniqueMethodsMetric;



public class SinergiaAlgorithm extends Algorithm {

	public SinergiaAlgorithm() {

		archive = "Sinergia.clp";

	}

	@Override
	public boolean filerFact(Object fact) {
		if (fact instanceof FanInMetric || fact instanceof InsideFirstExecutionMetric || fact instanceof InsideLastExecutionMetric || fact instanceof OutsideAfterExecutionMetric 
				|| fact instanceof OutsideBeforeExecutionMetric || fact instanceof UniqueMethodsMetric) 
			return false;
		return true;
	}

}
