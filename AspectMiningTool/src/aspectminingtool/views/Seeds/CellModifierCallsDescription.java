/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.views.Seeds;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

import aspectminingtool.JessIntegrationModel.Seeds.CallDescription;
import aspectminingtool.JessIntegrationModel.Seeds.ModelSeedsFanIn;

/**
 * This class implements an ICellModifier
 * An ICellModifier is called when the user modifes a cell in the 
 * tableViewer
 */

public class CellModifierCallsDescription implements ICellModifier {
	private ViewPartSeeds viewPart;
	private String[] columnNames;
	
	/**
	 * Constructor 
	 * @param TableViewerExample an instance of a TableViewerExample 
	 */
	public CellModifierCallsDescription(ViewPartSeeds viewPart) {
		super();
		this.viewPart = viewPart;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
	 */
	public boolean canModify(Object element, String property) {
		if (property.equals("Caller Method"))
			return false;
		return true;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
	 */
	public Object getValue(Object element, String property) {

		// Find the index of the column
		int columnIndex = viewPart.getColumnNamesCalls().indexOf(property);

		Object result = null;
		CallDescription call = (CallDescription) element;

		switch (columnIndex) {
			case 0 : // SELECTED_COLUMN
				call.getSelected(); 
				
				String stringValue = call.getSelected();
				if (stringValue.equals("yes"))
					result = new Integer(0);
				else
					result = new Integer(1);
				
			break;
			case 1 : // CALL_COUNTED_COLUMN 
				result = call.getCallCounted();
				break;
			case 2 : // DESCRIPTION_COLUMN 
				result = call.getDescription();
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
		int columnIndex	= viewPart.getColumnNamesCalls().indexOf(property);
			
		TableItem item = (TableItem) element;
		CallDescription cd = (CallDescription) item.getData();
		String valueString;

		switch (columnIndex) {
			case 0 :// SELECTED_COLUMN
				Integer i = (Integer)value;			
				if ( i.equals(new Integer(0)))
					cd.setSelected("yes");
				else
					cd.setSelected("no");
				break;
			case 1 : // CALL_COUNTED_COLUMN 
				break;
			case 2 : //DESCRIPTION_COLUMN
				valueString = ((String) value).trim();
				cd.setDescription(valueString);
				break;
			default :
			}
		((ModelSeedsFanIn) viewPart.getModel()).CallDescriptionChanged(cd);
	}
}
