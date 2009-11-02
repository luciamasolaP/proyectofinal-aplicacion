package aspectminingtool.views.Sinergia;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.Sinergia.SinergiaResultsModel;

public class SinergiaSeedsContentProvider implements IStructuredContentProvider {

	private SinergiaResultsModel sinergiaResultsModel = null;

	@Override
	public Object[] getElements(Object inputElement) {
		sinergiaResultsModel = (SinergiaResultsModel)inputElement;
		return sinergiaResultsModel.getSeeds().toArray();

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
