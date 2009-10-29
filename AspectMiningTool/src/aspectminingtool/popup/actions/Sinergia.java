package aspectminingtool.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import aspectminingtool.dialogs.SinergiaSettings;

public class Sinergia implements IObjectActionDelegate {

	
	private ISelection selection;

	/**
	 * Constructor for Action1.
	 */
	public Sinergia() {
		super();
	}

	//public abstract Algorithm getAlgorithm();
	
	
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
		Display display = Display.getDefault();
		Shell shell1 = new Shell(display);
		SinergiaSettings inst = new SinergiaSettings(shell1, SWT.NULL, null);
		inst.open();
				
//		if (selection instanceof IStructuredSelection) {
//			
//			IProject project = (IProject) ((IStructuredSelection) selection).getFirstElement();
//						
//			IJavaProject javaProject = JavaCore.create(project);
//						
//			pm = AspectMiningModel.createProjectModel(javaProject);
//			
//			ProjectInspector projectInspector = new ProjectInspector(javaProject,new JessFactsVisitor());
//			
//			Shell shell = new Shell();
//			 try {
//				 
//				 new ProgressMonitorDialog(shell).run(true, true, projectInspector);
//		       
//				 Facts = projectInspector.getFacts();
//				
//				 InferenceEngine inferenceEngine = AspectMiningModel.getInferenceEngine();
//				 
//				 inferenceEngine.setAlgorithm(getAlgorithm());
//				 
//				 inferenceEngine.execute(Facts);
//				 
//				 Facts.clear();
//			
//				 showResults(getResults(inferenceEngine));
//				
//			
//			 } catch (InvocationTargetException e) {
//		          MessageDialog.openError(shell, "Error", e.getMessage());
//		        } catch (InterruptedException e) {
//		          MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
//		        }
//			
//		//	drawResults();
//
//			
//		}
//		else
//			System.out.println("no es una IStructuredSelection");
		
	}

	//protected abstract void showResults(IResultsModel results2);

	//protected abstract IResultsModel getResults(InferenceEngine InferenceEngine);

	/**
	 * guarda la seleccción realizada por el usuario.
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		this.selection = selection;
		
	}

}

