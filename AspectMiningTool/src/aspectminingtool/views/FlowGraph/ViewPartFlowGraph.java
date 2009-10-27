package aspectminingtool.views.FlowGraph;

import java.util.List;

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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;
import aspectminingtool.JessIntegrationModel.FlowGraph.InsideFirstExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.InsideLastExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.OutsideAfterExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.OutsideBeforeExecutionMetric;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;
import aspectminingtool.views.AbstractView;
import aspectminingtool.views.SearchInTable;
import aspectminingtool.views.ViewAlgorithmInterface;
import aspectminingtool.views.FanIn.SorterFanInViewCalls;


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
    	private TableViewer tableViewerLeft;
    	private SearchInTable searchInTableLeft = new SearchInTable();
    	private Table tableRight;
    	private TableViewer tableViewerRight;
    	
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
		tableViewerLeft.setInput(model);
		tableViewerLeftTab2.setInput(model);
		tableViewerLeftTab3.setInput(model);
		tableViewerLeftTab4.setInput(model);
	}
	
	private void selectionItemTab1(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				OutsideBeforeExecutionMetric relation = (OutsideBeforeExecutionMetric) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getOutsideBeforeExecutionMethods(relation.getMethod());
				tableViewerRight.setInput(relatedMethos);
			}
		}
	}
	
	private void selectionItemTab2(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				OutsideAfterExecutionMetric relation = (OutsideAfterExecutionMetric) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getOutsideAfterExecutionMethods(relation.getMethod());
				tableViewerRightTab2.setInput(relatedMethos);
			}
		}
	}
	
	private void selectionItemTab3(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				InsideFirstExecutionMetric relation = (InsideFirstExecutionMetric) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getInsideFirstExecutionMethods(relation.getMethod());
				tableViewerRightTab3.setInput(relatedMethos);
			}
		}
	}
	
	private void selectionItemTab4(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				InsideLastExecutionMetric relation = (InsideLastExecutionMetric) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<Method> relatedMethos = ((FlowGraphModel) model).getInsideLastExecutionMethods(relation.getMethod());
				tableViewerRightTab4.setInput(relatedMethos);
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
				tableViewerLeft = new TableViewer(tableLeft);
				
				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab1Left();
				tableViewerLeft.setSorter(sorter);

				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tableViewerLeft
						.setContentProvider(new FlowGraphContentProviderOB());
				tableViewerLeft.setLabelProvider(new FlowGraphLabelProviderOB());

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
						((SorterFlowGraphTab1Left) tableViewerLeft
								.getSorter()).doSort(0);
						tableViewerLeft.refresh();
					}
				});


				// Column 2
				TableColumn tc2 = new TableColumn(tableLeft, SWT.LEFT);
				tc2.setText("Value");
				tc2.setWidth(50);
				tc2
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab1Left) tableViewerLeft
								.getSorter()).doSort(1);
						tableViewerLeft.refresh();
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

				
				tableViewerLeft
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab1(event);

					}

				});

				
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
					tableViewerRight = new TableViewer(tableRight);
					
					// Set the sorter
