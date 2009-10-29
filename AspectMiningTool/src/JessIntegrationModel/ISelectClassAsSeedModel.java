package JessIntegrationModel;

import java.util.List;

import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;
import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.RelatedCallCountedDescription;

public interface ISelectClassAsSeedModel extends IResultsModel{

	
	List<RelatedCallCountedDescription> getRelatedMethods(RedirectorFinderResults method, String name);
	
}
