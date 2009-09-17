package aspectminingtool.views.UniqueMethodsSeeds;

import java.util.ArrayList;
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
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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
import aspectminingtool.JessIntegrationModel.Seeds.CallDescription;
import aspectminingtool.JessIntegrationModel.Seeds.CallDescriptionListViewer;
import aspectminingtool.JessIntegrationModel.Seeds.MethodDescription;
import aspectminingtool.JessIntegrationModel.Seeds.MethodDescriptionListViewer;
import aspectminingtool.JessIntegrationModel.Seeds.SeedsModel;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.ViewFilterProject;
import aspectminingtool.views.ViewSeedsInterface;
import aspectminingtool.views.FanIn.SorterFanInViewCalls;


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
public class ViewPartUniqueMethodsSeeds extends ViewPart implements ViewSeedsInterface,ViewFilterProject {
	public static final String ID_VIEW = "aspectminingtool.views.UniqueMethodsSeeds.ViewPartUniqueMethodsSeeds"; //$NON-NLS-1$

	private SashForm sashForm;
	private Composite composite1;
	private Composite composite2;

	private Table tableMethod;
	private TableViewer tableViewerMethod;
	private Button closeButton;

	private Table callsTable;
	private TableViewer callsTableViewer;
	private Action openItemActionMethodsTable, openItemActionCallsTable,
			deleteAction, selectAllActionMethodsTable,
			selectAllActionCallsTable;

	// Create a ExampleTaskList and assign it to an instance variable
	private IResultsModel model = new SeedsModel();

	// Set the table column property names for tableViewerMethod
	private final String METHOD_NAME_COLUMN = "Method";
	private final String METHOD_DESCRIPTION_COLUMN = "description";

	// Set the table column property names for callsTableViewer
	private final String CALL_SELECTED_COLUMN = "";
	private final String CALL_NAME_COLUMN = "Caller Method";
	private final String CALL_DESCRIPTION_COLUMN = "Description";
	
	// Set column names
	private String[] columnNamesMethodsTable = new String[] { METHOD_NAME_COLUMN,
			METHOD_DESCRIPTION_COLUMN };
	
	private String[] columnNamesCallsTable = new String[] { CALL_SELECTED_COLUMN, CALL_NAME_COLUMN,
			CALL_DESCRIPTION_COLUMN };

	/**
     * 
     */
	public ViewPartUniqueMethodsSeeds() {
		super();
	}

	public void setName(){
		super.setPartName("Fan in Seeds - " + model.getId());
	}
	
	public void addMethodToModel(Method method, List<Call_Counted> list,
			ProjectModel projectModel) {

		
		MethodDescription et = new MethodDescription();
		et.setMethod(method);
		((SeedsModel) model).setProjectModel(projectModel);
		setName();
		((SeedsModel) model).addMethodAsASeed(et, method.getId(), createMethodsDescriptions(list));

	}

