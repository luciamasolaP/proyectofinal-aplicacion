package aspectminingtool.views.RedirectorFinderSeeds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.Activator;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;
import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.RelatedCallCountedDescription;


/**
 * Label provider for the TableViewerExample
 * 
 * @see org.eclipse.jface.viewers.LabelProvider 
 */
public class LabelProviderRelatedCallCounted implements ITableLabelProvider {

	private Map imageCache = new HashMap(3);


	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		RelatedCallCountedDescription relatedCallCountedDescription = (RelatedCallCountedDescription) element;
		switch (columnIndex) {
			case 0 :
				//result = relatedMethodDescription.getSelected();
				break;
			case 1 :
				result = relatedCallCountedDescription.toString();
				break;
			case 2 :
				result = relatedCallCountedDescription.getDescription();
				break;
	
		}
		return result;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		  ImageDescriptor descriptor = null;
		   if (columnIndex == 0 ){
			   if (((RelatedCallCountedDescription)element).getSelected().equals("yes"))
				   descriptor = Activator.getImageDescriptor("images/add.gif");
			   else
				   descriptor = Activator.getImageDescriptor("images/remove.gif");
		   } else if (columnIndex ==  1) {
			   descriptor = null; 
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
	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void dispose() {
		 for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
				((Image) i.next()).dispose();
			}
			imageCache.clear();
		
	}



	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}


}



