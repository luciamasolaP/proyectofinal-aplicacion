package aspectminingtool.views.RedirectorFinder;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ViewPartRedirectorFinder extends ViewPart {
    public static final String ID_VIEW =
        "aspectminingtool.views.RedirectorFinder.ViewPartRedirectorFinder"; //$NON-NLS-1$
    
    private IResultsModel model;
    

    private SashForm sashForm;

    private Composite composite1;
	private TableViewer tableViewerLeft;
	private Table tableLeft;
    
	private Composite composite2;
	private Table tableRight;
	private TableViewer tableViewerRight;

    
    /**
     * 
     */
    public ViewPartRedirectorFinder() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
    	FillLayout parentLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
		parent.setLayout(parentLayout);
		
		sashForm = new SashForm(parent, SWT.NONE);

		createLeftTable();
		createRightTable();

    }

	private void createLeftTable() {
		composite1 = new Composite(sashForm, SWT.NULL);
		FillLayout composite1Layout = new FillLayout(
				org.eclipse.swt.SWT.HORIZONTAL);
		composite1.setLayout(composite1Layout);

		tableLeft = new Table(composite1, SWT.BORDER | SWT.MULTI);
		
		createLeftTableViewer();
		
		// Set up the table, each column has a listener for the click
		// that calls
		// the sorter and refreshes the tree.
		// Column 1
		TableColumn tc1 = new TableColumn(tableLeft, SWT.LEFT);
		tc1.setText("Clase");
		tc1.setWidth(200);
//		tc1
//				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
//					public void widgetSelected(SelectionEvent event) {
//						((SorterFanInViewFanIn) tableViewerLeft
//								.getSorter()).doSort(0);
//						tableViewerLeft.refresh();
//					}
//				});

		// Column 2
		TableColumn tc2 = new TableColumn(tableLeft, SWT.LEFT);
		tc2.setText("Clase Redireccionada");
		tc2.setWidth(200);
//		tc2
//				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
//					public void widgetSelected(SelectionEvent event) {
//						((SorterFanInViewFanIn) tableViewerLeft
//								.getSorter()).doSort(1);
//						tableViewerLeft.refresh();
//					}
//				});
		
		TableColumn tc3 = new TableColumn(tableLeft, SWT.LEFT);
		tc3.setText("Redirecciones");
		tc3.setWidth(91);
		
		TableColumn tc4 = new TableColumn(tableLeft, SWT.LEFT);
		tc4.setText("%");
		tc4.setWidth(50);

		// Turn on the header and the lines
		tableLeft.setHeaderVisible(true);
		tableLeft.setLinesVisible(true);

		
		
	}

	private void createLeftTableViewer() {
		tableViewerLeft = new TableViewer(tableLeft);

		// Set the sorter
//		ViewerSorter sorter = new SorterFanInViewFanIn();
//		tableViewerLeft.setSorter(sorter);

		// Set the content and label providers
		tableViewerLeft
				.setContentProvider(new RedirectorFinderContentProvider());
		tableViewerLeft.setLabelProvider(new RedirectorFinderLabelProvider());
		
		
//		tableViewerLeft
//		.addSelectionChangedListener(new ISelectionChangedListener() {
//			public void selectionChanged(
//					SelectionChangedEvent event) {
//				selectionItem(event);
//
//			}
//
//		});
//
//		tableViewerLeft.addDoubleClickListener(new IDoubleClickListener(){
//		
//			@Override
//			public void doubleClick(DoubleClickEvent event) {
//				if (!event.getSelection().isEmpty()) {
//					
//					if (event.getSelection() instanceof IStructuredSelection) {
//						
//						Fan_in_Result fanInResult = (Fan_in_Result) ((IStructuredSelection) event.getSelection()).getFirstElement();
//						openResource(fanInResult.getMetodo().getClass_id());
//					}
//				}
//				
//			}
//			
//		});
	}
	
	 private void createRightTable() {
		
		 composite2 = new Composite(sashForm, SWT.NONE);
			FillLayout composite2Layout = new FillLayout(
					org.eclipse.swt.SWT.HORIZONTAL);
			composite2.setLayout(composite2Layout);
			composite2.setBounds(0, 0, 77, 81);
			{
				tableRight = new Table(composite2, SWT.LEFT | SWT.MULTI);
				
				
				createTableViewerRight();
				
				{
					TableColumn tableColumn = new TableColumn(tableRight,
							SWT.NONE);
					tableColumn.setText("Llamadas");
					tableColumn.setWidth(300);
//					tableColumn
//					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
//						public void widgetSelected(SelectionEvent event) {
//							((SorterFanInViewCalls) tableViewerRight
//									.getSorter()).doSort(0);
//							tableViewerRight.refresh();
//						}
//					});
				}


				tableRight.setHeaderVisible(true);
			
		}
	 }

	private void createTableViewerRight() {
		
		tableViewerRight = new TableViewer(tableRight);
		
//		// Set the sorter
//		ViewerSorter sorterCalls = new SorterFanInViewCalls();
//		tableViewerRight.setSorter(sorterCalls);
//		
//		// Set the content and label providers
//		tableViewerRight.setContentProvider(new CallsContentProviderFanIn());
//		tableViewerRight.setLabelProvider(new CallsLabelProviderFanIn());
		
//		callsTableViewer.addDoubleClickListener(new IDoubleClickListener(){
		//
//							@Override
//							public void doubleClick(DoubleClickEvent event) {
//								if (!event.getSelection().isEmpty()) {
//									
//									if (event.getSelection() instanceof IStructuredSelection) {
//										
//										Call_Counted callCounted = (Call_Counted) ((IStructuredSelection) event.getSelection()).getFirstElement();
//										String name = callCounted.getCaller_id();
//										int index = name.indexOf("//");
//										name = name.substring(0, index);
//										openResource(name);
//									}
//								}
//								
//							}
//							
//						});
		
	}

	/* (non-Javadoc)
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
		super.setPartName("Redirector Finder Results - " + model.getId());
		tableViewerLeft.setInput(model);
	}
	
	public IResultsModel getModel() {
		return model;
	}
    
}
