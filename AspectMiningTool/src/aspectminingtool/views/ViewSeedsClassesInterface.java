package aspectminingtool.views;

import java.util.List;

import JessIntegrationModel.ProjectModel;
import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;
import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.RelatedCallCountedDescription;

public interface ViewSeedsClassesInterface {

	void addSeedClassToModel(RedirectorFinderResults redirResul, String algorithm, List<RelatedCallCountedDescription> relatedMethods,
			ProjectModel projectModel);
	
		
}
