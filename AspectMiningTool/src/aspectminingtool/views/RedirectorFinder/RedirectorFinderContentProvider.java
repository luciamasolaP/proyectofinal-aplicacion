package aspectminingtool.views.RedirectorFinder;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectionFinderModel;

public class RedirectorFinderContentProvider implements IStructuredContentProvider {

	private RedirectionFinderModel redirectorFinderModel = null;
	

	@Override
	public Object[] getElements(Object inputElement) {
		redirectorFinderModel = (RedirectionFinderModel)inputElement;
		return redirectorFinderModel.getRedirectorFinderResults().toArray();

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
