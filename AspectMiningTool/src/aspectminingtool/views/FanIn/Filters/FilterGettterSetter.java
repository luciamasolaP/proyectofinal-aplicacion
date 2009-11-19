package aspectminingtool.views.FanIn.Filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import aspectminingtool.JessIntegrationModel.MetricMethodResult;

public class FilterGettterSetter extends ViewerFilter {

	private boolean filter;
	private String GETTER = "get";
	private String SETTER = "set";

	public FilterGettterSetter(boolean filter){
		this.filter = filter;
	}
	
	/**
	 * 
	 * @param umbral
	 */
	public void setFilterOut(boolean filter) {
		this.filter = filter;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		
		
		if (filter) {
			String method = ((MetricMethodResult)element).getMetodo().getName();
			if ( method.startsWith(GETTER) || method.startsWith(SETTER))
					return false;
		}
		return true;
	}
}

