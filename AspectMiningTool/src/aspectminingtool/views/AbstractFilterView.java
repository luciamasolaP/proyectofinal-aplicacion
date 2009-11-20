package aspectminingtool.views;

import org.eclipse.jface.viewers.ViewerFilter;

import JessIntegrationModel.IResultsModel;

public abstract class AbstractFilterView extends AbstractView{

	protected IResultsModel model;
	protected ViewerFilter filterUmbral;
	protected ViewerFilter filterGetSetter;
 

	public abstract void setUmbralFilter(String umbral);
	
	public abstract void setGetterSetterFilter(boolean filter);
	
	public IResultsModel getModel(){
		return model;
	}
	
}
