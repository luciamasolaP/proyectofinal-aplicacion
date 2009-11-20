package aspectminingtool.views.Sinergia;

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
import aspectminingtool.JessIntegrationModel.Sinergia.Seed;
import aspectminingtool.JessIntegrationModel.Sinergia.SinergiaResultsModel;
import aspectminingtool.views.AbstractView;
import aspectminingtool.views.OpenClassListener;
import aspectminingtool.views.SearchInTable;
import aspectminingtool.views.actions.OpenClassAction;
import aspectminingtool.views.actions.SelectSinergiaResultAsSeedAction;



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
public class ViewPartSinergia extends AbstractView{
  
	public static final String ID_VIEW = "aspectminingtool.views.Sinergia.ViewPartSinergia"; //$NON-NLS-1$
    public static final String NAME = "Sinergia Analysis";

	private Composite composite1;
	private Composite composite2;
	private Composite composite3;
	private SashForm sashForm;
	
	private TableViewer tableViewerLeft;
	private Table tableLeft;
	private Table tableRight;
	private TableViewer tableViewerRight;

	private Button buttonSearch;
	private CLabel labelSearch;
	private Text textSearch;
	
	private SearchInTable searchInTable = new SearchInTable();
	
	private Action selectAllTableLeft;
	private OpenClassAction openActionTableLeft;
	private SelectSinergiaResultAsSeedAction selectAsSeedAction;
	
	private String COLUMN1_SEED = "Seed";
	private String COLUMN2_TRUST = "Trust Value";
	
	private String COLUMN1_FANIN_SEED = "Fan-In Seed";
	private String COLUMN1_UNIQUE_SEED = "Unique Methods Seed";
	private String COLUMN1_EXECRELATIONS_SEED = "Execution Relations Seed";
	

    public ViewPartSinergia() {
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
		
		createPopUpsMenus();
    }

