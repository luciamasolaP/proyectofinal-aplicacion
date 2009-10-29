package aspectminingtool.views.actions;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ISelectClassAsSeedModel;
import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;
import aspectminingtool.util.ViewPartUtil;
import aspectminingtool.views.RedirectorFinderSeeds.ViewPartClassesSeeds;

public class SelectClassAsSeedAction extends Action{

	private IResultsModel model;
	private TableViewer table;
	private String name;
	
	public SelectClassAsSeedAction(IResultsModel model, TableViewer table, String Name){
		super("Select As Seed");
		this.model = model;
		this.table = table;
		this.name = Name;

	}
	
	public void run() { 
		
		IStructuredSelection sel = (IStructuredSelection)table.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			RedirectorFinderResults redirecFinder = (RedirectorFinderResults) iter.next();
			//Method method = fir.getMetodo();
			ViewPartUtil.selectClassAsSeed(redirecFinder, ViewPartClassesSeeds.ID_VIEW, ((ISelectClassAsSeedModel)model).getRelatedMethods(redirecFinder, name), model.getProjectModel(), name);
		}
	}
	
}
