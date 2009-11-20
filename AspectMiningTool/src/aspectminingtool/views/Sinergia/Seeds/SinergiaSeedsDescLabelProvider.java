package aspectminingtool.views.Sinergia.Seeds;

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

public class SinergiaSeedsDescLabelProvider implements ITableLabelProvider {

	
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
		
		if (element instanceof SeedDescription){
			SeedDescription seedDescription = (SeedDescription)element;
			if (columnIndex == 0) 
		    	return MethodFormater.formatMethodIdToString(seedDescription.getMethod());
		    else if (columnIndex == 1)
		    	return seedDescription.getTrust();
		    else if ((columnIndex == 2))
		    	return seedDescription.getDescription();
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
