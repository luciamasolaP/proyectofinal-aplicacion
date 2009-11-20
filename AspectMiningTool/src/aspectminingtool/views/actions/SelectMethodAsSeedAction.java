package aspectminingtool.views.actions;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ISelectMethodAsSeedModel;
import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.MetricMethodResult;
import aspectminingtool.util.ViewPartUtil;
import aspectminingtool.views.SeedsGeneral.ViewPartSeeds;

public class SelectMethodAsSeedAction extends AbstractSelectAsSeedAction{


	public SelectMethodAsSeedAction(IResultsModel model, TableViewer table,
			String Name) {
		super(model, table, Name);

	}

	protected String getViewPartSeedsId(){
		return ViewPartSeeds.ID_VIEW;
	}
	
	public void run() { 
		
		IStructuredSelection sel = (IStructuredSelection)table.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			MetricMethodResult fir = (MetricMethodResult) iter.next();
			Method method = fir.getMetodo();
			ViewPartUtil.selectMethodAsSeed(method, getViewPartSeedsId(), ((ISelectMethodAsSeedModel)model).getRelatedMethods(method, name), model.getProjectModel(), name);
		}
	}
		
}
