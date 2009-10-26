package aspectminingtool.views.SeedsGeneral;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedDescription;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedsGeneralModel;
import aspectminingtool.JessIntegrationModel.Seeds.MethodDescription;
import aspectminingtool.JessIntegrationModel.Seeds.SeedsModel;


public class CellModifierSeedDescription implements ICellModifier {
	private ViewPartSeeds viewPart;
	private String[] columnNames;
	
	/**
	 * Constructor 
	 * @param TableViewerExample an instance of a TableViewerExample 
	 */
	public CellModifierSeedDescription(ViewPartSeeds viewPart) {
		super();
		this.viewPart = viewPart;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
	 */
	public boolean canModify(Object element, String property) {
		if (property.equals("Method") || property.equals("Algorithm"))
			return false;
		return true;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
	 */
	public Object getValue(Object element, String property) {

		// Find the index of the column
		int columnIndex = viewPart.getColumnNamesMethods().indexOf(property);

		Object result = null;
		SeedDescription task = (SeedDescription) element;

		switch (columnIndex) {
			case 0 : // METHOD_COLUMN 
				result = task.getMethod();
				break;
			case 1: // ALGORITHM_COLUMN
				result = task.getAlgoritmo();
				break;
			case 2 : // DESCRIPTION_COLUMN 
				result = task.getDescription();
				break;
			
			default :
				result = "";
		}
		return result;	
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public void modify(Object element, String property, Object value) {	

		// Find the index of the column 
		int columnIndex	= viewPart.getColumnNamesMethods().indexOf(property);
			
		TableItem item = (TableItem) element;
		SeedDescription seedDescription = (SeedDescription) item.getData();
		String valueString;

		switch (columnIndex) {
			case 0 : // METHOD_COLUMN 
				break;
			case 1: //ALGOITHM_COLUMN
				break;
			case 2 : // DESCRIPTION_COLUMN 
				valueString = ((String) value).trim();
				seedDescription.setDescription(valueString);
				break;

			default :
			}
		((SeedsGeneralModel) viewPart.getModel()).seedDescriptionChanged(seedDescription);
	}
}
