package aspectminingtool.views.FanIn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.Activator;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import aspectminingtool.model.Call_Counted;

public class CallsLabelProviderFanIn implements ITableLabelProvider {

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
		    return columnIndex == 0 ? ((Call_Counted)element).toString(): "";
		 
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
