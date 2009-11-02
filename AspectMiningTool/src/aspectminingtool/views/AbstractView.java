package aspectminingtool.views;

import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;

public abstract class AbstractView extends ViewPart{

	protected IResultsModel model;
	
//	protected Button buttonSearch;
//    protected CLabel labelSearch;
//    protected Text textSearch;
//    
//	protected String searchHistory = "";
//	protected int searchCounted = 0;
//	protected List<TableItem> searchHistoryResults;
    

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
