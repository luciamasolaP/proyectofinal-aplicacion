package aspectminingtool.views.FanIn;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;

public class FanInContentProvider implements IStructuredContentProvider {

	private FanInModel fanInModel = null;
	

	@Override
	public Object[] getElements(Object inputElement) {
		fanInModel = (FanInModel)inputElement;
		return fanInModel.getResultadoFanIn().toArray();

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
