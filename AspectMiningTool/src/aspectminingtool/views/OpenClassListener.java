package aspectminingtool.views;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;

import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.util.ViewPartUtil;

public class OpenClassListener implements IDoubleClickListener{
	
	AbstractView view;
	
	public OpenClassListener(AbstractView view){
		this.view = view;
	}
	
	public void doubleClick(DoubleClickEvent event) {
		if (!event.getSelection().isEmpty()) {
			
			if (event.getSelection() instanceof IStructuredSelection) {
				
				Results result = (Results) ((IStructuredSelection) event.getSelection()).getFirstElement();
				ViewPartUtil.openResource(result.getOpenableData(),view.getModel().getProjectModel());
			}
		}
		
	}

}
