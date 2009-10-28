package aspectminingtool.views.FlowGraph;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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
import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.MetricMethodResult;
import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;
import aspectminingtool.views.AbstractView;
import aspectminingtool.views.OpenClassListener;
import aspectminingtool.views.SearchInTable;
import aspectminingtool.views.ViewAlgorithmInterface;
import aspectminingtool.views.FanIn.SorterFanInViewCalls;
import aspectminingtool.views.actions.OpenClassAction;
import aspectminingtool.views.actions.SelectMethodAsSeedAction;


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
public class ViewPartFlowGraph extends AbstractView implements ViewAlgorithmInterface{
    public static final String ID_VIEW =
        "aspectminingtool.views.FlowGraph.ViewPartFlowGraph"; //$NON-NLS-1$
		public static final String NAME = "Flow Graph";

        private CTabItem cTabItemInsideFirstExecution;
        private CTabItem cTabItemInsideLastExecution;
        private CTabItem cTabItemOutsideAfterExecution;
        private CTabItem cTabItemOutsideBeforeExecution;
        private CTabFolder cTabFolderFlowGraph;
        
      	private SashForm sashForm1;
        private SashForm sashForm2;
        private SashForm sashForm3;
        private SashForm sashForm4;        
    	
    	private Table tableLeft;
    	private TableViewer tableViewerLeftTab1;
    	private Table tableRight;
    	private TableViewer tableViewerRightTab1;
    	private SearchInTable searchInTableLeft = new SearchInTable();
    	
//    	private Action selectAllActionMethodsTableTab1, selectAllActionCallsTableTab1;
//    	private OpenClassAction openActionTableLeftTab1;
//    	private OpenClassAction openActionTableRightTab1;
//    	private SelectMethodAsSeedAction selectAsSeedOperationTab1;
    	
    	  	
    	private Button buttonSearch;
    	private CLabel labelSearch;
    	private Text textSearch;

    	private Table tableLeftTab2;
		private TableViewer tableViewerLeftTab2;
		private SearchInTable searchInTableLeftTab2 = new SearchInTable();
		private Table tableRightTab2;
		private TableViewer tableViewerRightTab2;
		private Button buttonSearch2;
    	private CLabel labelSearch2;
    	private Text textSearch2;
		
		private Table tableLeftTab3;
		private TableViewer tableViewerLeftTab3;
		private SearchInTable searchInTableLeftTab3 = new SearchInTable();
		private Table tableRightTab3;
		private TableViewer tableViewerRightTab3;
		private Button buttonSearch3;
    	private CLabel labelSearch3;
    	private Text textSearch3;
		
		private Table tableLeftTab4;
		private TableViewer tableViewerLeftTab4;
		private SearchInTable searchInTableLeftTab4 = new SearchInTable();
		private Table tableRightTab4;
		private TableViewer tableViewerRightTab4;
		private Button buttonSearch4;
    	private CLabel labelSearch4;
    	private Text textSearch4;

//    	private Action selectAllActionTableLTab1, selectAllActionTableRTab1, selectAllActionTableLTab2, selectAllActionTableRTab2, 
//    					selectAllActionTableLTab3, selectAllActionTableRTab3, selectAllActionTableLTab4, selectAllActionTableRTab4;
//    	private OpenClassAction openActionTableLeftTab1,openActionTableLeftTab2,openActionTableLeftTab3,openActionTableLeftTab4;
//    	private OpenClassAction openActionTableRightTab1,openActionTableRightTab2,openActionTableRightTab3,openActionTableRightTab4;
//    	private SelectMethodAsSeedAction selectAsSeedOperationTab1,selectAsSeedOperationTab2,selectAsSeedOperationTab3,selectAsSeedOperationTab4;
    	
//    	TableViewer[] tablesVLeft =  {tableViewerLeftTab1,tableViewerLeftTab2,tableViewerLeftTab3,tableViewerLeftTab4};
//    	TableViewer[] tablesVRight = {tableViewerRightTab1,tableViewerRightTab2,tableViewerRightTab3,tableViewerRightTab4};
//    	Action[] selectAllActionsRight = {selectAllActionTableRTab1,selectAllActionTableRTab2,selectAllActionTableRTab3,selectAllActionTableRTab4};
//    	Action[] selectAllActionsLeft = {selectAllActionTableLTab1,selectAllActionTableLTab2,selectAllActionTableLTab3,selectAllActionTableLTab4};
//    	OpenClassAction[] openClassActionTableL = {openActionTableLeftTab1,openActionTableLeftTab2,openActionTableLeftTab3,openActionTableLeftTab4};
//    	OpenClassAction[] openClassActionTableR = {openActionTableRightTab1,openActionTableRightTab2,openActionTableRightTab3,openActionTableRightTab4};
//    	SelectMethodAsSeedAction[] selectMethodAsSeedAction = {selectAsSeedOperationTab1,selectAsSeedOperationTab2,selectAsSeedOperationTab3,selectAsSeedOperationTab4};
    	
