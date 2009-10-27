package aspectminingtool.views.FlowGraph;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import JessIntegrationModel.Method;

public class FlowGraphContentProviderOBCalls implements IStructuredContentProvider {

	private List<Method> calls = null;
	
	@Override
	public Object[] getElements(Object inputElement) {
		
		calls = (List<Method>)inputElement;
		return calls.toArray(); 	

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
