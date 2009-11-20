package aspectminingtool.views.FanIn.Filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;

public class FilterRedirPercent extends ViewerFilter {

	private Double umbral;

	public FilterRedirPercent(Double umbral){
		super();
		this.umbral = umbral;
	}
	
	/**
	 * 
	 * @param umbral
	 */
	public void setUmbralText(String umbral) {
		this.umbral = Double.valueOf(umbral);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		Double value = ((RedirectorFinderResults)element).getPercent();
		
		if (umbral <= value) {
			return true;
		}
		return false;
	}
}

