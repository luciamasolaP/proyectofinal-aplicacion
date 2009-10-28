package aspectminingtool.views.FlowGraph;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.IWorkbenchActionConstants;

import aspectminingtool.views.actions.OpenClassAction;
import aspectminingtool.views.actions.SelectMethodAsSeedAction;

public class MenuLeftListener implements IMenuListener{

	Action selectAll;
	OpenClassAction openAction;
	TableViewer tableViewer;
	SelectMethodAsSeedAction selectSeedAction;
	
	public MenuLeftListener(TableViewer tableViewer, Action selectAll, OpenClassAction openAction, SelectMethodAsSeedAction selectSeedAction){
		this.tableViewer = tableViewer;
		this.selectAll = selectAll;
		this.openAction = openAction;
		this.selectSeedAction = selectSeedAction;
	}

	@Override
	public void menuAboutToShow(IMenuManager manager) {
		
		manager.add(openAction);
		manager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		manager.add(selectSeedAction);
		manager.add(new Separator());
		manager.add(selectAll);
		
	}
	
}