	private List<CallDescription> createMethodsDescriptions(
			List<Call_Counted> list) {
		List<CallDescription> resultList = new ArrayList<CallDescription>();
		if (list != null){	
			for (Iterator<Call_Counted> iterator = list.iterator() ; iterator.hasNext() ; ){
			
				CallDescription cd = new CallDescription(iterator.next());
				resultList.add(cd);
			
			}
		}
		return resultList;
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
				this.createTableLeft(composite1);
			}
			{

				composite2 = new Composite(sashForm, SWT.NONE);
				FillLayout composite2Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite2.setLayout(composite2Layout);
				composite2.setBounds(0, 0, 77, 81);
				this.createTableRight(composite2);
			}
		}

		createActions();
		createContextMenu();
		hookGlobalActions();

	}
	
	private void createTableLeft(Composite composite1) {

		// Set numColumns to 3 for the buttons
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		composite1.setLayout(layout);

		// Create the table
		createMethodsTable(composite1);

		// Create and setup the TableViewer
		createMethodsTableViewer();
		tableViewerMethod.setContentProvider(new ContentProviderSeedsFanIN());
		tableViewerMethod
				.setLabelProvider(new MethodsDescriptionLabelProvider());

		// The input for the table viewer is the instance of ExampleTaskList
		model = new SeedsModel();
		tableViewerMethod.setInput(model);

	}

	private void createTableRight(Composite composite2){
		
		// Set numColumns to 3 for the buttons
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		composite2.setLayout(layout);

		// Create the table
		createCallTable(composite2);

		// Create and setup the TableViewer
		createCallsTableViewer();
		callsTableViewer.setContentProvider(new ContentProviderCallSeedsFanIN());
		callsTableViewer
				.setLabelProvider(new CallsDescriptionLabelProvider());

		
	}
	
	/**
	 * Create the Methods Table
	 */
	private void createMethodsTable(Composite parent) {

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
	
	private void createCallTable(Composite parent) {

		callsTable = new Table(parent, SWT.BORDER | SWT.MULTI);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		callsTable.setLayoutData(gridData);

		callsTable.setLinesVisible(true);
		callsTable.setHeaderVisible(true);

		{

			//Columna de la imágen
			TableColumn tableCallsColumn0 = new TableColumn(callsTable, SWT.NONE);
			tableCallsColumn0.setText("");
			tableCallsColumn0.setWidth(40);

			
			//Columna del método
			TableColumn tableCallsColumn1 = new TableColumn(callsTable, SWT.NONE);
			tableCallsColumn1.setText("Caller Method");
			tableCallsColumn1.setWidth(300);
			tableCallsColumn1
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterFanInViewCalls) callsTableViewer
									.getSorter()).doSort(0);
							callsTableViewer.refresh();
						}
					});
			//Columna de la descripcion
			TableColumn tableCallsColumn2 = new TableColumn(callsTable, SWT.NONE);
			tableCallsColumn2.setText("Description");
			tableCallsColumn2.setWidth(300);
			tableCallsColumn2
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterFanInViewCalls) callsTableViewer
									.getSorter()).doSort(0);
							callsTableViewer.refresh();
						}
					});
		}


	}
	
	/**
	 * Create the Methods TableViewer
	 */
	private void createMethodsTableViewer() {

		tableViewerMethod = new TableViewer(tableMethod);
		tableViewerMethod.setUseHashlookup(true);

		tableViewerMethod.setColumnProperties(columnNamesMethodsTable);

		// Set the sorter
		ViewerSorter sorter = new SorterMethodDescriptionView();
		tableViewerMethod.setSorter(sorter);
		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNamesMethodsTable.length];
		// Column 1 :
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

	
	private void createCallsTableViewer(){
		
		callsTableViewer = new TableViewer(callsTable);
		callsTableViewer.setUseHashlookup(true);

		callsTableViewer.setColumnProperties(columnNamesCallsTable);

		// Set the sorter
//		ViewerSorter sorter = new SorterMethodDescriptionView();
//		callsTableViewer.setSorter(sorter);
		
		
		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNamesCallsTable.length];
		// Column 0 : Imagen
		String[] s = new String[2];
		s[0] = "yes";
		s[1] = "no";
		editors[0] = new ComboBoxCellEditor(callsTable, s , SWT.READ_ONLY);
		// Column 1 : Call
		editors[1] = null;
		// Column 2 : Description (Free text)
		TextCellEditor textEditor = new TextCellEditor(callsTable);
		editors[2] = textEditor;

		// Assign the cell editors to the viewer
		callsTableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
		callsTableViewer.setCellModifier(new CellModifierCallsDescription(
				this));
		
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

	


	
	protected void selectionItem(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				MethodDescription metodo = (MethodDescription) ((IStructuredSelection) event
						.getSelection()).getFirstElement();
				String key = metodo.getMethod().getId();
				callsTableViewer.setInput(key);

			}

		}

	}

	/**
	 * Return the column names in a collection
	 * 
	 * @return List containing column names
	 */
	public java.util.List getColumnNamesMethods() {
		return Arrays.asList(columnNamesMethodsTable);
	}

	/**
	 * Return the column names in a collection
	 * 
	 * @return List containing column names
	 */
	public java.util.List getColumnNamesCalls() {
		return Arrays.asList(columnNamesCallsTable);
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
	public IResultsModel getModel() {
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
						((SeedsModel) model)
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
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * InnerClass that acts as a proxy for the methodDescriptionListViewer 
     * providing content for the Table. It implements the MethodDescriptionListViewer 
     * interface since it must register changeListeners with the 
     * methodDescriptionListViewer
	 * 
	 *
	 */
	class ContentProviderSeedsFanIN implements IStructuredContentProvider,
			MethodDescriptionListViewer {
		/**
		 * It register itself as a listener to the domain object changes so it can notify the tree viewer of any changes. 
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			if (newInput != null)
				((SeedsModel) newInput).addChangeListenerMethodDescription(this);
			if (oldInput != null)
				((SeedsModel) oldInput).removeChangeListenerMethodDescription(this);
		}

		public void dispose() {
			((SeedsModel) model).removeChangeListenerMethodDescription(this);
		}

		// Return the methodsDescriptions as an array of Objects
		public Object[] getElements(Object parent) {
			return ((SeedsModel) model).getMethodsDescriptions().toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#addTask(ExampleTask)
		 */
		public void addMethodDescription(MethodDescription methodDescription) {
			tableViewerMethod.add(methodDescription);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#removeTask(ExampleTask)
		 */
		public void removeMethodDescription(MethodDescription methodDescription) {
			tableViewerMethod.remove(methodDescription);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#updateTask(ExampleTask)
		 */
		public void updateMethodDEscription(MethodDescription methodDescription) {
			tableViewerMethod.update(methodDescription, null);
		}
	}

	/**
	 * InnerClass that acts as a proxy for the methodDescriptionListViewer 
     * providing content for the Table. It implements the MethodDescriptionListViewer 
     * interface since it must register changeListeners with the 
     * methodDescriptionListViewer
	 * 
	 *
	 */
	class ContentProviderCallSeedsFanIN implements IStructuredContentProvider,
	CallDescriptionListViewer {
		/**
		 * It register itself as a listener to the domain object changes so it can notify the tree viewer of any changes. 
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			if (newInput != null)
				((SeedsModel) model).addChangeListenerCallDescription(this);
			if (oldInput != null)
				((SeedsModel) model).removeChangeListenerCallDescription(this);
		}

		public void dispose() {
			((SeedsModel) model).removeChangeListenerCallDescription(this);
		}

		// Returns the callDescriptions of a given method_id
		public Object[] getElements(Object inputElement) {
			String method_id = (String)inputElement;
			return (((SeedsModel)model).getCallsDescriptions(method_id)).toArray();

		}

		@Override
		public void addCallDescription(CallDescription callDescription) {
			callsTableViewer.add(callDescription);
			
		}

		@Override
		public void removeCallDescription(CallDescription callDescription) {
			callsTableViewer.remove(callDescription);
			
		}

		@Override
		public void updateCallDEscription(CallDescription callDescription) {
			callsTableViewer.update(callDescription, null);
			
		}



	}
	
}
