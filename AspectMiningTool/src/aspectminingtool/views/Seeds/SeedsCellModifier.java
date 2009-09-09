/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.views.Seeds;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;

/**
 * This class implements an ICellModifier An ICellModifier is called when the
 * user modifes a cell in the tableViewer
 */

public class SeedsCellModifier implements ICellModifier {
	private TableViewer tableViewer;
	private String[] columnNames;

	/**
	 * Constructor
	 * 
	 * @param TableViewerExample
	 *            an instance of a TableViewerExample
	 */
	public SeedsCellModifier(TableViewer tableViewer) {
		super();
		tableViewer = tableViewer;
	}

	/**
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
	 *      java.lang.String)
	 */
	public boolean canModify(Object element, String property) {

		if (ViewPartSeeds.METHOD.equals(property))
			return false;
		else if (ViewPartSeeds.DESCRIPTION.equals(property))
			return true;
		return false;

	}

	public Object getValue(Object element, String property) {
		String d = (String) element;
		if (ViewPartSeeds.DESCRIPTION.equals(property))
			return d;
		else
			return null;
	}

	public void modify(Object element, String property, Object value) {

		String d = (String) element;
		if (ViewPartSeeds.DESCRIPTION.equals(property))
			//d.setDescription((String)value);

		tableViewer.refresh();
	}

}
