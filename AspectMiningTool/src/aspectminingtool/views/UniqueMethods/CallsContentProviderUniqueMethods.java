package aspectminingtool.views.UniqueMethods;


import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.UniqueMethods.Call_Counted;


public class CallsContentProviderUniqueMethods implements IStructuredContentProvider {

	private List<Call_Counted> calls = null;
	

	@Override
	public Object[] getElements(Object inputElement) {
		calls = (List<Call_Counted>)inputElement;
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
