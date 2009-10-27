package aspectminingtool.views;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;

import aspectminingtool.JessIntegrationModel.MetricMethodResult;
import aspectminingtool.util.ViewPartUtil;

public class OpenMethodListener implements IDoubleClickListener{
	
	AbstractView view;
	
	public OpenMethodListener(AbstractView view){
		this.view = view;
	}
	
	public void doubleClick(DoubleClickEvent event) {
		if (!event.getSelection().isEmpty()) {
			
			if (event.getSelection() instanceof IStructuredSelection) {
				
				MetricMethodResult result = (MetricMethodResult) ((IStructuredSelection) event.getSelection()).getFirstElement();
				ViewPartUtil.openResource(result.getMetodo().getClass_id(),view.getModel().getProjectModel());
			}
		}
		
	}

}
