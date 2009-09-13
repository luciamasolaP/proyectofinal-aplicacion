package aspectminingtool.views.Seeds;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.FanIn.CallsContentProviderFanIn;
import aspectminingtool.views.FanIn.CallsLabelProviderFanIn;
import aspectminingtool.views.FanIn.SorterFanInViewCalls;
import aspectminingtool.views.FanIn.SorterFanInViewFanIn;

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
public class ViewPartSeeds extends ViewPart {
	public static final String ID_VIEW = "aspectminingtool.views.Seeds.ViewPartSeeds"; //$NON-NLS-1$

	private SashForm sashForm;
	private Composite composite1;
	private Composite composite2;

	private Table tableMethod;
	private TableViewer tableViewerMethod;
	private Button closeButton;

	private TableColumn tableCallsColumn1;
	private Table callsTable;
	private TableViewer callsTableViewer;
	private Action openItemActionMethodsTable, openItemActionCallsTable,
			deleteAction, selectAllActionMethodsTable,
			selectAllActionCallsTable;

	// Create a ExampleTaskList and assign it to an instance variable
	private IResultsModel model = new ModelSeedsFanIn();

	// Set the table column property names
	private final String METHOD_COLUMN = "Method";
	private final String DESCRIPTION_COLUMN = "description";

	// Set column names
	private String[] columnNames = new String[] { METHOD_COLUMN,
			DESCRIPTION_COLUMN };

	/**
     * 
     */
	public ViewPartSeeds() {
		super();
	}