//					ViewerSorter sorterCalls = new SorterFanInViewCalls();
//					tableViewerRight.setSorter(sorterCalls);
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tableViewerRight.setContentProvider(new FlowGraphContentProviderOBCalls());
					tableViewerRight.setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightColumn1 = new TableColumn(tableRight,
								SWT.NONE);
						tableRightColumn1.setText("Calls");
						tableRightColumn1.setWidth(300);
						tableRightColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) tableViewerRight
										.getSorter()).doSort(0);
								tableViewerRight.refresh();
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
				tableViewerLeftTab2 = new TableViewer(tableLeftTab2);

				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab2Left();
				tableViewerLeftTab2.setSorter(sorter);

				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tableViewerLeftTab2
						.setContentProvider(new FlowGraphContentProviderOA());
				tableViewerLeftTab2.setLabelProvider(new FlowGraphLabelProviderOA());

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
						((SorterFlowGraphTab2Left) tableViewerLeftTab2
								.getSorter()).doSort(0);
						tableViewerLeftTab2.refresh();
					}
				});


				// Column 2
				TableColumn tc22 = new TableColumn(tableLeftTab2, SWT.LEFT);
				tc22.setText("Value");
				tc22.setWidth(50);
				tc22
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab2Left) tableViewerLeftTab2
								.getSorter()).doSort(1);
						tableViewerLeftTab2.refresh();
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
				
				tableViewerLeftTab2
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab2(event);

					}

				});

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
					tableViewerRightTab2 = new TableViewer(tableRightTab2);
					
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tableViewerRightTab2.setContentProvider(new FlowGraphContentProviderOBCalls());
					tableViewerRightTab2.setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
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
				tableViewerLeftTab3 = new TableViewer(tableLeftTab3);

				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab3Left();
				tableViewerLeftTab3.setSorter(sorter);
				
				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tableViewerLeftTab3
						.setContentProvider(new FlowGraphContentProviderIF());
				tableViewerLeftTab3.setLabelProvider(new FlowGraphLabelProviderIF());

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
						((SorterFlowGraphTab3Left) tableViewerLeftTab3
								.getSorter()).doSort(0);
						tableViewerLeftTab3.refresh();
					}
				});


				// Column 2
				TableColumn tc32 = new TableColumn(tableLeftTab3, SWT.LEFT);
				tc32.setText("Value");
				tc32.setWidth(50);
				tc32
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab3Left) tableViewerLeftTab3
								.getSorter()).doSort(1);
						tableViewerLeftTab3.refresh();
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
				
				tableViewerLeftTab3
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab3(event);

					}

				});


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
					tableViewerRightTab3 = new TableViewer(tableRightTab3);
					
					// Set the sorter
//					ViewerSorter sorterCalls = new SorterFanInViewCalls();
//					tableViewerRight.setSorter(sorterCalls);
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tableViewerRightTab3.setContentProvider(new FlowGraphContentProviderOBCalls());
					tableViewerRightTab3.setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightColumn1 = new TableColumn(tableRightTab3,
								SWT.NONE);
						tableRightColumn1.setText("Calls");
						tableRightColumn1.setWidth(300);
						tableRightColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) tableViewerRightTab3
										.getSorter()).doSort(0);
								tableViewerRightTab3.refresh();
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
				tableViewerLeftTab4 = new TableViewer(tableLeftTab4);

				// Set the sorter
				ViewerSorter sorter = new SorterFlowGraphTab4Left();
				tableViewerLeftTab4.setSorter(sorter);

				// Set the content and label providers ACA tienen que ir tus contentsProviders!
				tableViewerLeftTab4
						.setContentProvider(new FlowGraphContentProviderIL());
				tableViewerLeftTab4.setLabelProvider(new FlowGraphLabelProviderIL());

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
						((SorterFlowGraphTab4Left) tableViewerLeftTab4
								.getSorter()).doSort(0);
						tableViewerLeftTab4.refresh();
					}
				});


				// Column 2
				TableColumn tc32 = new TableColumn(tableLeftTab4, SWT.LEFT);
				tc32.setText("Value");
				tc32.setWidth(50);
				tc32
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(SelectionEvent event) {
						((SorterFlowGraphTab4Left) tableViewerLeftTab4
								.getSorter()).doSort(1);
						tableViewerLeftTab4.refresh();
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
				
				
				
				tableViewerLeftTab4
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab4(event);

					}

				});


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
					tableViewerRightTab4 = new TableViewer(tableRightTab4);
					
					// Set the sorter
//					ViewerSorter sorterCalls = new SorterFanInViewCalls();
//					tableViewerRight.setSorter(sorterCalls);
					
					// Set the content and label providers ACA tienen que ir tus contentsProviders DE LA SEGUNDA TABLA!
					tableViewerRightTab4.setContentProvider(new FlowGraphContentProviderOBCalls());
					tableViewerRightTab4.setLabelProvider(new FlowGraphLabelProviderOBCalls());
					
					{
						TableColumn tableRightColumn1 = new TableColumn(tableRightTab4,
								SWT.NONE);
						tableRightColumn1.setText("Calls");
						tableRightColumn1.setWidth(300);
						tableRightColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterFanInViewCalls) tableViewerRightTab4
										.getSorter()).doSort(0);
								tableViewerRightTab4.refresh();
							}
						});
					}
	

					tableRightTab4.setHeaderVisible(true);


				}
			}
		}
}

	@Override
	public List<RelatedMethodDescription> getRelatedMethods(List relatedMethods) {
		// TODO Auto-generated method stub
		return null;
	}
}
