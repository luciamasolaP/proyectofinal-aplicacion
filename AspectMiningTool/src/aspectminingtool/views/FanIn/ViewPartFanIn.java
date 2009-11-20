package aspectminingtool.views.FanIn;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.JessIntegrationModel.MetricMethodResult;
import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.AbstractFilterView;
import aspectminingtool.views.OpenClassListener;
import aspectminingtool.views.SearchInTable;
import aspectminingtool.views.FanIn.Filters.FilterFanInUmbral;
import aspectminingtool.views.FanIn.Filters.FilterGettterSetter;
import aspectminingtool.views.actions.OpenClassAction;
import aspectminingtool.views.actions.SelectMethodAsSeedAction;

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
public class ViewPartFanIn extends AbstractFilterView{
	public static final String ID_VIEW = "aspectminingtool.views.ViewPartFanIn"; //$NON-NLS-1$
	public static final String NAME = "Fan-In";
	private Composite composite1;
	private Composite composite2;
	private Composite composite3;
	private SashForm sashForm1;
	
	private TableViewer tableViewerLeft;
	private Table tableLeft;
//	private ViewerFilter filterUmbral;
//	private ViewerFilter filterGetSetter;
	private Table tableRight;
	private TableViewer tableViewerRight;
	
	private Button buttonSearch;
	private CLabel labelSearch;
	private Text textSearch;
	
	private SearchInTable searchInTable = new SearchInTable();

	
	//actions
	private Action selectAllActionMethodsTable, selectAllActionCallsTable;
	private OpenClassAction openActionTableLeft;
	private OpenClassAction openActionTableRight;
	private SelectMethodAsSeedAction selectAsSeedOperation;

	/**
     * 
     */
	public ViewPartFanIn() {
		super();
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
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.makeColumnsEqualWidth = true;
				composite1Layout.marginHeight = 0;
				composite1Layout.marginWidth = 0;
				composite1Layout.verticalSpacing = 0;
				composite1.setLayout(composite1Layout);
				composite1.setBounds(-483, -25, 461, 81);
				
				createMethodsTable();

			}
			{
				composite2 = new Composite(sashForm1, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite2.setLayout(composite2Layout);
				composite2.setBounds(0, 0, 77, 81);
				
				createCallsTable();
			
			}
		}
		
