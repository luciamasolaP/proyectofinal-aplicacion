package aspectminingtool.parser;

import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;

import aspectminingtool.data.Fact;


/**
 * ProjectInspector is responsible of walking through the project representation, as well as calling the ASTClassInspector in order to get the facts representing each class. 
 * 
 * @author maria
 *
 */
public class ProjectInspector{


	
	public ProjectInspector(){
	}
	                     
		
	public ArrayList getProjectFacts(IJavaProject javaProject, FactsVisitor visitor){
		
		ArrayList facts = new ArrayList();
		
		try {
			IPackageFragment[] fragments = javaProject.getPackageFragments();
			
			for (int i=0 ; i < fragments.length ; i++){
				ICompilationUnit[] compUnit = fragments[i].getCompilationUnits();
				
				for(int y=0 ; y < compUnit.length ; y++){
					ASTClassInspector AIP = new ASTClassInspector(); 
					facts.addAll(AIP.getCompilationFacts(compUnit[y],visitor));
				}
				
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return facts;
		
	}
	
	
	
}
