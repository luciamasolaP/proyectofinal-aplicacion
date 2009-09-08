package aspectminingtool.views.Seeds;

import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;

public class ViewPartSeeds extends ViewPart {
    public static final String ID_VIEW =
        "aspectminingtool.views.Seeds.ViewPartSeeds"; //$NON-NLS-1$

    private TableViewerExample viewer;

	/**
	 * The constructor.
	 */
	public ViewPartSeeds() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewerExample(parent);
		viewer.getCloseButton().addSelectionListener(new SelectionAdapter() {
       	
			// Close the view i.e. dispose of the composite's parent
			public void widgetSelected(SelectionEvent e) {
				handleDispose();
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	/**
	 * Handle a 'close' event by disposing of the view
	 */

	public void handleDispose() {	
		this.getSite().getPage().hideView(this);
	}
    
}
