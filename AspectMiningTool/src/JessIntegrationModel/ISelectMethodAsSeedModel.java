package JessIntegrationModel;

import java.util.List;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;

public interface ISelectMethodAsSeedModel extends IResultsModel{

	
	List<RelatedMethodDescription> getRelatedMethods(Method method, String name);
	
}