    	TableViewer[] tablesVLeft = new TableViewer[4];
    	TableViewer[] tablesVRight = new TableViewer[4];
    	Action[] selectAllActionsRight = new Action[4];
    	Action[] selectAllActionsLeft = new Action[4];
    	OpenClassAction[] openClassActionTableL = new OpenClassAction[4];
    	OpenClassAction[] openClassActionTableR = new OpenClassAction[4];
    	SelectMethodAsSeedAction[] selectMethodAsSeedAction = new SelectMethodAsSeedAction[4];
    	
//    	private IResultsModel model;
    	
    	private Composite composite1;
    	private Composite composite2;
    	private Composite composite3;
    	private Composite composite4;
    	private Composite composite5;
    	private Composite composite6;
    	private Composite composite7;
    	private Composite composite8;
    	
    	private Composite composite9;
    	private Composite composite10;
    	private Composite composite11;
    	private Composite composite12;

    
    /**
     * 
     */
    public ViewPartFlowGraph() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void setModel(IResultsModel model) {
		this.model = model;
		super.setPartName("FlowControl Results - " + model.getId());
		//aca les seteas el modelo a las tablas, los content y label saben leerlos y llenar las tablas.
		tablesVLeft[0].setInput(model);
		
		tablesVLeft[1].setInput(model);
		tablesVLeft[2].setInput(model);
		tablesVLeft[3].setInput(model);
		
		for (int i = 0; i < tablesVLeft.length ; i ++){
		
			openClassActionTableL[i] = new OpenClassAction(model,tablesVLeft[i]);
			selectMethodAsSeedAction[i] = new SelectMethodAsSeedAction(model,tablesVLeft[i],NAME);
			
		}
		
		createActions();
		createContextMenu();
		hookGlobalActions();
		
	}
	
