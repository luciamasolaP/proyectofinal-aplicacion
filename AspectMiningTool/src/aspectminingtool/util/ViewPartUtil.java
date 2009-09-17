package aspectminingtool.util;


import java.util.List;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.ViewSeedsInterface;

public class ViewPartUtil {

	public static void selectAsSeed(Method method, String idView, List<Call_Counted> list , ProjectModel pm){
		
		try {
			ViewSeedsInterface view = (ViewSeedsInterface) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(idView );

			view.addMethodToModel(method, list, pm);
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