	public void addMethodToModel(Method method, List<Call_Counted> list,
			ProjectModel projectModel) {

		MethodDescription et = new MethodDescription("");
		et.setMethod(method);
		((ModelSeedsFanIn) model).setProjectModel(projectModel);
		((ModelSeedsFanIn) model).addMethodAsASeed(et, method.getId(), list);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
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

		createActions();
		createContextMenu();
		hookGlobalActions();

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
			tableCallsColumn1 = new TableColumn(callsTable, SWT.NONE);
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

		callsTableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty()) {

					if (event.getSelection() instanceof IStructuredSelection) {

						Call_Counted callCounted = (Call_Counted) ((IStructuredSelection) event
								.getSelection()).getFirstElement();
						String name = callCounted.getCaller_id();
						int index = name.indexOf("//");
						name = name.substring(0, index);
						// openResource(name);
					}
				}

			}

		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	public void setFocus() {
	}

	/**
	 * Cleans up all resources created by this ViewPart.
	 */
	public void dispose() {
		super.dispose();
	}

	private void addChildControls(Composite composite1) {

		// Set numColumns to 3 for the buttons
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		composite1.setLayout(layout);

		// Create the table
		createTable(composite1);

		// Create and setup the TableViewer
		createTableViewer();
		tableViewerMethod.setContentProvider(new ExampleContentProvider());
		tableViewerMethod
				.setLabelProvider(new MethodsDescriptionLabelProvider());

		// The input for the table viewer is the instance of ExampleTaskList
		model = new ModelSeedsFanIn();
		tableViewerMethod.setInput(model);

	}

	/**
	 * Create the Methods Table
	 */
	private void createTable(Composite parent) {

		final int NUMBER_COLUMNS = 4;

		tableMethod = new Table(parent, SWT.BORDER | SWT.MULTI);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		tableMethod.setLayoutData(gridData);

		tableMethod.setLinesVisible(true);
		tableMethod.setHeaderVisible(true);

		TableColumn column = new TableColumn(tableMethod, SWT.CENTER, 0);
		column.setText("Method");
		column.setWidth(200);
		// Add listener to column so tasks are sorted by Method when clicked
		column
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterMethodDescriptionView) tableViewerMethod
						.getSorter()).doSort(0);
				tableViewerMethod.refresh();
			}
		});

		// 2nd column with task Description
		column = new TableColumn(tableMethod, SWT.LEFT, 1);
		column.setText("Description");
		column.setWidth(600);
		// Add listener to column so tasks are sorted by description when clicked
		column
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterMethodDescriptionView) tableViewerMethod
						.getSorter()).doSort(1);
				tableViewerMethod.refresh();
			}
		});

	}

	/**
	 * Create the Methods TableViewer
	 */
	private void createTableViewer() {

		tableViewerMethod = new TableViewer(tableMethod);
		tableViewerMethod.setUseHashlookup(true);

		tableViewerMethod.setColumnProperties(columnNames);

		// Set the sorter
		ViewerSorter sorter = new SorterMethodDescriptionView();
		tableViewerMethod.setSorter(sorter);
		
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
		tableViewerMethod.setCellModifier(new CellModifierMethodsDescription(
				this));

		tableViewerMethod
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						selectionItem(event);

					}

				});
		
		tableViewerMethod.addDoubleClickListener(new IDoubleClickListener(){

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty()) {
					
					if (event.getSelection() instanceof IStructuredSelection) {
						
						MethodDescription methodDescription = (MethodDescription) ((IStructuredSelection) event.getSelection()).getFirstElement();
						openResource(methodDescription.getMethod().getClass_id());
					}
				}
				
			}
			
		});

		// Set the default sorter for the viewer
		// tableViewerMethod.setSorter(new
		// ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
	}

	protected void selectionItem(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MethodDescription metodo = (MethodDescription) ((IStructuredSelection) event
						.getSelection()).getFirstElement();
				String key = metodo.getMethod().getId();
				List<Call_Counted> llamadas = ((ModelSeedsFanIn) model)
						.getCalls(key);
				callsTableViewer.setInput(llamadas);

			}

		}

	}

	/**
	 * Return the column names in a collection
	 * 
	 * @return List containing column names
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
	public IResultsModel getMethodsDescription() {
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

	private void createContextMenu() {
		// Create menu manager for methodsTableViewer
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenuMethodsTableViewer(mgr);
			}

		});
		// Create menu for methodsTableViewer
		Menu menu = menuMgr.createContextMenu(tableViewerMethod.getControl());
		tableViewerMethod.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr, tableViewerMethod);

		// Create menu manager for methodsTableViewer for callsTableViewer
		MenuManager menuMgr1 = new MenuManager();
		menuMgr1.setRemoveAllWhenShown(true);
		menuMgr1.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenuCallsTableViewer(mgr);
			}

		});
		// Create menu for callsTableViewer
		Menu menu1 = menuMgr1.createContextMenu(callsTableViewer.getControl());
		callsTableViewer.getControl().setMenu(menu1);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr1, callsTableViewer);
	}

	protected void fillContextMenuMethodsTableViewer(IMenuManager mgr) {
		mgr.add(openItemActionMethodsTable);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(deleteAction);
		mgr.add(new Separator());
		mgr.add(selectAllActionMethodsTable);
	}

	protected void fillContextMenuCallsTableViewer(IMenuManager mgr) {
		mgr.add(openItemActionCallsTable);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(new Separator());
		mgr.add(selectAllActionCallsTable);

	}

	private void hookGlobalActions() {
		IActionBars bars = getViewSite().getActionBars();
		bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL,
				selectAllActionMethodsTable);

	}

	/**
	 * Create the actions.
	 */
	public void createActions() {
		openItemActionMethodsTable = new Action("Open") {
			public void run() {
				IStructuredSelection sel = (IStructuredSelection) tableViewerMethod
						.getSelection();
				Iterator iter = sel.iterator();
				while (iter.hasNext()) {
					MethodDescription methodDescription = (MethodDescription) iter
							.next();
					if (methodDescription != null) {
						String id = methodDescription.getMethod().getClass_id();
						openResource(id);
					}

				}
			}
		};

		deleteAction = new Action("Delete") {
			public void run() {
				IStructuredSelection sel = (IStructuredSelection) tableViewerMethod
						.getSelection();
				Iterator iter = sel.iterator();
				while (iter.hasNext()) {
					MethodDescription methodDescription = (MethodDescription) iter
							.next();
					if (methodDescription != null) {
						((ModelSeedsFanIn) model)
								.removeMethodDescription(methodDescription);
					}

				}

			}
		};

		selectAllActionMethodsTable = new Action("Select All") {
			public void run() {
				selectAll(tableViewerMethod);
			}
		};

		openItemActionCallsTable = new Action("Open") {
			public void run() {
				IStructuredSelection sel = (IStructuredSelection) callsTableViewer
						.getSelection();
				Iterator iter = sel.iterator();
				while (iter.hasNext()) {
					Call_Counted callCounted = (Call_Counted) iter.next();
					String name = callCounted.getCaller_id();
					int index = name.indexOf("//");
					name = name.substring(0, index);
					openResource(name);

				}
			}
		};

		selectAllActionCallsTable = new Action("Select All") {
			public void run() {
				selectAll(callsTableViewer);
			}
		};

		// Add selection listener.
		tableViewerMethod
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection sel = (IStructuredSelection) tableViewerMethod
								.getSelection();
						openItemActionMethodsTable.setEnabled(sel.size() > 0);
						selectAllActionMethodsTable.setEnabled(sel.size() > 0);
						deleteAction.setEnabled(sel.size() > 0);
					}
				});

		callsTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection sel = (IStructuredSelection) tableViewerMethod
								.getSelection();
						selectAllActionCallsTable.setEnabled(sel.size() > 0);
						openItemActionCallsTable.setEnabled(sel.size() > 0);
					}
				});

	}

	protected void selectAll(TableViewer tableViewer) {
		tableViewer.getTable().selectAll();

	}

	private void openResource(String resourceName) {

		ProjectModel projectModel = model.getProjectModel();
		IResource resource = projectModel.getAssociatedResource(resourceName);
		if (resource != null) {
			IFile fileStore = ResourcesPlugin.getWorkspace().getRoot().getFile(
					resource.getFullPath());
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor(page, fileStore, true);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class ExampleContentProvider implements IStructuredContentProvider,
			MethodDesccriptionListViewer {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			if (newInput != null)
				((ModelSeedsFanIn) newInput).addChangeListener(this);
			if (oldInput != null)
				((ModelSeedsFanIn) oldInput).removeChangeListener(this);
		}

		public void dispose() {
			((ModelSeedsFanIn) model).removeChangeListener(this);
		}

		// Return the tasks as an array of Objects
		public Object[] getElements(Object parent) {
			return ((ModelSeedsFanIn) model).getTasks().toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#addTask(ExampleTask)
		 */
		public void addTask(MethodDescription task) {
			tableViewerMethod.add(task);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#removeTask(ExampleTask)
		 */
		public void removeTask(MethodDescription task) {
			tableViewerMethod.remove(task);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#updateTask(ExampleTask)
		 */
		public void updateTask(MethodDescription task) {
			tableViewerMethod.update(task, null);
		}
	}

}
