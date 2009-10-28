package aspectminingtool.views.FlowGraph;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import aspectminingtool.views.actions.OpenClassAction;

public class MenuRightChangeListener implements ISelectionChangedListener{

	Action selectAll;
	OpenClassAction openAction;
	TableViewer tableViewer;
	
	public MenuRightChangeListener(TableViewer tableViewer, Action selectAll, OpenClassAction openAction){
		this.tableViewer = tableViewer;
		this.selectAll = selectAll;
		this.openAction = openAction;
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		IStructuredSelection sel = (IStructuredSelection)tableViewer.getSelection();
		selectAll.setEnabled(sel.size() > 0);
		openAction.setEnabled(sel.size() > 0);
	}

}
