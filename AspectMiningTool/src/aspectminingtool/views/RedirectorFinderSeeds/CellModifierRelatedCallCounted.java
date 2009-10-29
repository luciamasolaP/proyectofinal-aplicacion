/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.views.RedirectorFinderSeeds;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.RelatedCallCountedDescription;
import aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds.SeedsClassGeneralModel;

public class CellModifierRelatedCallCounted implements ICellModifier {
	private ViewPartClassesSeeds viewPart;
	private String[] columnNames;
	
	/**
	 * Constructor 
	 * @param TableViewerExample an instance of a TableViewerExample 
	 */
	public CellModifierRelatedCallCounted(ViewPartClassesSeeds viewPart) {
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
		RelatedCallCountedDescription relatedMethodDescription = (RelatedCallCountedDescription) element;

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
				result = relatedMethodDescription.toString();
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
		RelatedCallCountedDescription relatedMethodDescription = (RelatedCallCountedDescription) item.getData();
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
		((SeedsClassGeneralModel) viewPart.getModel()).relatedMethodDescriptionChanged(relatedMethodDescription);
	}
}


