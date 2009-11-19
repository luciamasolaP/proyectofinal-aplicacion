package aspectminingtool.popup.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.Algorithms.Algorithm;
import aspectminingtool.Algorithms.Sinergia.SinergiaAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.JessIntegrationModel.Sinergia.SinergiaResultsModel;
import aspectminingtool.dialogs.Sinergia.SinergiaSettings;
import aspectminingtool.model.AspectMiningModel;
import aspectminingtool.views.FanIn.ViewPartFanIn;

public class SinergiaPopUp implements IObjectActionDelegate {

	
	private ISelection selection;
	protected Algorithm algorithm;
	protected List Facts;
	protected ProjectModel pm;
	protected InferenceEngine inferenceEngine;
	/**
	 * Constructor for Action1.
	 */
	public SinergiaPopUp() {
		super();
	}

	public Algorithm getAlgorithm(){
		return new SinergiaAlgorithm();
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
			
			Display display = Display.getDefault();
			
			Shell shell = new Shell(display);
			
			SinergiaSettings inst = new SinergiaSettings(shell, SWT.NULL, javaProject);
			
			inst.open();
//			SinergiaAnalysis sA = new SinergiaAnalysis(javaProject);
//			sA.prueba();
//			Shell shell = new Shell();
//
//			try {
//				new ProgressMonitorDialog(shell).run(true, true, sA);
//				
//				System.out.println("salida del monitor");
//				
//			} catch (InvocationTargetException e) {
//				MessageDialog.openError(shell, "Error", e.getMessage());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				 MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
//			}
			//sA.run(monitor);
			
//			ProjectInspector projectInspector = new ProjectInspector(javaProject,new JessFactsVisitor());
			

			
			//			Shell shell = new Shell();
//			 try {
//				 
//				 new ProgressMonitorDialog(shell).run(true, true, projectInspector);
//		       
//				 Facts = projectInspector.getFacts();
//				
//				 //Results = ejecutar algoritmo de Fan-IN y obtener results
//				 //Results = ejecutar algoritmo de Unique Methods y obtener results
//				 //Results = ejecutar algoritmo de FlowGraphs y obtener results
//				 
//				 //limpiar facts
//				 
//				 inferenceEngine = AspectMiningModel.getInferenceEngine();
//				 
//				 inferenceEngine.setAlgorithm(new FanInAlgorithm());
//				 inferenceEngine.execute(Facts);
//				 List SinergiaFacts = getFanInMetric();
//				 
//				 inferenceEngine.clearEngine();
//				 
//				 inferenceEngine.setAlgorithm(new UniqueMethodsAlgorithm());
//				 inferenceEngine.execute(Facts);
//				 SinergiaFacts.addAll(getUniqueMethodsMetric());
//				 
//				 inferenceEngine.clearEngine();
//				 
//				 inferenceEngine.setAlgorithm(new FlowGraphAlgorithm());
//				 inferenceEngine.execute(Facts);
//				 SinergiaFacts.addAll(getFlowGraphMetric());
//				 
//				 inferenceEngine.clearEngine();
//				 Facts.clear();
//				 
//				 
//				 inferenceEngine.setAlgorithm(getAlgorithm());
//				 
//				 inferenceEngine.execute(SinergiaFacts);
//				 
//				 SinergiaFacts.clear();
//			
//				 showResults(getResults(inferenceEngine));
//				
//			
//			 } catch (InvocationTargetException e) {
//		          MessageDialog.openError(shell, "Error", e.getMessage());
//		        } catch (InterruptedException e) {
//		          MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
//		        }
			
		//	drawResults();

			
		}
		else
			System.out.println("no es una IStructuredSelection");
		
	}

	protected void showResults(IResultsModel results){
		
		try {

//			ViewPartFanIn view = (ViewPartFanIn) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
//			.getActivePage().showView(ViewPartFanIn.ID_VIEW,results.getId() , IWorkbenchPage.VIEW_CREATE);
//			para abrir en multiples vistas, esto y ***			

			ViewPartFanIn view = (ViewPartFanIn) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(ViewPartFanIn.ID_VIEW );
			
			view.setModel(results);

//			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ViewPartFanIn.ID_VIEW,results.getId() , IWorkbenchPage.VIEW_ACTIVATE);
//			**			
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	protected IResultsModel getResults(InferenceEngine InferenceEngine){
//	
//		return new SinergiaResultsModel(null, null, null, pm);
//		
//	}

	/**
	 * guarda la seleccción realizada por el usuario.
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		this.selection = selection;
		
	}

}

