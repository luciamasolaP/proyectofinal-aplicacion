package aspectminingtool.views.Seeds;

import java.util.Arrays;

import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;


public class ViewPartSeeds extends ViewPart {
    public static final String ID_VIEW =
        "aspectminingtool.views.Seeds.ViewPartSeeds"; //$NON-NLS-1$

    Composite composite1;
    

	private Table table;
	private TableViewer tableViewer;
	private Button closeButton;
    
	
	// Create a ExampleTaskList and assign it to an instance variable 
	private ExampleTaskList taskList = new ExampleTaskList(); 

	// Set the table column property names
	private final String COMPLETED_COLUMN 		= "completed";
	private final String DESCRIPTION_COLUMN 	= "description";
	private final String OWNER_COLUMN 			= "owner";
	private final String PERCENT_COLUMN 		= "percent";

	// Set column names
	private String[] columnNames = new String[] { 
			COMPLETED_COLUMN, 
			DESCRIPTION_COLUMN,
			OWNER_COLUMN,
			PERCENT_COLUMN
			};
	
    /**
     * 
     */
    public ViewPartSeeds() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        
    	composite1 = new Composite(parent, SWT.NULL);
        composite1.setLayout(new GridLayout(4, false));
        this.addChildControls(composite1);
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
    
	private void addChildControls(Composite composite) {

		// Create a composite to hold the children
		GridData gridData = new GridData (GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_BOTH);
		composite.setLayoutData (gridData);

		// Set numColumns to 3 for the buttons 
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		composite.setLayout (layout);

		// Create the table 
		createTable(composite);
		
		// Create and setup the TableViewer
		createTableViewer();
		tableViewer.setContentProvider(new ExampleContentProvider());
		tableViewer.setLabelProvider(new ExampleLabelProvider());
		// The input for the table viewer is the instance of ExampleTaskList
		taskList = new ExampleTaskList();
		tableViewer.setInput(taskList);

		// Add the buttons
		createButtons(composite);
	}
	
	/**
	 * Create the Table
	 */
	private void createTable(Composite parent) {
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | 
					SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

		final int NUMBER_COLUMNS = 4;

		table = new Table(parent, style);
		
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		table.setLayoutData(gridData);		
					
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// 1st column with image/checkboxes - NOTE: The SWT.CENTER has no effect!!
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);		
		column.setText("!");
		column.setWidth(20);
		
