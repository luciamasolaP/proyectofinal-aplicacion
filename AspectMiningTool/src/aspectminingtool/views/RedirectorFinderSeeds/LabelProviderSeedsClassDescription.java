/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 */

package aspectminingtool.views.RedirectorFinderSeeds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import aspectminingtool.Activator;
import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.SeedClassDescription;


/**
 * Label provider for the TableViewerExample
 * 
 * @see org.eclipse.jface.viewers.LabelProvider 
 */
public class LabelProviderSeedsClassDescription 
	extends LabelProvider
	implements ITableLabelProvider {


	private Map imageCache = new HashMap(2);
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		  ImageDescriptor descriptor = null;
		   if (columnIndex == 0 ) {
			   descriptor = Activator.getImageDescriptor("images/method.gif");
		   } else if (columnIndex ==  1) {
			   descriptor = null; 
		   	} else if (columnIndex == 2){
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
	

	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		SeedClassDescription seedClassDesc = (SeedClassDescription) element;
		switch (columnIndex) {
			case 0 :
				result = seedClassDesc.toString();
				break;
			case 1 :
				result = seedClassDesc.getAlgoritmo();
				break;
			case 2:
				result = seedClassDesc.getDescription();
	
		}
		return result;
	}

	@Override
	public void dispose() {
		  for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
				((Image) i.next()).dispose();
			}
			imageCache.clear();
		
	}

}
