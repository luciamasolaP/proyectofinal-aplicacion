package aspectminingtool.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.internal.Model;
import org.eclipse.ui.part.ViewPart;

import aspectminingtool.JessIntegrationModel.FanIn.Call_Counted;
import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;

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
	private SashForm sashForm1;
	private TableColumn tableCallsColumn1;
	private Table callsTable;
	private TableViewer callsTableViewer;
	private Composite composite2;
	private TableViewer methodsTableViewer;
	private Table methodsTable = null;

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
		FillLayout parentLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
		parent.setLayout(parentLayout);
		{
			sashForm1 = new SashForm(parent, SWT.NONE);
			sashForm1.setSize(60, 30);
			{
				composite1 = new Composite(sashForm1, SWT.NULL);
				FillLayout composite1Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite1.setLayout(composite1Layout);
				composite1.setBounds(-483, -25, 461, 81);
				
				methodsTable = new Table(composite1, SWT.BORDER);
				methodsTableViewer = new TableViewer(methodsTable);

				// Set the sorter
				ViewerSorter sorter = new SorterFanInView();
				methodsTableViewer.setSorter(sorter);

				// Set the content and label providers
				methodsTableViewer
						.setContentProvider(new FanInContentProvider());
				methodsTableViewer.setLabelProvider(new FanInLabelProvider());

				// Set up the table, each column has a listener for the click
				// that calls
				// the sorter and refreshes the tree.
				// Column 1
				final TableColumn tc1 = new TableColumn(methodsTable, SWT.LEFT);
				tc1.setText("Method");
				tc1.setWidth(498);
				tc1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInView) methodsTableViewer
										.getSorter()).doSort(0);
								methodsTableViewer.refresh();
							}
						});

				// Column 2
				TableColumn tc2 = new TableColumn(methodsTable, SWT.LEFT);
				tc2.setText("Fan in");
				tc2.setWidth(50);
				tc2
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInView) methodsTableViewer
										.getSorter()).doSort(1);
								methodsTableViewer.refresh();
							}
						});

				// Turn on the header and the lines
				methodsTable.setHeaderVisible(true);
				methodsTable.setLinesVisible(true);

				methodsTableViewer
						.addSelectionChangedListener(new ISelectionChangedListener() {
							public void selectionChanged(
									SelectionChangedEvent event) {
								selectionItem(event);

							}

						});

			}
			{
				composite2 = new Composite(sashForm1, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite2.setLayout(composite2Layout);
				composite2.setBounds(0, 0, 77, 81);
				{
					callsTable = new Table(composite2, SWT.LEFT);
					callsTableViewer = new TableViewer(callsTable);
					
					// Set the sorter
					ViewerSorter sorterCalls = new SorterFanInViewCalls();
					callsTableViewer.setSorter(sorterCalls);
					
					// Set the content and label providers
					callsTableViewer.setContentProvider(new CallsContentProvider());
					callsTableViewer.setLabelProvider(new CallsLabelProvider());
					
					{
						tableCallsColumn1 = new TableColumn(callsTable,
								SWT.NONE);
						tableCallsColumn1.setText("Calls");
						tableCallsColumn1.setWidth(150);
						tableCallsColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) callsTableViewer
										.getSorter()).doSort(0);
								callsTableViewer.refresh();
							}
						});
					}
	

					callsTable.setHeaderVisible(true);


				}
			}
		}


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
		methodsTableViewer.setInput(model);
	}

	public IResultsModel getModel() {
		return model;
	}



	private void selectionItem(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				Fan_in_Result metodo = (Fan_in_Result) ((IStructuredSelection) event.getSelection()).getFirstElement();
				String key = metodo.getMetodo().getId();
				List<Call_Counted> llamadas = ((FanInModel) model).getCalls().get(key);
				callsTableViewer.setInput(llamadas);

			}

		}

	}
}
