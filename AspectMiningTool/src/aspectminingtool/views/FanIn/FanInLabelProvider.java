package aspectminingtool.views.FanIn;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;

public class FanInLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO 
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		if (element instanceof Fan_in_Result){
			if (columnIndex == 0) 
		    	return ((Fan_in_Result)element).toString();
		    else if (columnIndex == 1)
		    	return ((Fan_in_Result)element).getMetric();
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
