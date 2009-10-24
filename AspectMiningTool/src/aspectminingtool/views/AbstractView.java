package aspectminingtool.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.JessIntegrationModel.Results;

public abstract class AbstractView extends ViewPart{

	protected IResultsModel model;
	protected TableViewer tableViewerLeft;
	protected Table tableLeft;
	protected Table tableRight;
	protected TableViewer tableViewerRight;
	
	protected Button buttonSearch;
    protected CLabel labelSearch;
    protected Text textSearch;
    
	protected String searchHistory = "";
	protected int searchCounted = 0;
	protected List<TableItem> searchHistoryResults;
    
	
	
	public IResultsModel getModel(){
		return model;
	}
	
	public void locateItemInTable() {

		String searchClass = textSearch.getText().toLowerCase();
		if (!searchClass.equals("")){
			//si tengo que buscar el siguiente Item, lo busco en el searchHistory
			if (searchHistory.equals(searchClass)){
				
				if (searchCounted >= searchHistoryResults.size()-1)
					searchCounted = 0;
				else
					searchCounted++;
				
				
			}
			//sino, busco todos los valores y lleno el history
			else
			{
				searchHistory = searchClass;
				searchCounted = 0;
				searchHistoryResults = new ArrayList<TableItem>();
	
				TableItem[] items = tableLeft.getItems();
	
				for (int i = 0 ; i < items.length; i++){
					if (searchClass.equals(((Results)items[i].getData()).getSearchData())){
						searchHistoryResults.add(items[i]);
					}
				}
			}
			if (searchHistoryResults.size() > 0)
				tableLeft.setSelection(searchHistoryResults.get(searchCounted));
		}
		
	}
	
}
