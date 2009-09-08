package aspectminingtool.views.UniqueMethods;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.JessIntegrationModel.UniqueMethods.UniqueMethods_Result;


public class UniqueMethodsLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO 
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		if (element instanceof UniqueMethods_Result){
			if (columnIndex == 0) 
		    	return ((UniqueMethods_Result)element).toString();
		    else if (columnIndex == 1)
		    	return ((UniqueMethods_Result)element).getMetric();
		}
		 
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