	private void selectionItemTab1(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MetricMethodResult relation = (MetricMethodResult) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getOutsideBeforeExecutionMethods(relation.getMetodo());
				tablesVRight[0].setInput(relatedMethos);
				openClassActionTableR[0] = new OpenClassAction(model,tablesVRight[0]);
			}
		}
	}
	
	private void selectionItemTab2(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MetricMethodResult relation = (MetricMethodResult) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getOutsideAfterExecutionMethods(relation.getMetodo());
				tablesVRight[1].setInput(relatedMethos);
				openClassActionTableR[1] = new OpenClassAction(model,tablesVRight[1]);
			}
		}
	}
	
	private void selectionItemTab3(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MetricMethodResult relation = (MetricMethodResult) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getInsideFirstExecutionMethods(relation.getMetodo());
				tablesVRight[2].setInput(relatedMethos);
				openClassActionTableR[2] = new OpenClassAction(model,tablesVRight[2]);
			}
		}
	}
	
	private void selectionItemTab4(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MetricMethodResult relation = (MetricMethodResult) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getInsideLastExecutionMethods(relation.getMetodo());
				tablesVRight[3].setInput(relatedMethos);
				openClassActionTableR[3] = new OpenClassAction(model,tablesVRight[3]);
			}
		}
	}

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        composite1 = new Composite(parent, SWT.NULL);
        composite1.setLayout(new GridLayout(4, false));
        {
        	cTabFolderFlowGraph = new CTabFolder(composite1, SWT.NONE);
        	GridData cTabFolderFlowGraphLData = new GridData();
        	cTabFolderFlowGraphLData.horizontalAlignment = GridData.FILL;
        	cTabFolderFlowGraphLData.verticalAlignment = GridData.FILL;
        	cTabFolderFlowGraphLData.grabExcessVerticalSpace = true;
        	cTabFolderFlowGraphLData.grabExcessHorizontalSpace = true;
        	cTabFolderFlowGraph.setLayoutData(cTabFolderFlowGraphLData);
        	{
        		cTabItemOutsideBeforeExecution = new CTabItem(cTabFolderFlowGraph, SWT.NONE);
        		cTabItemOutsideBeforeExecution.setText("Outside Before Execution");
        		createTab1();

        	}
        	{
        		cTabItemOutsideAfterExecution = new CTabItem(cTabFolderFlowGraph, SWT.NONE);
        		cTabItemOutsideAfterExecution.setText("Outside After Execution");
        		createTab2();
        	}
        	{
        		cTabItemInsideFirstExecution = new CTabItem(cTabFolderFlowGraph, SWT.NONE);
        		cTabItemInsideFirstExecution.setText("Inside First Execution");
        		createTab3();
        	}
        	{
        		cTabItemInsideLastExecution = new CTabItem(cTabFolderFlowGraph, SWT.NONE);
        		cTabItemInsideLastExecution.setText("Inside Last Execution");
        		createTab4();
        	}
        	cTabFolderFlowGraph.setSelection(0);
        }
        
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
    
    public void createTab1(){
    	
		{
			sashForm1 = new SashForm(cTabFolderFlowGraph, SWT.NONE);
			cTabItemOutsideBeforeExecution.setControl(sashForm1);
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
				
				tableLeft = new Table(composite1, SWT.BORDER | SWT.MULTI);
				tablesVLeft[0] = new TableViewer(tableLeft);
				
				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab1Left();
				tablesVLeft[0].setSorter(sorter);

				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tablesVLeft[0]
						.setContentProvider(new FlowGraphContentProviderOB());
				tablesVLeft[0].setLabelProvider(new FlowGraphLabelProviderOB());

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
						((SorterFlowGraphTab1Left) tablesVLeft[0]
								.getSorter()).doSort(0);
						tablesVLeft[0].refresh();
					}
				});


				// Column 2
				TableColumn tc2 = new TableColumn(tableLeft, SWT.LEFT);
				tc2.setText("Value");
				tc2.setWidth(50);
				tc2
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab1Left) tablesVLeft[0]
								.getSorter()).doSort(1);
						tablesVLeft[0].refresh();
					}
				});

				
				tableLeft.setHeaderVisible(true);
				GridData tableLeftLData = new GridData();
				tableLeftLData.verticalAlignment = GridData.FILL;
				tableLeftLData.grabExcessVerticalSpace = true;
				tableLeftLData.horizontalAlignment = GridData.FILL;
				tableLeftLData.grabExcessHorizontalSpace = true;
				tableLeft.setLayoutData(tableLeftLData);
				tableLeft.setLinesVisible(true);

				
				tablesVLeft[0]
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab1(event);

					}

				});
				
				tablesVLeft[0].addDoubleClickListener(new OpenClassListener(this));

				
				 		{
			GridData composite9LData = new GridData();
			composite9LData.verticalAlignment = GridData.FILL;
			composite9LData.horizontalAlignment = GridData.FILL;
			composite9 = new Composite(composite1, SWT.NONE);
			GridLayout composite9Layout = new GridLayout();
			composite9Layout.numColumns = 3;
			composite9.setLayout(composite9Layout);
			composite9.setLayoutData(composite9LData);
			
			{
				labelSearch = new CLabel(composite9, SWT.NONE);
				GridData labelSearchData = new GridData();
				labelSearchData.horizontalIndent = -5;
				labelSearchData.widthHint = 47;
				labelSearchData.heightHint = 21;
				labelSearch.setLayoutData(labelSearchData);
				labelSearch.setText("Search:");
							
			}
			{
				textSearch = new Text(composite9, SWT.BORDER);
				GridData textSearchData = new GridData();
				textSearchData.widthHint = 179;
				textSearchData.heightHint = 15;
				textSearch.setLayoutData(textSearchData);
				textSearch.setText("");
			}
			{
				buttonSearch = new Button(composite9, SWT.PUSH | SWT.CENTER);
				GridData buttonSearchLData = new GridData();
				buttonSearch.setLayoutData(buttonSearchLData);
				buttonSearch.setText("Search");
				
				buttonSearch.addListener (SWT.Selection, new Listener () {
					public void handleEvent (Event event) {
						searchInTableLeft.locateItemInTable(textSearch.getText().toLowerCase(),tableLeft);
						
					}
				});
				
			}
		}
				

			}
			{
				composite2 = new Composite(sashForm1, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite2.setLayout(composite2Layout);
				composite2.setBounds(0, 0, 77, 81);
				{
					tableRight = new Table(composite2, SWT.LEFT | SWT.MULTI);
					tablesVRight[0] = new TableViewer(tableRight);
					
					// Set the sorter
//					ViewerSorter sorterCalls = new SorterFanInViewCalls();
//					tableViewerRight.setSorter(sorterCalls);
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tablesVRight[0].setContentProvider(new FlowGraphContentProviderOBCalls());
					tablesVRight[0].setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightColumn1 = new TableColumn(tableRight,
								SWT.NONE);
						tableRightColumn1.setText("Calls");
						tableRightColumn1.setWidth(300);
						tableRightColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) tableViewerRightTab1
										.getSorter()).doSort(0);
								tablesVRight[0].refresh();
							}
						});
					}
	

					tableRight.setHeaderVisible(true);
					



				}
			}
		}
    }
    
 public void createTab2(){
    	
		{
			sashForm2 = new SashForm(cTabFolderFlowGraph, SWT.NONE);
			cTabItemOutsideAfterExecution.setControl(sashForm2);
			sashForm2.setSize(60, 30);
			{
				
				composite3 = new Composite(sashForm2, SWT.NULL);
				GridLayout composite3Layout = new GridLayout();
				composite3Layout.makeColumnsEqualWidth = true;
				composite3Layout.marginHeight = 0;
				composite3Layout.marginWidth = 0;
				composite3Layout.verticalSpacing = 0;
				composite3.setLayout(composite3Layout);
				composite3.setBounds(-483, -25, 461, 81);
				
				tableLeftTab2 = new Table(composite3, SWT.BORDER | SWT.MULTI);
				tablesVLeft[1] = new TableViewer(tableLeftTab2);

				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab2Left();
				tablesVLeft[1].setSorter(sorter);

				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tablesVLeft[1]
						.setContentProvider(new FlowGraphContentProviderOA());
				tablesVLeft[1].setLabelProvider(new FlowGraphLabelProviderOA());

				// Set up the table, each column has a listener for the click
				// that calls
				// the sorter and refreshes the tree.
				// Column 1
				final TableColumn tc21 = new TableColumn(tableLeftTab2, SWT.LEFT);
				tc21.setText("Method");
				tc21.setWidth(398);
				tc21
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab2Left) tablesVLeft[1]
								.getSorter()).doSort(0);
						tablesVLeft[1].refresh();
					}
				});


				// Column 2
				TableColumn tc22 = new TableColumn(tableLeftTab2, SWT.LEFT);
				tc22.setText("Value");
				tc22.setWidth(50);
				tc22
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab2Left) tablesVLeft[1]
								.getSorter()).doSort(1);
						tablesVLeft[1].refresh();
					}
				});


				
				tableLeftTab2.setHeaderVisible(true);
				GridData tableLeftTab2LData = new GridData();
				tableLeftTab2LData.verticalAlignment = GridData.FILL;
				tableLeftTab2LData.grabExcessVerticalSpace = true;
				tableLeftTab2LData.horizontalAlignment = GridData.FILL;
				tableLeftTab2LData.grabExcessHorizontalSpace = true;
				tableLeftTab2.setLayoutData(tableLeftTab2LData);
				tableLeftTab2.setLinesVisible(true);
				
				tablesVLeft[1]
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab2(event);

					}

				});
				tablesVLeft[1].addDoubleClickListener(new OpenClassListener(this));

		 		{
					GridData composite10LData = new GridData();
					composite10LData.verticalAlignment = GridData.FILL;
					composite10LData.horizontalAlignment = GridData.FILL;
					composite10 = new Composite(composite3, SWT.NONE);
					GridLayout composite10Layout = new GridLayout();
					composite10Layout.numColumns = 3;
					composite10.setLayout(composite10Layout);
					composite10.setLayoutData(composite10LData);
					
					{
						labelSearch2 = new CLabel(composite10, SWT.NONE);
						GridData labelSearch2Data = new GridData();
						labelSearch2Data.horizontalIndent = -5;
						labelSearch2Data.widthHint = 47;
						labelSearch2Data.heightHint = 21;
						labelSearch2.setLayoutData(labelSearch2Data);
						labelSearch2.setText("Search:");
									
					}
					{
						textSearch2 = new Text(composite10, SWT.BORDER);
						GridData textSearch2Data = new GridData();
						textSearch2Data.widthHint = 179;
						textSearch2Data.heightHint = 15;
						textSearch2.setLayoutData(textSearch2Data);
						textSearch2.setText("");
					}
					{
						buttonSearch2 = new Button(composite10, SWT.PUSH | SWT.CENTER);
						GridData buttonSearch2LData = new GridData();
						buttonSearch2.setLayoutData(buttonSearch2LData);
						buttonSearch2.setText("Search");
						
						buttonSearch2.addListener (SWT.Selection, new Listener () {
							public void handleEvent (Event event) {
								searchInTableLeftTab2.locateItemInTable(textSearch2.getText().toLowerCase(),tableLeftTab2);
								
							}
						});
						
					}
				}

			}
			{
				composite4 = new Composite(sashForm2, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite4.setLayout(composite2Layout);
				composite4.setBounds(0, 0, 77, 81);
				{
					tableRightTab2 = new Table(composite4, SWT.LEFT | SWT.MULTI);
					tablesVRight[1] = new TableViewer(tableRightTab2);
					
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tablesVRight[1].setContentProvider(new FlowGraphContentProviderOBCalls());
					tablesVRight[1].setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightTab2Column1 = new TableColumn(tableRightTab2,
								SWT.NONE);
						tableRightTab2Column1.setText("Calls");
						tableRightTab2Column1.setWidth(300);

	

						tableRightTab2.setHeaderVisible(true);
					



				}
			}
		}
    }    
}
 
 public void createTab3(){
 	
		{
			sashForm3 = new SashForm(cTabFolderFlowGraph, SWT.NONE);
			cTabItemInsideFirstExecution.setControl(sashForm3);
			sashForm3.setSize(60, 30);
			{
			
				composite5 = new Composite(sashForm3, SWT.NULL);
				GridLayout composite5Layout = new GridLayout();
				composite5Layout.makeColumnsEqualWidth = true;
				composite5Layout.marginHeight = 0;
				composite5Layout.marginWidth = 0;
				composite5Layout.verticalSpacing = 0;
				composite5.setLayout(composite5Layout);
				composite5.setBounds(-483, -25, 461, 81);
				
				tableLeftTab3 = new Table(composite5, SWT.BORDER | SWT.MULTI);
				tablesVLeft[2] = new TableViewer(tableLeftTab3);

				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab3Left();
				tablesVLeft[2].setSorter(sorter);
				
				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tablesVLeft[2]
						.setContentProvider(new FlowGraphContentProviderIF());
				tablesVLeft[2].setLabelProvider(new FlowGraphLabelProviderIF());

				// Set up the table, each column has a listener for the click
				// that calls
				// the sorter and refreshes the tree.
				// Column 1
				final TableColumn tc31 = new TableColumn(tableLeftTab3, SWT.LEFT);
				tc31.setText("Method");
				tc31.setWidth(398);
				tc31
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab3Left) tablesVLeft[2]
								.getSorter()).doSort(0);
						tablesVLeft[2].refresh();
					}
				});


				// Column 2
				TableColumn tc32 = new TableColumn(tableLeftTab3, SWT.LEFT);
				tc32.setText("Value");
				tc32.setWidth(50);
				tc32
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab3Left) tablesVLeft[2]
								.getSorter()).doSort(1);
						tablesVLeft[2].refresh();
					}
				});


				
				tableLeftTab3.setHeaderVisible(true);
				GridData tableLeftTab3LData = new GridData();
				tableLeftTab3LData.verticalAlignment = GridData.FILL;
				tableLeftTab3LData.grabExcessVerticalSpace = true;
				tableLeftTab3LData.horizontalAlignment = GridData.FILL;
				tableLeftTab3LData.grabExcessHorizontalSpace = true;
				tableLeftTab3.setLayoutData(tableLeftTab3LData);
				tableLeftTab3.setLinesVisible(true);
				
				tablesVLeft[2]
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab3(event);

					}

				});
				
				tablesVLeft[2].addDoubleClickListener(new OpenClassListener(this));


		 		{
					GridData composite11LData = new GridData();
					composite11LData.verticalAlignment = GridData.FILL;
					composite11LData.horizontalAlignment = GridData.FILL;
					composite11 = new Composite(composite5, SWT.NONE);
					GridLayout composite11Layout = new GridLayout();
					composite11Layout.numColumns = 3;
					composite11.setLayout(composite11Layout);
					composite11.setLayoutData(composite11LData);
					
					{
						labelSearch3 = new CLabel(composite11, SWT.NONE);
						GridData labelSearch3Data = new GridData();
						labelSearch3Data.horizontalIndent = -5;
						labelSearch3Data.widthHint = 47;
						labelSearch3Data.heightHint = 21;
						labelSearch3.setLayoutData(labelSearch3Data);
						labelSearch3.setText("Search:");
									
					}
					{
						textSearch3 = new Text(composite11, SWT.BORDER);
						GridData textSearch3Data = new GridData();
						textSearch3Data.widthHint = 179;
						textSearch3Data.heightHint = 15;
						textSearch3.setLayoutData(textSearch3Data);
						textSearch3.setText("");
					}
					{
						buttonSearch3 = new Button(composite11, SWT.PUSH | SWT.CENTER);
						GridData buttonSearch3LData = new GridData();
						buttonSearch3.setLayoutData(buttonSearch3LData);
						buttonSearch3.setText("Search");
						
						buttonSearch3.addListener (SWT.Selection, new Listener () {
							public void handleEvent (Event event) {
								searchInTableLeftTab3.locateItemInTable(textSearch3.getText().toLowerCase(),tableLeftTab3);
								
							}
						});
						
					}
				}


				

			}
			{
				composite6 = new Composite(sashForm3, SWT.NONE);
				FillLayout composite6Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite6.setLayout(composite6Layout);
				composite6.setBounds(0, 0, 77, 81);
				{
					tableRightTab3 = new Table(composite6, SWT.LEFT | SWT.MULTI);
					tablesVRight[2] = new TableViewer(tableRightTab3);
					
					// Set the sorter
//					ViewerSorter sorterCalls = new SorterFanInViewCalls();
//					tableViewerRight.setSorter(sorterCalls);
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tablesVRight[2].setContentProvider(new FlowGraphContentProviderOBCalls());
					tablesVRight[2].setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightColumn1 = new TableColumn(tableRightTab3,
								SWT.NONE);
						tableRightColumn1.setText("Calls");
						tableRightColumn1.setWidth(300);
						tableRightColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) tablesVRight[2]
										.getSorter()).doSort(0);
								tablesVRight[2].refresh();
							}
						});
					}
	

					tableRightTab3.setHeaderVisible(true);


				}
			}
		}
 }
 
 public void createTab4(){
	 	
		{
			sashForm4 = new SashForm(cTabFolderFlowGraph, SWT.NONE);
			cTabItemInsideLastExecution.setControl(sashForm4);
			sashForm4.setSize(60, 30);
			{
				
				composite7 = new Composite(sashForm4, SWT.NULL);
				GridLayout composite7Layout = new GridLayout();
				composite7Layout.makeColumnsEqualWidth = true;
				composite7Layout.marginHeight = 0;
				composite7Layout.marginWidth = 0;
				composite7Layout.verticalSpacing = 0;
				composite7.setLayout(composite7Layout);
				composite7.setBounds(-483, -25, 461, 81);
				
				tableLeftTab4 = new Table(composite7, SWT.BORDER | SWT.MULTI);
				tablesVLeft[3] = new TableViewer(tableLeftTab4);

				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab4Left();
				tablesVLeft[3].setSorter(sorter);

				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tablesVLeft[3]
						.setContentProvider(new FlowGraphContentProviderIL());
				tablesVLeft[3].setLabelProvider(new FlowGraphLabelProviderIL());

				// Set up the table, each column has a listener for the click
				// that calls
				// the sorter and refreshes the tree.
				// Column 1
				final TableColumn tc31 = new TableColumn(tableLeftTab4, SWT.LEFT);
				tc31.setText("Method");
				tc31.setWidth(398);
				tc31
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab4Left) tablesVLeft[3]
								.getSorter()).doSort(0);
						tablesVLeft[3].refresh();
					}
				});


				// Column 2
				TableColumn tc32 = new TableColumn(tableLeftTab4, SWT.LEFT);
				tc32.setText("Value");
				tc32.setWidth(50);
				tc32
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab4Left) tablesVLeft[3]
								.getSorter()).doSort(1);
						tablesVLeft[3].refresh();
					}
				});


				
				tableLeftTab4.setHeaderVisible(true);
				GridData tableLeftTab4LData = new GridData();
				tableLeftTab4LData.verticalAlignment = GridData.FILL;
				tableLeftTab4LData.grabExcessVerticalSpace = true;
				tableLeftTab4LData.horizontalAlignment = GridData.FILL;
				tableLeftTab4LData.grabExcessHorizontalSpace = true;
				tableLeftTab4.setLayoutData(tableLeftTab4LData);
				tableLeftTab4.setLinesVisible(true);
				
				
				
				tablesVLeft[3]
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab4(event);

					}

				});
				
				tablesVLeft[3].addDoubleClickListener(new OpenClassListener(this));


				{
					GridData composite12LData = new GridData();
					composite12LData.verticalAlignment = GridData.FILL;
					composite12LData.horizontalAlignment = GridData.FILL;
					composite12 = new Composite(composite7, SWT.NONE);
					GridLayout composite12Layout = new GridLayout();
					composite12Layout.numColumns = 3;
					composite12.setLayout(composite12Layout);
					composite12.setLayoutData(composite12LData);
					
					{
						labelSearch4 = new CLabel(composite12, SWT.NONE);
						GridData labelSearch4Data = new GridData();
						labelSearch4Data.horizontalIndent = -5;
						labelSearch4Data.widthHint = 47;
						labelSearch4Data.heightHint = 21;
						labelSearch4.setLayoutData(labelSearch4Data);
						labelSearch4.setText("Search:");
									
					}
					{
						textSearch4 = new Text(composite12, SWT.BORDER);
						GridData textSearch4Data = new GridData();
						textSearch4Data.widthHint = 179;
						textSearch4Data.heightHint = 15;
						textSearch4.setLayoutData(textSearch4Data);
						textSearch4.setText("");
					}
					{
						buttonSearch4 = new Button(composite12, SWT.PUSH | SWT.CENTER);
						GridData buttonSearch3LData = new GridData();
						buttonSearch4.setLayoutData(buttonSearch3LData);
						buttonSearch4.setText("Search");
						
						buttonSearch4.addListener (SWT.Selection, new Listener () {
							public void handleEvent (Event event) {
								searchInTableLeftTab3.locateItemInTable(textSearch4.getText().toLowerCase(),tableLeftTab4);
								
							}
						});
						
					}
				}

				

			}
			{
				composite8 = new Composite(sashForm4, SWT.NONE);
				FillLayout composite8Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite8.setLayout(composite8Layout);
				composite8.setBounds(0, 0, 77, 81);
				{
					tableRightTab4 = new Table(composite8, SWT.LEFT | SWT.MULTI);
					tablesVRight[3] = new TableViewer(tableRightTab4);
					
					// Set the sorter
//					ViewerSorter sorterCalls = new SorterFanInViewCalls();
//					tableViewerRight.setSorter(sorterCalls);
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tablesVRight[3].setContentProvider(new FlowGraphContentProviderOBCalls());
					tablesVRight[3].setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightColumn1 = new TableColumn(tableRightTab4,
								SWT.NONE);
						tableRightColumn1.setText("Calls");
						tableRightColumn1.setWidth(300);
						tableRightColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) tablesVRight[3]
										.getSorter()).doSort(0);
								tablesVRight[3].refresh();
							}
						});
					}
	

					tableRightTab4.setHeaderVisible(true);


				}
			}
		}
 	}
 
	private void createContextMenu() {
        
		for (int i = 0; i < tablesVLeft.length ; i ++){
		
			MenuManager menuMgr = new MenuManager();
	        menuMgr.setRemoveAllWhenShown(true);
	        menuMgr.addMenuListener(new MenuLeftListener(tablesVLeft[i],selectAllActionsLeft[i],openClassActionTableL[i],selectMethodAsSeedAction[i]));
	        // Create menu for methodsTableViewer
	        Menu menu = menuMgr.createContextMenu(tablesVLeft[i].getControl());
	        tablesVLeft[i].getControl().setMenu(menu);
	        
	        // Register menu for extension.
	        getSite().registerContextMenu(menuMgr, tablesVLeft[i]);
			
		}
	        
		for (int i = 0; i < tablesVRight.length ; i ++){
		
			   MenuManager menuMgr1 = new MenuManager();
		        menuMgr1.setRemoveAllWhenShown(true);
		        menuMgr1.addMenuListener(new MenuRightListener(tablesVRight[i],selectAllActionsRight[i],openClassActionTableR[i]));
	        
	        // Create menu for methodsTableViewer
	        Menu menu1 = menuMgr1.createContextMenu(tablesVRight[i].getControl());
	        tablesVRight[i].getControl().setMenu(menu1);
	        
	        // Register menu for extension.
	        getSite().registerContextMenu(menuMgr1, tablesVRight[i]);
			
		}

	}