		createActions();
		createContextMenu();
		hookGlobalActions();


	}

	private void createMethodsTable(){
		
		tableLeft = new Table(composite1, SWT.BORDER | SWT.MULTI);
		
		// Set up the table, each column has a listener for the click
		// that calls
		// the sorter and refreshes the tree.
		// Column 1
		final TableColumn tc1 = new TableColumn(tableLeft, SWT.LEFT);
		tc1.setText("Method");
		tc1.setWidth(398);
		tc1
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFanInViewFanIn) tableViewerLeft
								.getSorter()).doSort(0);
						tableViewerLeft.refresh();
					}
				});

		// Column 2
		TableColumn tc2 = new TableColumn(tableLeft, SWT.LEFT);
		tc2.setText("Fan in");
		tc2.setWidth(50);
		tc2
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFanInViewFanIn) tableViewerLeft
								.getSorter()).doSort(1);
						tableViewerLeft.refresh();
					}
				});

		// Turn on the header and the lines
		tableLeft.setHeaderVisible(true);
		GridData tableLeftLData = new GridData();
		tableLeftLData.verticalAlignment = GridData.FILL;
		tableLeftLData.grabExcessVerticalSpace = true;
		tableLeftLData.horizontalAlignment = GridData.FILL;
		tableLeftLData.grabExcessHorizontalSpace = true;
		tableLeft.setLayoutData(tableLeftLData);
		tableLeft.setLinesVisible(true);
		{
			GridData composite3LData = new GridData();
			composite3LData.verticalAlignment = GridData.FILL;
			composite3LData.horizontalAlignment = GridData.FILL;
			composite3 = new Composite(composite1, SWT.NONE);
			GridLayout composite3Layout = new GridLayout();
			composite3Layout.numColumns = 3;
			composite3.setLayout(composite3Layout);
			composite3.setLayoutData(composite3LData);
			{
				labelSearch = new CLabel(composite3, SWT.NONE);
				GridData labelSearchData = new GridData();
				labelSearchData.horizontalIndent = -5;
				labelSearchData.widthHint = 47;
				labelSearchData.heightHint = 21;
				labelSearch.setLayoutData(labelSearchData);
				labelSearch.setText("Search:");
							
			}
			{
				textSearch = new Text(composite3, SWT.BORDER);
				GridData textSearchData = new GridData();
				textSearchData.widthHint = 179;
				textSearchData.heightHint = 15;
				textSearch.setLayoutData(textSearchData);
				textSearch.setText("");
			}
			{
				buttonSearch = new Button(composite3, SWT.PUSH | SWT.CENTER);
				GridData buttonSearchLData = new GridData();
				buttonSearch.setLayoutData(buttonSearchLData);
				buttonSearch.setText("Search");
				
				buttonSearch.addListener (SWT.Selection, new Listener () {
					public void handleEvent (Event event) {
						searchInTable.locateItemInTable(textSearch.getText().toLowerCase(),tableLeft);
						
					}
				});
				
			}
		}


		createMethodsTableViewer();
		
	}
	
	private void createMethodsTableViewer() {
		
		tableViewerLeft = new TableViewer(tableLeft);

		// Set the sorter
		ViewerSorter sorter = new SorterFanInViewFanIn();
		tableViewerLeft.setSorter(sorter);

		// Set the content and label providers
		tableViewerLeft
				.setContentProvider(new FanInContentProvider());
		tableViewerLeft.setLabelProvider(new FanInLabelProvider());

		tableViewerLeft
		.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(
					SelectionChangedEvent event) {
				selectionItem(event);

			}

		});

		tableViewerLeft.addDoubleClickListener(new OpenClassListener(this));
		filterUmbral = new FilterFanInUmbral(new Integer(1));
		tableViewerLeft.addFilter(filterUmbral);
		
		filterGetSetter = new FilterGettterSetter(true);
		tableViewerLeft.addFilter(filterGetSetter);
		
	}

	private void createCallsTable(){
		
		tableRight = new Table(composite2, SWT.LEFT | SWT.MULTI);
			
		{
			TableColumn tableCallsColumn1 = new TableColumn(tableRight,
					SWT.NONE);
			tableCallsColumn1.setText("Calls");
			tableCallsColumn1.setWidth(300);
			tableCallsColumn1
			.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					((SorterFanInViewCalls) tableViewerRight
							.getSorter()).doSort(0);
					tableViewerRight.refresh();
				}
			});
		}


		tableRight.setHeaderVisible(true);
			
		createCallsTableViewer();
		
	}
	
	private void createCallsTableViewer(){
		
		tableViewerRight = new TableViewer(tableRight);
		
		// Set the sorter
		ViewerSorter sorterCalls = new SorterFanInViewCalls();
		tableViewerRight.setSorter(sorterCalls);
		
		// Set the content and label providers
		tableViewerRight.setContentProvider(new CallsContentProviderFanIn());
		tableViewerRight.setLabelProvider(new CallsLabelProviderFanIn());
		
		tableViewerRight.addDoubleClickListener(new OpenClassListener(this));
		
		
	}
	
	private void hookGlobalActions() {
		IActionBars bars = getViewSite().getActionBars();
		bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllActionMethodsTable);
	
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
		tableViewerLeft.setInput(model);
		openActionTableLeft = new OpenClassAction(model,tableViewerLeft);
		selectAsSeedOperation = new SelectMethodAsSeedAction(model,tableViewerLeft,NAME);
	}


	private void selectionItem(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MetricMethodResult metodo = (MetricMethodResult) ((IStructuredSelection) event.getSelection()).getFirstElement();
				String key = metodo.getMetodo().getId();
				List<Call_Counted> llamadas = ((FanInModel) model).getCalls().get(key);
				tableViewerRight.setInput(llamadas);
				openActionTableRight = new OpenClassAction(model,tableViewerRight);

			}
		}
	}
	
	private void createContextMenu() {
        
		MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
                public void menuAboutToShow(IMenuManager mgr) {
                	fillContextMenuMethodsTableViewer(mgr);
                }

        });
        // Create menu for methodsTableViewer
        Menu menu = menuMgr.createContextMenu(tableViewerLeft.getControl());
        tableViewerLeft.getControl().setMenu(menu);
        
        // Register menu for extension.
        getSite().registerContextMenu(menuMgr, tableViewerLeft);
		

        MenuManager menuMgr1 = new MenuManager();
        menuMgr1.setRemoveAllWhenShown(true);
        menuMgr1.addMenuListener(new IMenuListener() {
                public void menuAboutToShow(IMenuManager mgr) {
                	fillContextMenuCallsTableViewer(mgr);
                }

        });
        // Create menu for methodsTableViewer
        Menu menu1 = menuMgr1.createContextMenu(tableViewerRight.getControl());
        tableViewerRight.getControl().setMenu(menu1);
        
        // Register menu for extension.
        getSite().registerContextMenu(menuMgr1, tableViewerRight);

         

 }

	protected void fillContextMenuCallsTableViewer(IMenuManager mgr) {
		mgr.add(openActionTableRight);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(new Separator());
		mgr.add(selectAllActionCallsTable);
		
	}

	protected void fillContextMenuMethodsTableViewer(IMenuManager mgr) {
		mgr.add(openActionTableLeft);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(selectAsSeedOperation);
		mgr.add(new Separator());
		mgr.add(selectAllActionMethodsTable);
		
	}
	
	/**
	 * Create the actions.
	 */
	public void createActions() {
		
		selectAllActionMethodsTable = new Action("Select All") {
			public void run() {
				selectAll(tableViewerLeft);
			}
		};
			
		selectAllActionCallsTable = new Action("Select All") {
			public void run() {
				selectAll(tableViewerRight);
			}
		};
		
		// Add selection listener.
		tableViewerLeft.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)tableViewerLeft.getSelection();
				openActionTableLeft.setEnabled(sel.size() > 0);
				selectAllActionMethodsTable.setEnabled(sel.size() > 0);
				selectAsSeedOperation.setEnabled(sel.size() > 0);
			}
		});
		
		tableViewerRight.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)tableViewerRight.getSelection();
				selectAllActionCallsTable.setEnabled(sel.size() > 0);
				openActionTableRight.setEnabled(sel.size() > 0);
			}
		});
		
		
	}
	
	protected void selectAll(TableViewer tableViewer) {
		tableViewer.getTable().selectAll();
		
	}


	public void setUmbralFilter(String umbral){
		((FilterFanInUmbral)filterUmbral).setUmbralText(umbral);
		tableViewerLeft.refresh();
	}
	
	public void setGetterSetterFilter(boolean filter){
		((FilterGettterSetter)filterGetSetter).setFilterOut(filter);
		tableViewerLeft.refresh();
	}
	
}
