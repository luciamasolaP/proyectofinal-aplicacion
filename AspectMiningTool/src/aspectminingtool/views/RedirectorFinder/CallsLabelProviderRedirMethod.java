package aspectminingtool.views.RedirectorFinder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.Activator;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.util.MethodFormater;

public class CallsLabelProviderRedirMethod implements ITableLabelProvider {

	private Map imageCache = new HashMap(11);
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		ImageDescriptor descriptor = Activator.getImageDescriptor("images/call.gif");
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
		
		if (element instanceof Call_Counted)
		    if (columnIndex == 0)
		    	return((Call_Counted)element).toString();
		    else if (columnIndex == 1){
		    	String caller_id = ((Call_Counted)element).getCalle_id();
		    	String paquete = MethodFormater.formatPackageFromMethodId(caller_id);
				//class_id.substring(0, index);
				String clase = MethodFormater.formatClassFromMethodId(caller_id); 
				String parameters = caller_id.substring(caller_id.indexOf("///")+3); 
				parameters = MethodFormater.formatParameters(parameters);
				String name = MethodFormater.formatMethodName(caller_id);
				return name + "(" + parameters + "):" +"  Clase: "+clase+"   Paquete: "+ paquete;
	    
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
