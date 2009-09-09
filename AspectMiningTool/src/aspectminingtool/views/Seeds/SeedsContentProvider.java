package aspectminingtool.views.Seeds;



import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.Seeds.SeedsModel;



public class SeedsContentProvider implements IStructuredContentProvider {

	private SeedsModel seedsModel = null;
	
	@Override
	public Object[] getElements(Object inputElement) {
		seedsModel = (SeedsModel)inputElement;
		return seedsModel.getDescription().toArray();

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
