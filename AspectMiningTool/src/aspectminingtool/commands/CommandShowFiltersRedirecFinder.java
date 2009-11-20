package aspectminingtool.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import aspectminingtool.dialogs.ProjectTree.SettingsDialogsRedirFinder;
import aspectminingtool.views.AbstractMultipleThresholdsView;


public class CommandShowFiltersRedirecFinder extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		AbstractMultipleThresholdsView view = (AbstractMultipleThresholdsView)HandlerUtil.getActivePart(event);
		
		if (view.getModel() != null){
		
			Display display = Display.getDefault();
			Shell shell1 = new Shell(display);
			SettingsDialogsRedirFinder inst = new SettingsDialogsRedirFinder(shell1, SWT.NULL, view);
			inst.open();
		}
		
		return null;
	}

}
