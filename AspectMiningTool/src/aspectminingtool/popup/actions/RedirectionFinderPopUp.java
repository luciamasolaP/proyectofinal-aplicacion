package aspectminingtool.popup.actions;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.Algorithms.RedirectionFinderAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectionFinderModel;
import aspectminingtool.views.RedirectorFinder.ViewPartRedirectorFinder;

public class RedirectionFinderPopUp extends AbstractPerformMiningPopup {

	public RedirectionFinderPopUp() {

	}

	public Algorithm getAlgorithm() {

		return new RedirectionFinderAlgorithm();

	}

	@Override
	protected void showResults(IResultsModel results) {
		try {

//			ViewPartFanIn view = (ViewPartFanIn) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//			.getActivePage().showView(ViewPartFanIn.ID_VIEW,results.getId() , IWorkbenchPage.VIEW_CREATE);
//			para abrir en multiples vistas, esto y ***			

			ViewPartRedirectorFinder view = (ViewPartRedirectorFinder) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(ViewPartRedirectorFinder.ID_VIEW );
			
			view.setModel(results);

//			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartFanIn.ID_VIEW,results.getId() , IWorkbenchPage.VIEW_ACTIVATE);
//			**			
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected IResultsModel getResults(InferenceEngine inferenceEngine) {

		return new RedirectionFinderModel(pm, inferenceEngine);

	}

}
