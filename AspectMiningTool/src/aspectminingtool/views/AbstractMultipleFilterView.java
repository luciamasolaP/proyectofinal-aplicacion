package aspectminingtool.views;

import org.eclipse.jface.viewers.ViewerFilter;

import JessIntegrationModel.IResultsModel;

public abstract class AbstractMultipleFilterView extends AbstractView{

	protected IResultsModel model;
	
	protected ViewerFilter filterUmbral1;
	protected ViewerFilter filterGetSetter1;
	
	protected ViewerFilter filterUmbral2;
	protected ViewerFilter filterGetSetter2;
	
	protected ViewerFilter filterUmbral3;
	protected ViewerFilter filterGetSetter3;
	
	protected ViewerFilter filterUmbral4;
	protected ViewerFilter filterGetSetter4;
   

	public abstract void setUmbralFilter(String umbral1, String umbral2, String umbral3, String umbral4);
	
	public abstract void setGetterSetterFilter(boolean filter1, boolean filter2, boolean filter3, boolean filter4);

	
	public IResultsModel getModel(){
		return model;
	}
	
}
