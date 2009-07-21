package aspectminingtool.treeView;

import java.util.Vector;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;




class ProjectTreeContentProvider implements ITreeContentProvider {
  
	private IJavaProject root;
	
	public ProjectTreeContentProvider(IJavaProject project){
		this.root = project;
	}
	
	/**
   * Gets the children of the specified object
   * 
   * @param arg0
   *            the parent object
   * @return Object[]
   */
  public Object[] getChildren(Object arg0) {

	  Vector children = new Vector();
	  
	  if(arg0 instanceof IJavaProject) {
		  	IJavaProject root = (IJavaProject)arg0;
			IPackageFragmentRoot[] p;
			try {
				p = root.getAllPackageFragmentRoots();
				for(IPackageFragmentRoot pfr : p){
					if( pfr.getKind() == IPackageFragmentRoot.K_SOURCE){
						children.add(pfr);
					}
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }else if (arg0 instanceof IPackageFragmentRoot){
		  IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot)arg0;
		  IJavaElement[] hijos;
		  try {
			  hijos = packageFragmentRoot.getChildren();
			  for(int i=0; i<hijos.length; i++){
					IPackageFragment ipf = (IPackageFragment)hijos[i];
					children.add(ipf);
			  }
		  } catch (JavaModelException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		 
	  	}else if (arg0 instanceof IPackageFragment){
	  		IPackageFragment ipf = (IPackageFragment)arg0;
			try {
				for (ICompilationUnit unit : ipf.getCompilationUnits()) {
					children.add(unit);
					}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  
	  }
	  
	  return children.toArray();

  }

  /**
   * Gets the parent of the specified object
   * 
   * @param arg0
   *            the object
   * @return Object
   */
  public Object getParent(Object arg0) {

	  IJavaElement javaElement = (IJavaElement)arg0;
	  return javaElement.getParent();
  }

  /**
   * Returns whether the passed object has children
   * 
   * @param arg0
   *            the parent object
   * @return boolean
   */
  public boolean hasChildren(Object arg0) {
    // Get the children
    Object[] obj = getChildren(arg0);

    // Return whether the parent has children
    return obj == null ? false : obj.length > 0;
  }

  /**
   * Gets the root element(s) of the tree
   * 
   * @param arg0
   *            the input data
   * @return Object[]
   */
  public Object[] getElements(Object arg0) {

	  Object[] ret = new Object[1];
	  ret[0] = this.root;
	  return ret;
	 
  }

  /**
   * Disposes any created resources
   */
  public void dispose() {
    // Nothing to dispose
  }

  /**
   * Called when the input changes
   * 
   * @param arg0
   *            the viewer
   * @param arg1
   *            the old input
   * @param arg2
   *            the new input
   */
  public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
    // Nothing to change
  }
}
