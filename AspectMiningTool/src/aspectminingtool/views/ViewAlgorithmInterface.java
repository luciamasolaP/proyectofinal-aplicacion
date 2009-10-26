package aspectminingtool.views;

import java.util.List;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;

public interface ViewAlgorithmInterface {

	List<RelatedMethodDescription> getRelatedMethods(List relatedMethods);
	
}
