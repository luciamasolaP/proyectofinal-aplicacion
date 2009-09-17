package aspectminingtool.views;

import java.util.List;

import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.model.Call_Counted;

public interface ViewSeedsInterface {

	void addMethodToModel(Method method, List<Call_Counted> list,
			ProjectModel pm);
	
}
