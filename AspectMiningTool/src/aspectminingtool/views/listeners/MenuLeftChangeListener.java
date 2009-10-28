package aspectminingtool.views.listeners;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;

import aspectminingtool.views.actions.OpenClassAction;

public class MenuLeftChangeListener implements ISelectionChangedListener{

	Action selectAll;
	OpenClassAction openAction;
	TableViewer tableViewer;
	Action selectSeedAction;
	
	public MenuLeftChangeListener(TableViewer tableViewer, Action selectAll, OpenClassAction openAction, Action selectSeedAction){
		this.tableViewer = tableViewer;
		this.selectAll = selectAll;
		this.openAction = openAction;
		this.selectSeedAction = selectSeedAction;
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		IStructuredSelection sel = (IStructuredSelection)tableViewer.getSelection();
		selectAll.setEnabled(sel.size() > 0);
		openAction.setEnabled(sel.size() > 0);
		selectSeedAction.setEnabled(sel.size() > 0);
	}

}
