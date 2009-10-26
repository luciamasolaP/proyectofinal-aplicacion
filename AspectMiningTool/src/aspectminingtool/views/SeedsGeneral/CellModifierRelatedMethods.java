/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.views.SeedsGeneral;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedsGeneralModel;

/**
 * This class implements an ICellModifier
 * An ICellModifier is called when the user modifes a cell in the 
 * tableViewer
 */

public class CellModifierRelatedMethods implements ICellModifier {
	private ViewPartSeeds viewPart;
	private String[] columnNames;
	
	/**
	 * Constructor 
	 * @param TableViewerExample an instance of a TableViewerExample 
	 */
	public CellModifierRelatedMethods(ViewPartSeeds viewPart) {
		super();
		this.viewPart = viewPart;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
	 */
	public boolean canModify(Object element, String property) {
		if (property.equals(viewPart.RMETHOD_NAME_COLUMN))
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
		RelatedMethodDescription relatedMethodDescription = (RelatedMethodDescription) element;

		switch (columnIndex) {
			case 0 : // SELECTED_COLUMN
				relatedMethodDescription.getSelected(); 
				
				String stringValue = relatedMethodDescription.getSelected();
				if (stringValue.equals("yes"))
					result = new Integer(0);
				else
					result = new Integer(1);
				
			break;
			case 1 : // RELATED_METHOD_COLUMN 
				result = relatedMethodDescription.getRelatedMethod();
				break;
			case 2 : // DESCRIPTION_COLUMN 
				result = relatedMethodDescription.getDescription();
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
		RelatedMethodDescription relatedMethodDescription = (RelatedMethodDescription) item.getData();
		String valueString;

		switch (columnIndex) {
			case 0 :// SELECTED_COLUMN
				Integer i = (Integer)value;			
				if ( i.equals(new Integer(0)))
					relatedMethodDescription.setSelected("yes");
				else
					relatedMethodDescription.setSelected("no");
				break;
			case 1 : // RELATED_METHOD_COLUMN 
				break;
			case 2 : //DESCRIPTION_COLUMN
				valueString = ((String) value).trim();
				relatedMethodDescription.setDescription(valueString);
				break;
			default :
			}
		((SeedsGeneralModel) viewPart.getModel()).relatedMethodDescriptionChanged(relatedMethodDescription);
	}
}


