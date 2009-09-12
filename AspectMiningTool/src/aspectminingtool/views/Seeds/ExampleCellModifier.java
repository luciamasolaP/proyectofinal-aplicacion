/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.views.Seeds;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

/**
 * This class implements an ICellModifier
 * An ICellModifier is called when the user modifes a cell in the 
 * tableViewer
 */

public class ExampleCellModifier implements ICellModifier {
	private ViewPartSeeds viewPart;
	private String[] columnNames;
	
	/**
	 * Constructor 
	 * @param TableViewerExample an instance of a TableViewerExample 
	 */
	public ExampleCellModifier(ViewPartSeeds viewPart) {
		super();
		this.viewPart = viewPart;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
	 */
	public boolean canModify(Object element, String property) {
		if (property.equals("Method"))
			return false;
		return true;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
	 */
	public Object getValue(Object element, String property) {

		// Find the index of the column
		int columnIndex = viewPart.getColumnNames().indexOf(property);

		Object result = null;
		MethodDescription task = (MethodDescription) element;

		switch (columnIndex) {
			case 0 : // METHOD_COLUMN 
				result = task.getMethod();
				break;
			case 1 : // DESCRIPTION_COLUMN 
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
		int columnIndex	= viewPart.getColumnNames().indexOf(property);
			
		TableItem item = (TableItem) element;
		MethodDescription task = (MethodDescription) item.getData();
		String valueString;

		switch (columnIndex) {
			case 0 : // METHOD_COLUMN 
				break;
			case 1 : // DESCRIPTION_COLUMN 
				valueString = ((String) value).trim();
				task.setDescription(valueString);
				break;
			default :
			}
		viewPart.getTaskList().taskChanged(task);
	}
}
