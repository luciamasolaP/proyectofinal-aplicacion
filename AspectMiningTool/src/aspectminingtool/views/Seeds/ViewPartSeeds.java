package aspectminingtool.views.Seeds;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.Method;
import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.FanIn.CallsContentProviderFanIn;
import aspectminingtool.views.FanIn.CallsLabelProviderFanIn;
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
public class ViewPartSeeds extends ViewPart {
    public static final String ID_VIEW =
        "aspectminingtool.views.Seeds.ViewPartSeeds"; //$NON-NLS-1$


    private SashForm sashForm;
    private Composite composite1;
    private Composite composite2;

	private Table tableMethod;
	private TableViewer tableViewerMethod;
	private Button closeButton;
    
	
	private TableColumn tableCallsColumn1;
	private Table callsTable;
	private TableViewer callsTableViewer;
	
	// Create a ExampleTaskList and assign it to an instance variable 
	private ModelSeedsFanIn model = new ModelSeedsFanIn(); 

	// Set the table column property names
	private final String METHOD_COLUMN			= "Method";
	private final String DESCRIPTION_COLUMN 	= "description";


	// Set column names
	private String[] columnNames = new String[] { 
			METHOD_COLUMN,
			DESCRIPTION_COLUMN
			};
	
    /**
     * 
     */
    public ViewPartSeeds() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void addMethodToModel(Method method, List<Call_Counted> list ){
    	MethodDescription et = new MethodDescription("");
    	et.setMethod(method);
    	model.addMethodAsASeed(et,method.getId(),list);
    	
    	
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
    	
    	{
    		sashForm = new SashForm(parent, SWT.NONE);
    		sashForm.setSize(60, 30);
    		{
    			
    			
				composite1 = new Composite(sashForm, SWT.NULL);
				FillLayout composite1Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite1.setLayout(composite1Layout);
				composite1.setBounds(-483, -25, 461, 81);
				this.addChildControls(composite1);
    		}
    		{

				composite2 = new Composite(sashForm, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite2.setLayout(composite2Layout);
				composite2.setBounds(0, 0, 77, 81);
				this.addCallsView(composite2);
    		}
    	}

    }

    private void addCallsView(Composite composite2) {

		callsTable = new Table(composite2, SWT.LEFT | SWT.MULTI);
		callsTableViewer = new TableViewer(callsTable);
		
		// Set the sorter
		ViewerSorter sorterCalls = new SorterFanInViewCalls();
		callsTableViewer.setSorter(sorterCalls);
		
		// Set the content and label providers
		callsTableViewer.setContentProvider(new CallsContentProviderFanIn());
		callsTableViewer.setLabelProvider(new CallsLabelProviderFanIn());
		
		{
			tableCallsColumn1 = new TableColumn(callsTable,
					SWT.NONE);
			tableCallsColumn1.setText("Calls");
			tableCallsColumn1.setWidth(300);
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
		
		callsTableViewer.addDoubleClickListener(new IDoubleClickListener(){

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty()) {
					
					if (event.getSelection() instanceof IStructuredSelection) {
						
						Call_Counted callCounted = (Call_Counted) ((IStructuredSelection) event.getSelection()).getFirstElement();
						String name = callCounted.getCaller_id();
						int index = name.indexOf("//");
						name = name.substring(0, index);
						//openResource(name);
					}
				}
				
			}
			
		});
		
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
    
	private void addChildControls(Composite composite1) {

		// Create a composite to hold the children
		GridData gridData = new GridData (GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_BOTH);
		composite1.setLayoutData (gridData);

		// Set numColumns to 3 for the buttons 
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		composite1.setLayout (layout);

		// Create the table 
		createTable(composite1);
		
		// Create and setup the TableViewer
		createTableViewer();
		tableViewerMethod.setContentProvider(new ExampleContentProvider());
		tableViewerMethod.setLabelProvider(new ExampleLabelProvider());
		// The input for the table viewer is the instance of ExampleTaskList
		model = new ModelSeedsFanIn();
		tableViewerMethod.setInput(model);

		// Add the buttons
		createButtons(composite1);
	}
	
	/**
	 * Create the Table
	 */
	private void createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
					SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		final int NUMBER_COLUMNS = 4;

		tableMethod = new Table(parent, style);
		
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		tableMethod.setLayoutData(gridData);		
					
		tableMethod.setLinesVisible(true);
		tableMethod.setHeaderVisible(true);

		
		TableColumn column = new TableColumn(tableMethod, SWT.CENTER, 0);		
		column.setText("Method");
		column.setWidth(400);
		
		// 2nd column with task Description
		column = new TableColumn(tableMethod, SWT.LEFT, 1);
		column.setText("Description");
		column.setWidth(400);
		// Add listener to column so tasks are sorted by description when clicked 
		column.addSelectionListener(new SelectionAdapter() {
       	
			public void widgetSelected(SelectionEvent e) {
				//tableViewerMethod.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
			}
		});
		


	}
   