//	protected void fillContextMenuCallsTableViewer(IMenuManager mgr) {
//		mgr.add(openActionTableRightTab1);
//		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
//		mgr.add(new Separator());
//		mgr.add(selectAllActionCallsTableTab1);
//		
//	}
//
//	protected void fillContextMenuMethodsTableViewer(IMenuManager mgr) {
//		mgr.add(openActionTableLeftTab1);
//		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
//		mgr.add(selectAsSeedOperationTab1);
//		mgr.add(new Separator());
//		mgr.add(selectAllActionMethodsTableTab1);
//		
//	}
	
	/**
	 * Create the actions.
	 */
	public void createActions() {
		
		for (int i = 0 ; i < tablesVLeft.length ; i++){
			selectAllActionsLeft[i] = new SelectAllAction(tablesVLeft[i]);
			selectAllActionsRight[i] = new SelectAllAction(tablesVRight[i]); 
		}
		// Add selection listener.
		for (int i = 0 ; i< tablesVLeft.length ; i++){
			tablesVLeft[i].addSelectionChangedListener(new MenuLeftChangeListener(tablesVLeft[i],selectAllActionsLeft[i],openClassActionTableL[i],selectMethodAsSeedAction[i]));
		}

		for (int i = 0 ; i < tablesVRight.length ; i++){
			tablesVRight[i].addSelectionChangedListener(new MenuRightChangeListener(tablesVRight[i],selectAllActionsRight[i],openClassActionTableR[i]));
		
		}
	}
	
	private void hookGlobalActions() {
		
		for (int i = 0 ; i < selectAllActionsLeft.length ; i++){
			IActionBars bars = getViewSite().getActionBars();
			bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllActionsLeft[i]);
			bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllActionsRight[i]);			
		}

	}
	
//	protected void selectAll(TableViewer tableViewer) {
//		tableViewer.getTable().selectAll();
//		
//	}

}
