package aspectminingtool.popup.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;



import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.data.Fact;
import aspectminingtool.parser.JessFactsVisitor;
import aspectminingtool.parser.ProjectInspector;


public abstract class AbstractPerformMiningPopup implements IObjectActionDelegate {

	
	private ISelection selection;
	protected Algorithm algorithm;
	protected ArrayList Facts;
	
	/**
	 * Constructor for Action1.
	 */
	public AbstractPerformMiningPopup() {
		super();
	}

	public abstract void setAlgorithm(InferenceEngine inferenceEngine);
	
	public void getResults() {
		
		algorithm.viewResults();
		
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
			
			IProject project = (IProject) ((IStructuredSelection) selection).getFirstElement();
						
			IJavaProject javaProject = JavaCore.create(project);
							
			ProjectInspector projectInspector = new ProjectInspector(javaProject,new JessFactsVisitor());
			Shell shell = new Shell();
			 try {
				 
		          new ProgressMonitorDialog(shell).run(true, true, projectInspector);
		       
			
				Facts = projectInspector.getFacts();
				InferenceEngine inferenceEngine = new JessInferenceEngine();
				setAlgorithm(inferenceEngine);
				inferenceEngine.setAlgorithm(algorithm);
				inferenceEngine.execute(Facts);
				
				getResults();
			
			 } catch (InvocationTargetException e) {
		          MessageDialog.openError(shell, "Error", e.getMessage());
		        } catch (InterruptedException e) {
		          MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
		        }
			
		//	drawResults();

			
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

