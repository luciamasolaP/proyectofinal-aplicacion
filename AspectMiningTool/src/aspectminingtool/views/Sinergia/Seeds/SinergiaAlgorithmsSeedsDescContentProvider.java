package aspectminingtool.views.Sinergia.Seeds;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SinergiaAlgorithmsSeedsDescContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] o = {inputElement};
		return o;
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
