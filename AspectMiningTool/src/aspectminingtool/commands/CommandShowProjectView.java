package aspectminingtool.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import aspectminingtool.dialogs.ProjectTree.SettingsDialogs;
import aspectminingtool.views.AbstractFilterView;


public class CommandShowProjectView extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		AbstractFilterView view = (AbstractFilterView)HandlerUtil.getActivePart(event);
		
		if (view.getModel() != null){
			IJavaProject javaProject = view.getModel().getProjectModel().getProject(); //TODO obtener java project del modelo de la vista
		
			Display display = Display.getDefault();
			Shell shell1 = new Shell(display);
			SettingsDialogs inst = new SettingsDialogs(shell1, SWT.NULL, javaProject,view);
			inst.open();
		}
		
		return null;
	}

}
