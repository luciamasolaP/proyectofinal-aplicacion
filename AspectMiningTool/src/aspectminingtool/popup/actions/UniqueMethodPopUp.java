package aspectminingtool.popup.actions;



import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.Algorithms.UniqueMethodsAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.JessIntegrationModel.UniqueMethods.UniqueMethodsModel;
import aspectminingtool.views.UniqueMethods.ViewPartUniqueMethods;



public class UniqueMethodPopUp extends AbstractPerformMiningPopup {

	public UniqueMethodPopUp() {

	}

	@Override
	public Algorithm getAlgorithm() {

		return new UniqueMethodsAlgorithm();
	}

	@Override
	protected void showResults(IResultsModel results) {
		try {

//			ViewPartFanIn view = (ViewPartFanIn) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//			.getActivePage().showView(ViewPartFanIn.ID_VIEW,results.getId() , IWorkbenchPage.VIEW_CREATE);
//			para abrir en multiples vistas, esto y ***			

			ViewPartUniqueMethods view = (ViewPartUniqueMethods) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(ViewPartUniqueMethods.ID_VIEW );
			
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


		return new UniqueMethodsModel(pm, inferenceEngine);

	}

	

	


}
