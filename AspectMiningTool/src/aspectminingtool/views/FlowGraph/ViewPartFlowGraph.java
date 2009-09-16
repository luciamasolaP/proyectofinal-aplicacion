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

import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;
import aspectminingtool.JessIntegrationModel.FlowGraph.OutsideBeforeExecutionMetric;
import aspectminingtool.JessIntegrationModel.FlowGraph.OutsideAfterExecutionMetric;


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
        private SashForm sashForm2;
        private CTabItem cTabItemInsideFirstExecution;
        private CTabItem cTabItemInsideLastExecution;
        private CTabItem cTabItemOutsideAfterExecution;
        private CTabItem cTabItemOutsideBeforeExecution;
        private CTabFolder cTabFolderFlowGraph;
        
  
    	private SashForm sashForm1;
    	private Table tableRight;
    	private TableViewer tableViewerRight;
    	
    	private TableViewer tableViewerLeft;
    	private Table tableLeft = null;

    	private Table tableLeftTab2;
		private TableViewer tableViewerLeftTab2;
		private Table tableRightTab2;
		private TableViewer tableViewerRightTab2;

    	private IResultsModel model;
    	private Composite composite2;
    	private Composite composite1;
    	private Composite composite3;
    	private Composite composite4;


    
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
		//tableViewerRight.setInput(model);
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
        	}
        	{
        		cTabItemInsideLastExecution = new CTabItem(cTabFolderFlowGraph, SWT.NONE);
        		cTabItemInsideLastExecution.setText("Inside Last Execution");
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


				// Column 2
				TableColumn tc2 = new TableColumn(tableLeft, SWT.LEFT);
				tc2.setText("Value");
				tc2.setWidth(50);


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


				// Column 2
				TableColumn tc22 = new TableColumn(tableLeftTab2, SWT.LEFT);
				tc22.setText("Value");
				tc22.setWidth(50);


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
}
