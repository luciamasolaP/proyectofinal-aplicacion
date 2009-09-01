package aspectminingtool.popup.actions;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.Algorithms.FanInAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;
import aspectminingtool.model.AspectMiningModel;
import aspectminingtool.views.ViewPartFanIn;

public class FanInPopUp extends AbstractPerformMiningPopup {

	public FanInPopUp() {

	}

	public Algorithm getAlgorithm() {

		return new FanInAlgorithm();

	}

	@Override
	protected void showResults(IResultsModel results) {
		try {

			ViewPartFanIn view = (ViewPartFanIn) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().findView(
							ViewPartFanIn.ID_VIEW);
						
			view.setModel(results);

			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(ViewPartFanIn.ID_VIEW);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected IResultsModel getResults() {

		return new FanInModel(pm);

	}

}
