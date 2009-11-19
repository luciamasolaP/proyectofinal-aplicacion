package aspectminingtool.views;

import org.eclipse.jface.viewers.ViewerFilter;

import JessIntegrationModel.IResultsModel;

public abstract class AbstractFilterView extends AbstractView{

	protected IResultsModel model;
	protected ViewerFilter filterUmbral;
	protected ViewerFilter filterGetSetter;
//	protected Button buttonSearch;
//    protected CLabel labelSearch;
//    protected Text textSearch;
//    
//	protected String searchHistory = "";
//	protected int searchCounted = 0;
//	protected List<TableItem> searchHistoryResults;
    

	public abstract void setUmbralFilter(String umbral);
	
	public abstract void setGetterSetterFilter(boolean filter);
	
	public IResultsModel getModel(){
		return model;
	}

//	public void locateItemInTable(Table tableLeft, String searchHistory, int searchCounted,List<TableItem> searchHistoryResults) {
//
//		String searchClass = textSearch.getText().toLowerCase();
//		if (!searchClass.equals("")){
//			//si tengo que buscar el siguiente Item, lo busco en el searchHistory
//			if (searchHistory.equals(searchClass)){
//				
//				if (searchCounted >= searchHistoryResults.size()-1)
//					searchCounted = 0;
//				else
//					searchCounted++;
//				
//				
//			}
//			//sino, busco todos los valores y lleno el history
//			else
//			{
//				searchHistory = searchClass;
//				searchCounted = 0;
//				searchHistoryResults = new ArrayList<TableItem>();
//	
//				TableItem[] items = tableLeft.getItems();
//	
//				for (int i = 0 ; i < items.length; i++){
//					if (searchClass.equals(((Results)items[i].getData()).getSearchData())){
//						searchHistoryResults.add(items[i]);
//					}
//				}
//			}
//			if (searchHistoryResults.size() > 0)
//				tableLeft.setSelection(searchHistoryResults.get(searchCounted));
//		}
//		
//	}
	
	
	
}
