package aspectminingtool.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

import aspectminingtool.data.Fact;


/**
 * ProjectInspector is responsible of walking through the project representation, as well as calling the ASTClassInspector in order to get the facts representing each class. 
 * 
 * @author maria
 *
 */
public class ProjectInspector implements IRunnableWithProgress{

	private ArrayList facts = new ArrayList();
	private IJavaProject javaProject;
	private FactsVisitor visitor;
	
	public ProjectInspector(IJavaProject javaProject, FactsVisitor visitor){
		this.javaProject = javaProject;
		this.visitor = visitor;
	}
	
	public ArrayList getFacts(){
		return facts;
	}
	

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		monitor.beginTask("Executing Analysis...", IProgressMonitor.UNKNOWN);
        
		try {

			IPackageFragment[] fragments = javaProject.getPackageFragments();
								
			for (int i = 0; i < fragments.length; i++) {
				ICompilationUnit[] compUnit = fragments[i]
						.getCompilationUnits();

				for (int y = 0; y < compUnit.length; y++) {
					ASTClassInspector AIP = new ASTClassInspector();
					facts.addAll(AIP.getCompilationFacts(compUnit[y],visitor));
					
					if (monitor.isCanceled())
						//System.out.println("cancel");
						throw new InterruptedException("The long running operation was cancelled");
				}

			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		
        monitor.done();
      //  return Status.OK_STATUS;
			
	}
			    	
			
		
}
	
	
	
		

