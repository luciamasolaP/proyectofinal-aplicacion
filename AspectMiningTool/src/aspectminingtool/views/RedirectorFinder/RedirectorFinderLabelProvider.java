package aspectminingtool.views.RedirectorFinder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.Activator;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;
import aspectminingtool.util.MethodFormater;

public class RedirectorFinderLabelProvider implements ITableLabelProvider {

	
	private Map imageCache = new HashMap(2);
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		  ImageDescriptor descriptor = null;
		   if (columnIndex == 0 ) {
			   descriptor = Activator.getImageDescriptor("images/method.gif");
		   } else{
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
		
		if (element instanceof RedirectorFinderResults){
			if (columnIndex == 0) 
		    	return MethodFormater.formatClassIdToString(((RedirectorFinderResults)element).getClaseLlamadora());
		    else if (columnIndex == 1)
		    		return MethodFormater.formatClassIdToString(((RedirectorFinderResults)element).getClaseLlamada());
		    	else if (columnIndex == 2)
		    			return ((RedirectorFinderResults)element).getCantidadRedireccionados();
		    		else if (columnIndex == 3)
		    			return ((RedirectorFinderResults)element).getPercent().toString();
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