	/**
	 * Create the TableViewer 
	 */
	private void createTableViewer() {

		tableViewerMethod = new TableViewer(tableMethod);
		tableViewerMethod.setUseHashlookup(true);
		
		tableViewerMethod.setColumnProperties(columnNames);

		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNames.length];

		// Column 1 : Completed (Checkbox)
		editors[0] = null;

		// Column 2 : Description (Free text)
		TextCellEditor textEditor = new TextCellEditor(tableMethod);
		editors[1] = textEditor;


		// Assign the cell editors to the viewer 
		tableViewerMethod.setCellEditors(editors);
		// Set the cell modifier for the viewer
		tableViewerMethod.setCellModifier(new ExampleCellModifier(this));
		
		tableViewerMethod
		.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(
					SelectionChangedEvent event) {
				selectionItem(event);

			}

		});
		
		// Set the default sorter for the viewer 
//		tableViewerMethod.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
	}

	protected void selectionItem(SelectionChangedEvent event) {
		
		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MethodDescription metodo = (MethodDescription) ((IStructuredSelection) event.getSelection()).getFirstElement();
				String key = metodo.getMethod().getId();
				List<Call_Counted> llamadas = model.getCalls(key);
				callsTableViewer.setInput(llamadas);

			}

		}
		
	}


	/**
	 * Add the "Add", "Delete" and "Close" buttons
	 * @param parent the parent composite
	 */
	private void createButtons(Composite parent) {
		
//		// Create and configure the "Add" button
//		Button add = new Button(parent, SWT.PUSH | SWT.CENTER);
//		add.setText("Add");
//		
//		GridData gridData = new GridData (GridData.HORIZONTAL_ALIGN_BEGINNING);
//		gridData.widthHint = 80;
//		add.setLayoutData(gridData);
//		add.addSelectionListener(new SelectionAdapter() {
//       	
//       		// Add a task to the ExampleTaskList and refresh the view
//			public void widgetSelected(SelectionEvent e) {
//				model.addTask();
//			}
//		});
//
//		//	Create and configure the "Delete" button
//		Button delete = new Button(parent, SWT.PUSH | SWT.CENTER);
//		delete.setText("Delete");
//		gridData = new GridData (GridData.HORIZONTAL_ALIGN_BEGINNING);
//		gridData.widthHint = 80; 
//		delete.setLayoutData(gridData); 
//
//		delete.addSelectionListener(new SelectionAdapter() {
//       	
//			//	Remove the selection and refresh the view
//			public void widgetSelected(SelectionEvent e) {
//				MethodDescription task = (MethodDescription) ((IStructuredSelection) 
//						tableViewerMethod.getSelection()).getFirstElement();
//				if (task != null) {
//					model.removeTask(task);
//				} 				
//			}
//		});
//		
//		//	Create and configure the "Close" button
//		closeButton = new Button(parent, SWT.PUSH | SWT.CENTER);
//		closeButton.setText("Close");
//		gridData = new GridData (GridData.HORIZONTAL_ALIGN_END);
//		gridData.widthHint = 80; 
//		closeButton.setLayoutData(gridData); 		
	}

	/**
	 * Return the column names in a collection
	 * 
	 * @return List  containing column names
	 */
	public java.util.List getColumnNames() {
		return Arrays.asList(columnNames);
	}

	/**
	 * @return currently selected item
	 */
	public ISelection getSelection() {
		return tableViewerMethod.getSelection();
	}

	/**
	 * Return the ExampleTaskList
	 */
	public ModelSeedsFanIn getTaskList() {
		return model;	
	}

	/**
	 * Return the parent composite
	 */
	public Control getControl() {
		return tableMethod.getParent();
	}

	/**
	 * Return the 'close' Button
	 */
	public Button getCloseButton() {
		return closeButton;
	}
	

	public Object getModel() {
		return model;
	}
	
class ExampleContentProvider implements IStructuredContentProvider, ITaskListViewer {
public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	if (newInput != null)
		((ModelSeedsFanIn) newInput).addChangeListener(this);
	if (oldInput != null)
		((ModelSeedsFanIn) oldInput).removeChangeListener(this);
}

public void dispose() {
	model.removeChangeListener(this);
}

// Return the tasks as an array of Objects
public Object[] getElements(Object parent) {
	return model.getTasks().toArray();
}

/* (non-Javadoc)
 * @see ITaskListViewer#addTask(ExampleTask)
 */
public void addTask(MethodDescription task) {
	tableViewerMethod.add(task);
}

/* (non-Javadoc)
 * @see ITaskListViewer#removeTask(ExampleTask)
 */
public void removeTask(MethodDescription task) {
	tableViewerMethod.remove(task);			
}

/* (non-Javadoc)
 * @see ITaskListViewer#updateTask(ExampleTask)
 */
public void updateTask(MethodDescription task) {
	tableViewerMethod.update(task, null);	
}
}



	
}
