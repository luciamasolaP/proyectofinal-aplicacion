package aspectminingtool.views.Sinergia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.Activator;
import aspectminingtool.JessIntegrationModel.Sinergia.Seed;
import aspectminingtool.util.MethodFormater;

public class SinergiaSeedsLabelProvider implements ITableLabelProvider {

	
	private Map imageCache = new HashMap(2);
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		  ImageDescriptor descriptor = null;
		   if (columnIndex == 0 ) {
			   descriptor = Activator.getImageDescriptor("images/method.gif");
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
	public String getColumnText(Object element, int columnIndex) {
		
		if (element instanceof Seed){
			if (columnIndex == 0) 
		    	return MethodFormater.formatMethodIdToString(((Seed)element).getMethod());
		    else if (columnIndex == 1)
		    	return ((Seed)element).getTrust();
		}
		 
		return "";
	}

	

	@Override
	public void dispose() {
		  for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
				((Image) i.next()).dispose();
			}
			imageCache.clear();
		
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		
		
	}

}
