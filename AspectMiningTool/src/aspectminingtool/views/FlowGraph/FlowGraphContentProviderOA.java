package aspectminingtool.views.FlowGraph;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;

public class FlowGraphContentProviderOA implements IStructuredContentProvider {

	private FlowGraphModel flowGraphModel = null;
	

	@Override
	public Object[] getElements(Object inputElement) {
		flowGraphModel = (FlowGraphModel)inputElement;		
		return flowGraphModel.getOutsideAfterExecutionResult().toArray();	 	

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

}
