package aspectminingtool.views.FlowGraph;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;

public class SelectAllAction extends Action{


	TableViewer tableViewer;
	
	public SelectAllAction(TableViewer tableViewer){
		super("Select All");
		this.tableViewer = tableViewer;
	}
	
	@Override
	public void run() {
		tableViewer.getTable().selectAll();	
	}
	


}
