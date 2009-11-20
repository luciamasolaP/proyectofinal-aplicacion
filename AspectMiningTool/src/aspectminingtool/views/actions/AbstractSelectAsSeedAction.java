package aspectminingtool.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;

import JessIntegrationModel.IResultsModel;

public abstract class AbstractSelectAsSeedAction extends Action{

	protected IResultsModel model;
	protected TableViewer table;
	protected String name;
	
	public AbstractSelectAsSeedAction(IResultsModel model, TableViewer table, String Name){
		super("Select As Seed");
		this.model = model;
		this.table = table;
		this.name = Name;

	}
	
	protected abstract String getViewPartSeedsId();
	
	public abstract void run();
		
}
