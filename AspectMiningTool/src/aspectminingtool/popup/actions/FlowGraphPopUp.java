package aspectminingtool.popup.actions;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.Algorithms.FlowGraphAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;
import aspectminingtool.views.FlowGraph.ViewPartFlowGraph;

public class FlowGraphPopUp extends AbstractPerformMiningPopup {

	public FlowGraphPopUp() {

	}

	public Algorithm getAlgorithm() {

		return new FlowGraphAlgorithm();

	}

	@Override
	protected void showResults(IResultsModel results) {
		
		FlowGraphModel flowGraphResults = (FlowGraphModel)results;		
		flowGraphResults.listResults();
		try {

//			ViewPartFanIn view = (ViewPartFanIn) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//			.getActivePage().showView(ViewPartFanIn.ID_VIEW,results.getId() , IWorkbenchPage.VIEW_CREATE);
//			para abrir en multiples vistas, esto y ***			

			ViewPartFlowGraph view = (ViewPartFlowGraph) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(ViewPartFlowGraph.ID_VIEW );
			
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

		return new FlowGraphModel(pm, inferenceEngine);

	}

}
