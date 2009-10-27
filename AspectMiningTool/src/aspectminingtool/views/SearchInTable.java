package aspectminingtool.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import aspectminingtool.JessIntegrationModel.Results;

public class SearchInTable {

	public String searchHistory = "";
	public int searchCounted = 0;
	public List<TableItem> searchHistoryResults;
	
	public void locateItemInTable(String textSearch, Table tableLeft) {
		
		if (!textSearch.equals("")){
			//si tengo que buscar el siguiente Item, lo busco en el searchHistory
			if (searchHistory.equals(textSearch)){
				
				if (searchCounted >= searchHistoryResults.size()-1)
					searchCounted = 0;
				else
					searchCounted++;
	
				
			}
			//sino, busco todos los valores y lleno el history
			else
			{
				searchHistory = textSearch;
				searchCounted = 0;
				searchHistoryResults = new ArrayList<TableItem>();
	
				TableItem[] items = tableLeft.getItems();
	
				for (int i = 0 ; i < items.length; i++){
					if (textSearch.equals(((Results)items[i].getData()).getSearchData())){
						searchHistoryResults.add(items[i]);
					}
				}
			}
			if (searchHistoryResults.size() > 0)
				tableLeft.setSelection(searchHistoryResults.get(searchCounted));
		}
		
	}
	
	
	
}
