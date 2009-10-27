package JessIntegrationModel;

import java.util.List;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;

public interface ISelectAsSeedModel extends IResultsModel{

	List<RelatedMethodDescription> getRelatedMethods(Method method);
	
}
