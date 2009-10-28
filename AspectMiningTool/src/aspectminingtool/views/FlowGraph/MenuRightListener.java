package aspectminingtool.views.FlowGraph;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IWorkbenchActionConstants;

import aspectminingtool.views.actions.OpenClassAction;

public class MenuRightListener implements IMenuListener{

	Action selectAll;
	OpenClassAction openAction;
	TableViewer tableViewer;

	
	public MenuRightListener(TableViewer tableViewer, Action selectAll, OpenClassAction openAction){
		this.tableViewer = tableViewer;
		this.selectAll = selectAll;
		this.openAction = openAction;
	}

	@Override
	public void menuAboutToShow(IMenuManager manager) {
		
		manager.add(openAction);
		manager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		manager.add(new Separator());
		manager.add(selectAll);
		
	}
	
}
