package aspectminingtool.popup.actions;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import aspectminingtool.parser.ASTInspectorProject;

public class PerformMining implements IObjectActionDelegate {

	private ISelection selection;
	
	/**
	 * Constructor for Action1.
	 */
	public PerformMining() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
	}

	/**
	 * Toma la selección. Crea una ICompilationUnit de la misma para poder ejecutarle
	 * realizar lo necesario con AST. En un futuro esto va a ser un proyecto, y se llamará previamente
	 * a una clase que inspeccione al proyecto y esta uno a uno llame a AST.
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		if (selection instanceof IStructuredSelection) {
			ICompilationUnit lwUnit = (ICompilationUnit) ((IStructuredSelection) selection)
					.getFirstElement();
			ASTInspectorProject AIP = new ASTInspectorProject(); 
			AIP.run(lwUnit); // devuelve un AbstractASTArticle y a este se le ejecuta el run(lwUnit)
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
