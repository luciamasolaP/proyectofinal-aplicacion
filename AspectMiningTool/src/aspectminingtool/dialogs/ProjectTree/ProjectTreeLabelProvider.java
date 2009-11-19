package aspectminingtool.dialogs.ProjectTree;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;

import aspectminingtool.Activator;



/**
 * This class provides the labels for the file tree
 */

class ProjectTreeLabelProvider extends LabelProvider {
  /*
  // Images for tree nodes
  private Image project;
  private Image packageFolder;
  private Image packageObj;
  private Image classIm;
*/
  private Map imageCache = new HashMap(11);


  /**
   * Constructs a FileTreeLabelProvider
   */
  public ProjectTreeLabelProvider() {
  
  }

  /**
   * Gets the image to display for a node in the tree
   * 
   * @param arg0
   *            the node
   * @return Image
   */
  public Image getImage(Object arg0) {
    
	  ImageDescriptor descriptor = null;
	   if (arg0 instanceof IJavaProject) {
		   descriptor = Activator.getImageDescriptor("images/prj_obj.gif");
	   } else if (arg0 instanceof IPackageFragmentRoot) {
		   descriptor = Activator.getImageDescriptor("images/packagefolder_obj.gif");
	   } else if (arg0 instanceof IPackageFragment) {
	       descriptor = Activator.getImageDescriptor("images/package_obj.gif");
	   } else if (arg0 instanceof ICompilationUnit) {
		   descriptor = Activator.getImageDescriptor("images/classf_obj.gif");
	   } else {
			throw unknownElement(arg0);
		}
	  
	   if (descriptor == null){
		   return null;
	   } else{
		   //obtain the cached image corresponding to the descriptor
		   Image image = (Image)imageCache.get(descriptor);
		   if (image == null) {
		       image = descriptor.createImage();
		       imageCache.put(descriptor, image);
		   }
		   return image;
	   }

  }

  /**
   * Gets the text to display for a node in the tree
   * 
   * @param arg0
   *            the node
   * @return String
   */
  public String getText(Object arg0) {

	  String name = "";
	  if(arg0 instanceof IJavaProject) {
			IJavaProject root = (IJavaProject)arg0;
			name = root.getElementName();
			
	  }else if (arg0 instanceof IPackageFragmentRoot){
		  IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot)arg0;
		  name = packageFragmentRoot.getElementName();
 
	  	}else if (arg0 instanceof IPackageFragment){
	  		IPackageFragment ipf = (IPackageFragment)arg0;
	  		name = ipf.getElementName();

		}
	  	else if (arg0 instanceof ICompilationUnit){
	  		ICompilationUnit unit = (ICompilationUnit)arg0;
	  		name = unit.getElementName();

	  	}
  
	  return name;

  }



  /**
   * Called when this LabelProvider is being disposed
   */
  public void dispose() {
	  
	  for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
			((Image) i.next()).dispose();
		}
		imageCache.clear();
  }

  protected RuntimeException unknownElement(Object element) {
		return new RuntimeException("Unknown type of element in tree of type " + element.getClass().getName());
	}
  
}