		// 2nd column with task Description
		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText("Description");
		column.setWidth(400);
		// Add listener to column so tasks are sorted by description when clicked 
		column.addSelectionListener(new SelectionAdapter() {
       	
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
			}
		});

		// 3rd column with task Owner
		column = new TableColumn(table, SWT.LEFT, 2);
		column.setText("Owner");
		column.setWidth(100);
		// Add listener to column so tasks are sorted by owner when clicked
		column.addSelectionListener(new SelectionAdapter() {
       	
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.OWNER));
			}
		});

		// 4th column with task PercentComplete 
		column = new TableColumn(table, SWT.CENTER, 3);
		column.setText("% Complete");
		column.setWidth(80);
		//  Add listener to column so tasks are sorted by percent when clicked
		column.addSelectionListener(new SelectionAdapter() {
       	
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.PERCENT_COMPLETE));
			}
		});
	}
   
	/**
	 * Create the TableViewer 
	 */
	private void createTableViewer() {

		tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);
		
		tableViewer.setColumnProperties(columnNames);

		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNames.length];

		// Column 1 : Completed (Checkbox)
		editors[0] = new CheckboxCellEditor(table);

		// Column 2 : Description (Free text)
		TextCellEditor textEditor = new TextCellEditor(table);
		((Text) textEditor.getControl()).setTextLimit(60);
		editors[1] = textEditor;

		// Column 3 : Owner (Combo Box) 
		editors[2] = new ComboBoxCellEditor(table, taskList.getOwners(), SWT.READ_ONLY);

		// Column 4 : Percent complete (Text with digits only)
		textEditor = new TextCellEditor(table);
		((Text) textEditor.getControl()).addVerifyListener(
		
			new VerifyListener() {
				public void verifyText(VerifyEvent e) {
					// Here, we could use a RegExp such as the following 
					// if using JRE1.4 such as  e.doit = e.text.matches("[\\-0-9]*");
					e.doit = "0123456789".indexOf(e.text) >= 0 ;
				}
			});
		editors[3] = textEditor;

		// Assign the cell editors to the viewer 
		tableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		tableViewer.setCellModifier(new ExampleCellModifier(this));
		// Set the default sorter for the viewer 
		tableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
	}

	/**
	 * Add the "Add", "Delete" and "Close" buttons
	 * @param parent the parent composite
	 */
	private void createButtons(Composite parent) {
		
		// Create and configure the "Add" button
		Button add = new Button(parent, SWT.PUSH | SWT.CENTER);
		add.setText("Add");
		
		GridData gridData = new GridData (GridData.HORIZONTAL_ALIGN_BEGINNING);
		gridData.widthHint = 80;
		add.setLayoutData(gridData);
		add.addSelectionListener(new SelectionAdapter() {
       	
       		// Add a task to the ExampleTaskList and refresh the view
			public void widgetSelected(SelectionEvent e) {
				taskList.addTask();
			}
		});

		//	Create and configure the "Delete" button
		Button delete = new Button(parent, SWT.PUSH | SWT.CENTER);
		delete.setText("Delete");
		gridData = new GridData (GridData.HORIZONTAL_ALIGN_BEGINNING);
		gridData.widthHint = 80; 
		delete.setLayoutData(gridData); 

		delete.addSelectionListener(new SelectionAdapter() {
       	
			//	Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				ExampleTask task = (ExampleTask) ((IStructuredSelection) 
						tableViewer.getSelection()).getFirstElement();
				if (task != null) {
					taskList.removeTask(task);
				} 				
			}
		});
		
		//	Create and configure the "Close" button
		closeButton = new Button(parent, SWT.PUSH | SWT.CENTER);
		closeButton.setText("Close");
		gridData = new GridData (GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint = 80; 
		closeButton.setLayoutData(gridData); 		
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
		return tableViewer.getSelection();
	}

	/**
	 * Return the ExampleTaskList
	 */
	public ExampleTaskList getTaskList() {
		return taskList;	
	}

	/**
	 * Return the parent composite
	 */
	public Control getControl() {
		return table.getParent();
	}

	/**
	 * Return the 'close' Button
	 */
	public Button getCloseButton() {
		return closeButton;
	}
	
	/**
	 * Return the array of choices for a multiple choice cell
	 */
	public String[] getChoices(String property) {
		if (OWNER_COLUMN.equals(property))
			return taskList.getOwners();  // The ExampleTaskList knows about the choice of owners
		else
			return new String[]{};
	}

	public Object getModel() {
		return taskList;
	}
	
class ExampleContentProvider implements IStructuredContentProvider, ITaskListViewer {
public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	if (newInput != null)
		((ExampleTaskList) newInput).addChangeListener(this);
	if (oldInput != null)
		((ExampleTaskList) oldInput).removeChangeListener(this);
}

public void dispose() {
	taskList.removeChangeListener(this);
}

// Return the tasks as an array of Objects
public Object[] getElements(Object parent) {
	return taskList.getTasks().toArray();
}

/* (non-Javadoc)
 * @see ITaskListViewer#addTask(ExampleTask)
 */
public void addTask(ExampleTask task) {
	tableViewer.add(task);
}

/* (non-Javadoc)
 * @see ITaskListViewer#removeTask(ExampleTask)
 */
public void removeTask(ExampleTask task) {
	tableViewer.remove(task);			
}

/* (non-Javadoc)
 * @see ITaskListViewer#updateTask(ExampleTask)
 */
public void updateTask(ExampleTask task) {
	tableViewer.update(task, null);	
}
}
	
}
