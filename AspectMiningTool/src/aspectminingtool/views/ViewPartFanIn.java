package aspectminingtool.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ViewPartFanIn extends ViewPart {
	public static final String ID_VIEW = "aspectminingtool.views.ViewPartFanIn"; //$NON-NLS-1$

	private TreeViewer ttv;
	private Tree tree = null;
	private IResultsModel model;
	Composite composite1;

	/**
     * 
     */
	public ViewPartFanIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		composite1 = new Composite(parent, SWT.NULL);
		FillLayout composite1Layout = new FillLayout(
				org.eclipse.swt.SWT.HORIZONTAL);
		composite1.setLayout(composite1Layout);
		tree = new Tree(composite1, SWT.BORDER);

		this.createContents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	/**
	 * Cleans up all resources created by this ViewPart.
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * Update the view with the new model. It calls TreeViewer.setInput to fill
	 * the tree with the new model.
	 * 
	 * @param model
	 */
	public void setModel(IResultsModel model) {
		this.model = model;
		super.setPartName("Fan in Results - " + model.getId());
		ttv.setInput(model);
	}

	public IResultsModel getModel() {
		return model;
	}

	public void createContents() {

		ttv = new TreeViewer(tree);

		// Set the sorter
		ViewerSorter sorter = new SorterFanInView();
		ttv.setSorter(sorter);

		// Set the content and label providers
		ttv.setContentProvider(new FanInContentProvider());
		ttv.setLabelProvider(new FanInLabelProvider());

		// Set up the table, each column has a listener for the click that calls
		// the sorter and refreshes the tree.
		// Column 1
		final TreeColumn tc1 = new TreeColumn(tree, SWT.LEFT);
		tc1.setText("Method");
		tc1.setWidth(498);
		tc1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterFanInView) ttv.getSorter()).doSort(0);
				ttv.refresh();
			}
		});

		// Column 2
		TreeColumn tc2 = new TreeColumn(tree, SWT.LEFT);
		tc2.setText("Fan in");
		tc2.setWidth(50);
		tc2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterFanInView) ttv.getSorter()).doSort(1);
				ttv.refresh();
			}
		});

		// Turn on the header and the lines
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

	}

}

