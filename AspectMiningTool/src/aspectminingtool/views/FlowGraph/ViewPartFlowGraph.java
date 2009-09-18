package aspectminingtool.views.FlowGraph;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.FanIn.SorterFanInViewCalls;
import aspectminingtool.views.FanIn.SorterFanInViewFanIn;

import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;
import aspectminingtool.JessIntegrationModel.FlowGraph.OutsideBeforeExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.OutsideAfterExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.InsideFirstExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.InsideLastExecutionMetric;


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
public class ViewPartFlowGraph extends ViewPart {
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
    	private Table tableRight;
    	private TableViewer tableViewerRight;

    	private Table tableLeftTab2;
		private TableViewer tableViewerLeftTab2;
		private Table tableRightTab2;
		private TableViewer tableViewerRightTab2;
		
		private Table tableLeftTab3;
		private TableViewer tableViewerLeftTab3;
		private Table tableRightTab3;
		private TableViewer tableViewerRightTab3;
		
		private Table tableLeftTab4;
		private TableViewer tableViewerLeftTab4;
		private Table tableRightTab4;
		private TableViewer tableViewerRightTab4;

    	private IResultsModel model;
    	
    	private Composite composite1;
    	private Composite composite2;
    	private Composite composite3;
    	private Composite composite4;
    	private Composite composite5;
    	private Composite composite6;
    	private Composite composite7;
    	private Composite composite8;

    
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
				FillLayout composite1Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
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


				// Turn on the header and the lines
				tableLeft.setHeaderVisible(true);
				tableLeft.setLinesVisible(true);
				
				tableViewerLeft
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab1(event);

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
				FillLayout composite1Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite3.setLayout(composite1Layout);
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


				// Turn on the header and the lines
				tableLeftTab2.setHeaderVisible(true);
				tableLeftTab2.setLinesVisible(true);
				
				tableViewerLeftTab2
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab2(event);

					}

				});



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
				FillLayout composite5Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
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


				// Turn on the header and the lines
				tableLeftTab3.setHeaderVisible(true);
				tableLeftTab3.setLinesVisible(true);
				
				tableViewerLeftTab3
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab3(event);

					}

				});



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
				FillLayout composite7Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
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


				// Turn on the header and the lines
				tableLeftTab4.setHeaderVisible(true);
				tableLeftTab4.setLinesVisible(true);
				
				tableViewerLeftTab4
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(
							SelectionChangedEvent event) {
						selectionItemTab4(event);

					}

				});



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
}
