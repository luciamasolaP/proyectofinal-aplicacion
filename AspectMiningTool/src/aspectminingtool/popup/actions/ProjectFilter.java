package aspectminingtool.popup.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;


import aspectminingtool.treeView.SettingsDialogs;

public class ProjectFilter implements IObjectActionDelegate {

	private ISelection selection;
	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public ProjectFilter() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * Toma la selección. Crea una ICompilationUnit de la misma para poder ejecutarle
	 * realizar lo necesario con AST. En un futuro esto va a ser un proyecto, y se llamará previamente
	 * a una clase que inspeccione al proyecto y esta uno a uno llame a AST.
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		if (selection instanceof IStructuredSelection) {
			
			IProject project = (IProject) ((IStructuredSelection) selection).getFirstElement();
			IJavaProject javaProject = JavaCore.create(project);
		
			SettingsDialogs dialog = new SettingsDialogs(shell,javaProject);
			dialog.open();
		
		}
		
		
		
	}

	/**
	 * guarda la seleccción realizada por el usuario.
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		this.selection = selection;
		
	}

}
