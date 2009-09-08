package aspectminingtool.views.UniqueMethods;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.JessIntegrationModel.UniqueMethods.Call_Counted;



public class CallsLabelProviderUniqueMethods implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO 
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		if (element instanceof Call_Counted)
		    return columnIndex == 0 ? ((Call_Counted)element).toString(): "";
		 
		return "";
	}

	

	@Override
	public void dispose() {
		// TODO 
		
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		
		
	}

	

}
