package aspectminingtool.views.FanIn.Filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import aspectminingtool.JessIntegrationModel.MetricMethodResult;

public class FilterFanInUmbral extends ViewerFilter {

	private Integer umbral;

	public FilterFanInUmbral(Integer umbral){
		super();
		this.umbral = umbral;
	}
	
	/**
	 * 
	 * @param umbral
	 */
	public void setUmbralText(String umbral) {
		this.umbral = Integer.valueOf(umbral);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		Integer value = Integer.valueOf(((MetricMethodResult)element).getMetric());
		
		if (umbral <= value) {
			return true;
		}
		return false;
	}
}

