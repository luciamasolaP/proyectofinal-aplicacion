package aspectminingtool.views.UniqueMethods;



import org.eclipse.jface.viewers.IStructuredContentProvider;

import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.UniqueMethods.UniqueMethodsModel;



public class UniqueMethodsContentProvider implements IStructuredContentProvider {

	private UniqueMethodsModel uniqueMethodsModel = null;
	
	@Override
	public Object[] getElements(Object inputElement) {
		uniqueMethodsModel = (UniqueMethodsModel)inputElement;
		return uniqueMethodsModel.getResultadoFanIn().toArray();

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
