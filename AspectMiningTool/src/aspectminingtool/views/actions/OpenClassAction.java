package aspectminingtool.views.actions;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.JessIntegrationModel.Results;
import aspectminingtool.util.ViewPartUtil;

public class OpenClassAction extends Action{

	IResultsModel model;
	TableViewer table;
	
	public OpenClassAction(IResultsModel model, TableViewer table){
		super("Open");
		this.model = model;
		this.table = table;
		
	}
	
	public void run() { 
		IStructuredSelection sel = (IStructuredSelection)table.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			Results result = (Results) iter.next();
			ViewPartUtil.openResource(result.getOpenableData(),model.getProjectModel());

	}
	}
	
}
