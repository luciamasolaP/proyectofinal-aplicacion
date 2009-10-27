package aspectminingtool.views.actions;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ISelectAsSeedModel;
import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.MetricMethodResult;
import aspectminingtool.util.ViewPartUtil;
import aspectminingtool.views.SeedsGeneral.ViewPartSeeds;

public class SelectMethodAsSeedAction extends Action{

	private IResultsModel model;
	private TableViewer table;
	private String name;
	
	public SelectMethodAsSeedAction(IResultsModel model, TableViewer table, String Name){
		super("Select As Seed");
		this.model = model;
		this.table = table;
		this.name = Name;

	}
	
	public void run() { 
		
		IStructuredSelection sel = (IStructuredSelection)table.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			MetricMethodResult fir = (MetricMethodResult) iter.next();
			Method method = fir.getMetodo();
			ViewPartUtil.selectAsSeed(method, ViewPartSeeds.ID_VIEW, ((ISelectAsSeedModel)model).getRelatedMethods(method), model.getProjectModel(), name);
		}
	}
	
}
