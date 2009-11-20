package aspectminingtool.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import aspectminingtool.dialogs.ProjectTree.SettingsDialogsExecRelations;
import aspectminingtool.views.AbstractMultipleFilterView;


public class CommandShowFiltersExecRel extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		AbstractMultipleFilterView view = (AbstractMultipleFilterView)HandlerUtil.getActivePart(event);
		
		if (view.getModel() != null){
		
			Display display = Display.getDefault();
			Shell shell1 = new Shell(display);
			SettingsDialogsExecRelations inst = new SettingsDialogsExecRelations(shell1, SWT.NULL, view);
			inst.open();
		}
		
		return null;
	}

}
