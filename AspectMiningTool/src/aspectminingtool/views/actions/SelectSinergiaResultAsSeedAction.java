package aspectminingtool.views.actions;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.JessIntegrationModel.Sinergia.Seed;
import aspectminingtool.JessIntegrationModel.Sinergia.SinergiaResultsModel;
import aspectminingtool.util.ViewPartUtil;
import aspectminingtool.views.Sinergia.Seeds.ViewPartSinergiaSeedsDesc;

public class SelectSinergiaResultAsSeedAction extends AbstractSelectAsSeedAction{

	
	public SelectSinergiaResultAsSeedAction(IResultsModel model,
			TableViewer table, String Name) {
		super(model, table, Name);
	}

	protected String getViewPartSeedsId(){
		return ViewPartSinergiaSeedsDesc.ID_VIEW;
	}
	
	public void run() { 
		
		IStructuredSelection sel = (IStructuredSelection)table.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			Seed seed = (Seed) iter.next();
			ViewPartUtil.selectSinergiaResultAsSeed(seed, getViewPartSeedsId(), ((SinergiaResultsModel)model).getAlgorithmsResults(seed), model.getProjectModel());
		}
	}
		
}
