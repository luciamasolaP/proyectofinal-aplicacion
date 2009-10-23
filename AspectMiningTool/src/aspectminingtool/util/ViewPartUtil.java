package aspectminingtool.util;


import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

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
	
/**
 * Abre una determinada clase resourceName de un proyecto dado projectModel
 * @param resourceName
 * @param projectModel
 */
	public static void openResource(String resourceName, ProjectModel projectModel){
		

		IResource resource = projectModel.getAssociatedResource(resourceName);
		if (resource != null){
			IFile fileStore = ResourcesPlugin.getWorkspace().getRoot().getFile(resource.getFullPath());
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            try {
				IDE.openEditor(page, fileStore,true);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
		
	
	
}
