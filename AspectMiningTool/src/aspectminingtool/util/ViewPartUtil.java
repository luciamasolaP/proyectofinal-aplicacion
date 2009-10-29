package aspectminingtool.util;


import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;
import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;
import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.RelatedCallCountedDescription;
import aspectminingtool.views.RedirectorFinderSeeds.ViewPartClassesSeeds;
import aspectminingtool.views.SeedsGeneral.ViewPartSeeds;

public class ViewPartUtil {

	public static void selectMethodAsSeed(Method method, String idView, List<RelatedMethodDescription> relatedMethods , ProjectModel projectModel, String algorithm){
		
		try {
//			ViewSeedsInterface view = (ViewSeedsInterface) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//			.getActivePage().showView(idView );
//			view.addSeedToModel(method, algorithm, relatedMethods, projectModel);
			
			String secondaryId = projectModel.getName();
			
//			ViewPartSeeds view = (ViewPartSeeds) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//			.getActivePage().showView(ViewPartSeeds.ID_VIEW, projectModel.getName() , IWorkbenchPage.VIEW_CREATE);
//		
//			view.addSeedToModel(method, algorithm, relatedMethods, projectModel);
//			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartSeeds.ID_VIEW, projectModel.getName() , IWorkbenchPage.VIEW_ACTIVATE);


			IViewReference[] vistas = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
			boolean found = false;
			for ( int i = 0; i < vistas.length && !found; i++){
				
				if ( idView.equals(vistas[i].getId()) && secondaryId.equals(vistas[i].getSecondaryId())) {
					
					ViewPartSeeds viewSeeds = (ViewPartSeeds) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(ViewPartSeeds.ID_VIEW, secondaryId, IWorkbenchPage.VIEW_ACTIVATE);
					viewSeeds.addSeedToModel(method, algorithm, relatedMethods, projectModel);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartSeeds.ID_VIEW, secondaryId , IWorkbenchPage.VIEW_ACTIVATE);
					found = true;
					
				}
					
			}
			
			if (!found){
				
			
				ViewPartSeeds view = (ViewPartSeeds) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().showView(ViewPartSeeds.ID_VIEW, projectModel.getName() , IWorkbenchPage.VIEW_CREATE);
				view.setName(secondaryId);
				view.addSeedToModel(method, algorithm, relatedMethods, projectModel);
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartSeeds.ID_VIEW, projectModel.getName() , IWorkbenchPage.VIEW_ACTIVATE);
				
			}
			
		
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void selectClassAsSeed(RedirectorFinderResults redirectorFinderResult, String idView, List<RelatedCallCountedDescription> relatedMethods , ProjectModel projectModel, String algorithm){
		
		try {
			
			String secondaryId = projectModel.getName();
			
			IViewReference[] vistas = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
			boolean found = false;
			for ( int i = 0; i < vistas.length && !found; i++){
				
				if ( idView.equals(vistas[i].getId()) && secondaryId.equals(vistas[i].getSecondaryId())) {
					
					ViewPartClassesSeeds viewSeeds = (ViewPartClassesSeeds) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(ViewPartClassesSeeds.ID_VIEW, secondaryId, IWorkbenchPage.VIEW_ACTIVATE);
					viewSeeds.addSeedClassToModel(redirectorFinderResult, algorithm, relatedMethods, projectModel);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartClassesSeeds.ID_VIEW, secondaryId , IWorkbenchPage.VIEW_ACTIVATE);
					found = true;
					
				}
					
			}
			
			if (!found){
				
				ViewPartClassesSeeds view = (ViewPartClassesSeeds) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().showView(ViewPartClassesSeeds.ID_VIEW, projectModel.getName() , IWorkbenchPage.VIEW_CREATE);
				view.setName(secondaryId);
				view.addSeedClassToModel(redirectorFinderResult, algorithm, relatedMethods, projectModel);
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartSeeds.ID_VIEW, projectModel.getName() , IWorkbenchPage.VIEW_ACTIVATE);
			
	
				
			}
			
		
			
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