	private void createLeftTable() {
		composite1 = new Composite(sashForm, SWT.NULL);
		GridLayout composite1Layout = new GridLayout();
		composite1Layout.makeColumnsEqualWidth = true;
		composite1Layout.marginHeight = 0;
		composite1Layout.marginWidth = 0;
		composite1Layout.verticalSpacing = 0;
		composite1Layout.marginHeight = 0;
		composite1.setLayout(composite1Layout);

		tableLeft = new Table(composite1, SWT.BORDER | SWT.MULTI);

		createLeftTableViewer();
		
		// Set up the table, each column has a listener for the click
		// that calls
		// the sorter and refreshes the tree.
		// Column 1
		TableColumn tc1 = new TableColumn(tableLeft, SWT.LEFT);
		tc1.setText(COLUMN1_SEED);
		tc1.setWidth(200);
		tc1
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterSinergiaSeeds) tableViewerLeft
								.getSorter()).doSort(0);
						tableViewerLeft.refresh();
					}
				});

		// Column 2
		TableColumn tc2 = new TableColumn(tableLeft, SWT.LEFT);
		tc2.setText(COLUMN2_TRUST);
		tc2.setWidth(200);
		tc2
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterSinergiaSeeds) tableViewerLeft
								.getSorter()).doSort(1);
						tableViewerLeft.refresh();
					}
				});
				
		// Turn on the header and the lines
		tableLeft.setHeaderVisible(true);
		GridData tableLeftLData = new GridData();
		tableLeftLData.horizontalAlignment = GridData.FILL;
		tableLeftLData.verticalAlignment = GridData.FILL;
		tableLeftLData.grabExcessVerticalSpace = true;
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

	}

	private void createLeftTableViewer() {
		tableViewerLeft = new TableViewer(tableLeft);

		// Set the sorter
		ViewerSorter sorterCalls = new SorterSinergiaSeeds();
		tableViewerLeft.setSorter(sorterCalls);

		// Set the content and label providers
		tableViewerLeft
				.setContentProvider(new SinergiaSeedsContentProvider());
		tableViewerLeft.setLabelProvider(new SinergiaSeedsLabelProvider());
		

		tableViewerLeft
		.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionItem(event);
				
			}

		});

		tableViewerLeft.addDoubleClickListener(new OpenClassListener(this));
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
					TableColumn tableColumn1 = new TableColumn(tableRight,
							SWT.NONE);
					tableColumn1.setText(COLUMN1_FANIN_SEED);
					tableColumn1.setWidth(150);
					tableColumn1
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterSinergiaAlgorithmsSeeds) tableViewerRight
									.getSorter()).doSort(0);
							tableViewerRight.refresh();
						}
					});
					
					TableColumn tableColumn2 = new TableColumn(tableRight,
							SWT.NONE);
					tableColumn2.setText(COLUMN1_UNIQUE_SEED);
					tableColumn2.setWidth(150);
					tableColumn2
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterSinergiaAlgorithmsSeeds) tableViewerRight
									.getSorter()).doSort(0);
							tableViewerRight.refresh();
						}
					});
					
					TableColumn tableColumn3 = new TableColumn(tableRight,
							SWT.NONE);
					tableColumn3.setText(COLUMN1_EXECRELATIONS_SEED);
					tableColumn3.setWidth(150);
					tableColumn3
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterSinergiaAlgorithmsSeeds) tableViewerRight
									.getSorter()).doSort(0);
							tableViewerRight.refresh();
						}
					});
					
				}
				tableRight.setHeaderVisible(true);
				tableRight.setLinesVisible(true);		
		}
	 }

	private void createTableViewerRight() {
		
		tableViewerRight = new TableViewer(tableRight);
		
		// Set the content and label providers
		tableViewerRight
				.setLabelProvider(new SinergiaAlgorithmSeedsLabelProvider());
		tableViewerRight.setContentProvider(new SinergiaAlgorithmsSeedsContentProvider());
		
		tableViewerRight.setSorter(new SorterSinergiaAlgorithmsSeeds());
		
		
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
		super.setPartName("Sinergia Results - " + model.getId());
		tableViewerLeft.setInput(model);
		openActionTableLeft = new OpenClassAction(model,tableViewerLeft);
		selectAsSeedAction = new SelectSinergiaResultAsSeedAction(model,tableViewerLeft,NAME);
	}
	

	private void selectionItem(SelectionChangedEvent event) {
		
		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				Seed seed = (Seed) ((IStructuredSelection) event.getSelection()).getFirstElement();
				tableViewerRight.setInput(((SinergiaResultsModel)model).getAlgorithmsResults(seed));
			}

		}
		
	}
	
	private void createPopUpsMenus(){
		createActions();
		createPopUps();
		hookGlobalActions();
	}

	/**
	 * Create the actions.
	 */
	public void createActions() {
	
		selectAllTableLeft = new Action("Select All") {
			public void run() {
				selectAll(tableViewerLeft);
			}
		};
		// Add selection listener.
		tableViewerLeft.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)tableViewerLeft.getSelection();
				openActionTableLeft.setEnabled(sel.size() > 0);
				selectAllTableLeft.setEnabled(sel.size() > 0);
				selectAsSeedAction.setEnabled(sel.size() > 0);
			}
		});		
	}
	
	protected void selectAll(TableViewer tableViewer) {
		tableViewer.getTable().selectAll();
		
	}
	
	private void createPopUps() {

		MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
                public void menuAboutToShow(IMenuManager mgr) {
                	fillContextMenutableViewerLeft(mgr);
                }

				private void fillContextMenutableViewerLeft(IMenuManager mgr) {
					mgr.add(openActionTableLeft);
					mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
					mgr.add(selectAsSeedAction);
					mgr.add(new Separator());
					mgr.add(selectAllTableLeft);
					
				}

        });
        // Create menu for methodsTableViewer
        Menu menu = menuMgr.createContextMenu(tableViewerLeft.getControl());
        tableViewerLeft.getControl().setMenu(menu);
        
        // Register menu for extension.
        getSite().registerContextMenu(menuMgr, tableViewerLeft);
		
	}

	private void hookGlobalActions() {
		IActionBars bars = getViewSite().getActionBars();
		bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllTableLeft);
		
	}
	
    
}
