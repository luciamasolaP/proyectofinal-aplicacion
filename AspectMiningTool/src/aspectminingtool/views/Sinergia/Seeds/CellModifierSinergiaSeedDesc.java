package aspectminingtool.views.Sinergia.Seeds;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;


public class CellModifierSinergiaSeedDesc implements ICellModifier {
	private ViewPartSinergiaSeedsDesc viewPart;
	private String[] columnNames;
	
	/**
	 * Constructor 
	 * @param TableViewerExample an instance of a TableViewerExample 
	 */
	public CellModifierSinergiaSeedDesc(ViewPartSinergiaSeedsDesc viewPart) {
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
				result = task.getTrust();
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
		((SinergiaDescriptionResultsModel) viewPart.getModel()).seedDescriptionChanged(seedDescription);
	}
}
