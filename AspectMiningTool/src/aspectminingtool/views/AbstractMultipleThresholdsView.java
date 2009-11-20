package aspectminingtool.views;

import org.eclipse.jface.viewers.ViewerFilter;

import JessIntegrationModel.IResultsModel;

public abstract class AbstractMultipleThresholdsView extends AbstractView{

	protected IResultsModel model;
	
	protected ViewerFilter filterUmbral1;

	protected ViewerFilter filterUmbral2;


	public abstract void setUmbralFilter(String umbral1, String umbral2);
		
	public IResultsModel getModel(){
		return model;
	}
	
}
