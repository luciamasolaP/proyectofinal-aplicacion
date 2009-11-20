package aspectminingtool.views.FanIn.Filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;

public class FilterRedirQuantity extends ViewerFilter {

	private Integer umbral;

	public FilterRedirQuantity(Integer umbral){
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
		
		Integer value = Integer.valueOf(((RedirectorFinderResults)element).getCantidadRedireccionados());
		
		if (umbral <= value) {
			return true;
		}
		return false;
	}
}

