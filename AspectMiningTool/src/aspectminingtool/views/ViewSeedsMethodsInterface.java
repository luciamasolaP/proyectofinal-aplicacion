package aspectminingtool.views;

import java.util.List;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;

import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;

public interface ViewSeedsMethodsInterface {

	
	void addSeedToModel(Method method, String algorithm, List<RelatedMethodDescription> relatedMethods,
			ProjectModel projectModel);
	
		
}
