package aspectminingtool.popup.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
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
		/*
		if (selection instanceof IStructuredSelection) {
			ICompilationUnit lwUnit = (ICompilationUnit) ((IStructuredSelection) selection)
					.getFirstElement();
			ASTInspectorProject AIP = new ASTInspectorProject(); 
			AIP.run(lwUnit); // devuelve un AbstractASTArticle y a este se le ejecuta el run(lwUnit)
		}
		*/
				
		if (selection instanceof IStructuredSelection) {
			
			IProject project = (IProject) ((IStructuredSelection) selection).getFirstElement();
						
			IJavaProject javaProject = JavaCore.create(project);
							
			try {
				IPackageFragment[] fragments = javaProject.getPackageFragments();
				for (int i=0;i<fragments.length;i++){
					System.out.println("paquete: "+ fragments[i].getElementName());
					ICompilationUnit[] compUnit = fragments[i].getCompilationUnits();
					for(int y=0;y<compUnit.length;y++){
						System.out.println("compUnit: "+ compUnit[y].getElementName());
						ASTInspectorProject AIP = new ASTInspectorProject(); 
						AIP.run(compUnit[y]);
					}
					
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else
			System.out.println("no es una IStructuredSelection");
		
	}

	/**
	 * guarda la seleccción realizada por el usuario.
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		this.selection = selection;
		
	}

}
