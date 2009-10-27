package aspectminingtool.views.SeedsGeneral;

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
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescriptionListViewer;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedDescription;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedDescriptionListViewer;
import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedsGeneralModel;
import aspectminingtool.model.Call_Counted;
import aspectminingtool.views.AbstractView;
import aspectminingtool.views.SearchInTable;
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
public class ViewPartSeeds extends AbstractView implements ViewSeedsInterface{
	public static final String ID_VIEW = "aspectminingtool.views.SeedsGeneral.ViewPartSeeds"; //$NON-NLS-1$
	
	private TableViewer tableViewerLeft;
	private Table tableLeft;
	private Table tableRight;
	private TableViewer tableViewerRight;
	
	private SashForm sashForm;
	private Composite composite1;
	private Composite composite2;
	private Composite composite3;

	private Button buttonSearch;
	private CLabel labelSearch;
	private Text textSearch;
	
	private SearchInTable searchInTable = new SearchInTable();
	

	private Action openItemActionMethodsTable, openItemActionCallsTable,
			deleteAction, selectAllActionMethodsTable,
			selectAllActionCallsTable;

	// Create a ExampleTaskList and assign it to an instance variable
	//private IResultsModel model = new SeedsGeneralModel();

	// Set the table column property names for tableViewerMethod
	public final String SEED_NAME_COLUMN = "Method";
	public final String SEED_ALGORITH_COLUMN = "Algorithm";
	public final String SEED_DESCRIPTION_COLUMN = "Description";
	

	// Set the table column property names for callsTableViewer
	public final String RMETHOD_SELECTED_COLUMN = "";
	public final String RMETHOD_NAME_COLUMN = "Related Method";
	public final String RMETHOD_DESCRIPTION_COLUMN = "Description";
	
	// Set column names
	private String[] columnNamesMethodsTable = new String[] { SEED_NAME_COLUMN, SEED_ALGORITH_COLUMN,
			SEED_DESCRIPTION_COLUMN};
	
	private String[] columnNamesCallsTable = new String[] { RMETHOD_SELECTED_COLUMN, RMETHOD_NAME_COLUMN,
			RMETHOD_DESCRIPTION_COLUMN };

	private String SECOND_ID = "";
	/**
     * 
     */
	public ViewPartSeeds() {
		super();
	}

	public void setName(String secondId){
		SECOND_ID = secondId;
		super.setPartName("Seeds - " + SECOND_ID);
	}
	
	public void addSeedToModel(Method method, String algorithm, List<RelatedMethodDescription> relatedMethods,
			ProjectModel projectModel) {

		SeedDescription seedDescription = new SeedDescription();
		seedDescription.setMethod(method);
		seedDescription.setAlgoritmo(algorithm);
		((SeedsGeneralModel) model).setProjectModel(projectModel);
		((SeedsGeneralModel) model).addMethodAsASeed(seedDescription, method.getId(), relatedMethods);
		setName(projectModel.getName());
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {

		model = new SeedsGeneralModel();
		
		{
			sashForm = new SashForm(parent, SWT.NONE);
			sashForm.setSize(60, 30);
			{

				composite1 = new Composite(sashForm, SWT.NULL);
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.makeColumnsEqualWidth = true;
				composite1Layout.marginWidth = 0;
				composite1Layout.verticalSpacing = 0;
				composite1Layout.marginHeight = 0;
				composite1.setLayout(composite1Layout);
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
//		GridLayout layout = new GridLayout(3, false);
//		layout.marginWidth = 4;
//		composite1.setLayout(layout);

		// Create the table
		createTableLeftColumns(composite1);

		// Create and setup the TableViewer
		createTableViewerLeft();

	}

	private void createTableRight(Composite composite2){
		
		// Set numColumns to 3 for the buttons
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		composite2.setLayout(layout);

		// Create the table
		createTableRightColumns(composite2);

		// Create and setup the TableViewer
		createCallsTableViewerRight();
				
	}
	
	/**
	 * Create the Methods Table
	 */
	private void createTableLeftColumns(Composite parent) {

		tableLeft = new Table(parent, SWT.BORDER | SWT.MULTI);

		tableLeft.setHeaderVisible(true);
		GridData tableLeftLData = new GridData();
		tableLeftLData.horizontalAlignment = GridData.FILL;
		tableLeftLData.verticalAlignment = GridData.FILL;
		tableLeftLData.grabExcessVerticalSpace = true;
		tableLeftLData.grabExcessHorizontalSpace = true;
		tableLeft.setLayoutData(tableLeftLData);
		tableLeft.setLinesVisible(true);

		TableColumn column = new TableColumn(tableLeft, SWT.CENTER, 0);
		column.setText(SEED_NAME_COLUMN);
		column.setWidth(200);
		// Add listener to column so tasks are sorted by Method when clicked
		column
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterSeedsDescriptionView) tableViewerLeft
						.getSorter()).doSort(0);
				tableViewerLeft.refresh();
			}
		});
		


		// 3er column with algorithmn
		column = new TableColumn(tableLeft, SWT.LEFT, 1);
		column.setText(SEED_ALGORITH_COLUMN);
		column.setWidth(150);
		// Add listener to column so tasks are sorted by description when clicked
		column
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterSeedsDescriptionView) tableViewerLeft
						.getSorter()).doSort(1);
				tableViewerLeft.refresh();
			}
		});
		
		// 2nd column with task Description
		column = new TableColumn(tableLeft, SWT.LEFT, 2);
		column.setText(SEED_DESCRIPTION_COLUMN);
		column.setWidth(600);
		// Add listener to column so tasks are sorted by description when clicked
		column
		.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				((SorterSeedsDescriptionView) tableViewerLeft
						.getSorter()).doSort(2);
				tableViewerLeft.refresh();
			}
		});
		
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
	
	private void createTableRightColumns(Composite parent) {

		tableRight = new Table(parent, SWT.BORDER | SWT.MULTI);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		tableRight.setLayoutData(gridData);

		tableRight.setLinesVisible(true);
		tableRight.setHeaderVisible(true);

		{

			//Columna de la imágen
			TableColumn tableCallsColumn0 = new TableColumn(tableRight, SWT.NONE);
			tableCallsColumn0.setText("");
			tableCallsColumn0.setWidth(40);
		
			//Columna del método
			TableColumn tableCallsColumn1 = new TableColumn(tableRight, SWT.NONE);
			tableCallsColumn1.setText(RMETHOD_NAME_COLUMN);
			tableCallsColumn1.setWidth(300);
			tableCallsColumn1
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterFanInViewCalls) tableViewerRight
									.getSorter()).doSort(0);
							tableViewerRight.refresh();
						}
					});
			//Columna de la descripcion
			TableColumn tableCallsColumn2 = new TableColumn(tableRight, SWT.NONE);
			tableCallsColumn2.setText(RMETHOD_DESCRIPTION_COLUMN);
			tableCallsColumn2.setWidth(300);
			tableCallsColumn2
					.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							((SorterFanInViewCalls) tableViewerRight
									.getSorter()).doSort(0);
							tableViewerRight.refresh();
						}
					});
		}

	}
	
	/**
	 * Create the Methods TableViewer
	 */
	private void createTableViewerLeft() {

		tableViewerLeft = new TableViewer(tableLeft);
		tableViewerLeft.setUseHashlookup(true);

		tableViewerLeft.setColumnProperties(columnNamesMethodsTable);

		// Set the sorter
		ViewerSorter sorter = new SorterSeedsDescriptionView();
		tableViewerLeft.setSorter(sorter);
		
		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNamesMethodsTable.length];
		// Column 1 : nombre del método
		editors[0] = null;
		//Column 2: Algoritmo
		editors[1] = null;
		// Column 3 : Description
		TextCellEditor textEditor = new TextCellEditor(tableLeft);
		editors[2] = textEditor;

		

		// Assign the cell editors to the viewer
		tableViewerLeft.setCellEditors(editors);
		// Set the cell modifier for the viewer
		tableViewerLeft.setCellModifier(new CellModifierSeedDescription(
				this));

		tableViewerLeft
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						selectionItem(event);

					}

				});
		
		tableViewerLeft.addDoubleClickListener(new IDoubleClickListener(){

			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (!event.getSelection().isEmpty()) {
					
					if (event.getSelection() instanceof IStructuredSelection) {	
						SeedDescription methodDescription = (SeedDescription) ((IStructuredSelection) event.getSelection()).getFirstElement();
						openResource(methodDescription.getMethod().getClass_id());
					}
				}
				
			}
			
		});

		tableViewerLeft.setContentProvider(new ContentProviderSeedsDescription());
		tableViewerLeft
				.setLabelProvider(new LabelProviderSeedsDescription());

		// The input for the table viewer is the instance of ExampleTaskList
		model = new SeedsGeneralModel();
		tableViewerLeft.setInput(model);
	}

	
	private void createCallsTableViewerRight(){
		
		tableViewerRight = new TableViewer(tableRight);
		tableViewerRight.setUseHashlookup(true);

		tableViewerRight.setColumnProperties(columnNamesCallsTable);

		// Set the sorter
//		ViewerSorter sorter = new SorterMethodDescriptionView();
//		callsTableViewer.setSorter(sorter);
				
		// Create the cell editors
		CellEditor[] editors = new CellEditor[columnNamesCallsTable.length];
		// Column 0 : Imagen
		String[] s = new String[2];
		s[0] = "yes";
		s[1] = "no";
		editors[0] = new ComboBoxCellEditor(tableRight, s , SWT.READ_ONLY);
		// Column 1 : Call
		editors[1] = null;
		// Column 2 : Description (Free text)
		TextCellEditor textEditor = new TextCellEditor(tableRight);
		editors[2] = textEditor;

		// Assign the cell editors to the viewer
		tableViewerRight.setCellEditors(editors);
		// Set the cell modifier for the viewer
		tableViewerRight.setCellModifier(new CellModifierRelatedMethods(
				this));
		
		tableViewerRight.setContentProvider(new ContentProviderRelatedMethodsSeeds());
		tableViewerRight
				.setLabelProvider(new LabelProviderRelatedMethodsSeeds());
		
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
				SeedDescription metodo = (SeedDescription) ((IStructuredSelection) event
						.getSelection()).getFirstElement();
				String key = metodo.getMethod().getId();
				tableViewerRight.setInput(key);

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
		return tableViewerLeft.getSelection();
	}

	/**
	 * Return the parent composite
	 */
	public Control getControl() {
		return tableLeft.getParent();
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
		Menu menu = menuMgr.createContextMenu(tableViewerLeft.getControl());
		tableViewerLeft.getControl().setMenu(menu);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr, tableViewerLeft);

		// Create menu manager for methodsTableViewer for callsTableViewer
		MenuManager menuMgr1 = new MenuManager();
		menuMgr1.setRemoveAllWhenShown(true);
		menuMgr1.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenuCallsTableViewer(mgr);
			}

		});
		// Create menu for callsTableViewer
		Menu menu1 = menuMgr1.createContextMenu(tableViewerRight.getControl());
		tableViewerRight.getControl().setMenu(menu1);

		// Register menu for extension.
		getSite().registerContextMenu(menuMgr1, tableViewerRight);
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
				IStructuredSelection sel = (IStructuredSelection) tableViewerLeft
						.getSelection();
				Iterator iter = sel.iterator();
				while (iter.hasNext()) {
					SeedDescription methodDescription = (SeedDescription) iter
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
				IStructuredSelection sel = (IStructuredSelection) tableViewerLeft
						.getSelection();
				Iterator iter = sel.iterator();
				while (iter.hasNext()) {
					SeedDescription methodDescription = (SeedDescription) iter
							.next();
					if (methodDescription != null) {
						((SeedsGeneralModel) model)
								.removeMethodDescription(methodDescription);
					}

				}

			}
		};

		selectAllActionMethodsTable = new Action("Select All") {
			public void run() {
				selectAll(tableViewerLeft);
			}
		};

		openItemActionCallsTable = new Action("Open") {
			public void run() {
				IStructuredSelection sel = (IStructuredSelection) tableViewerRight
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
				selectAll(tableViewerRight);
			}
		};

		// Add selection listener.
		tableViewerLeft
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection sel = (IStructuredSelection) tableViewerLeft
								.getSelection();
						openItemActionMethodsTable.setEnabled(sel.size() > 0);
						selectAllActionMethodsTable.setEnabled(sel.size() > 0);
						deleteAction.setEnabled(sel.size() > 0);
					}
				});

		tableViewerRight
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection sel = (IStructuredSelection) tableViewerLeft
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
	class ContentProviderSeedsDescription implements IStructuredContentProvider,
		SeedDescriptionListViewer {
		/**
		 * It register itself as a listener to the domain object changes so it can notify the tree viewer of any changes. 
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			if (newInput != null)
				((SeedsGeneralModel) newInput).addChangeListenerSeedDescription(this);
			if (oldInput != null)
				((SeedsGeneralModel) oldInput).removeChangeListenerSeedDescription(this);
		}

		public void dispose() {
			((SeedsGeneralModel) model).removeChangeListenerSeedDescription(this);
		}

		// Return the methodsDescriptions as an array of Objects
		public Object[] getElements(Object parent) {
			return ((SeedsGeneralModel) model).getSeedDescriptions().toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#addTask(ExampleTask)
		 */
		public void addSeedDescription(SeedDescription methodDescription) {
			tableViewerLeft.add(methodDescription);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#removeTask(ExampleTask)
		 */
		public void removeSeedDescription(SeedDescription methodDescription) {
			tableViewerLeft.remove(methodDescription);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see ITaskListViewer#updateTask(ExampleTask)
		 */
		public void updateSeedDescription(SeedDescription methodDescription) {
			tableViewerLeft.update(methodDescription, null);
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
	class ContentProviderRelatedMethodsSeeds implements IStructuredContentProvider,
	RelatedMethodDescriptionListViewer {
		/**
		 * It register itself as a listener to the domain object changes so it can notify the tree viewer of any changes. 
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			if (newInput != null)
				((SeedsGeneralModel) model).addChangeListenerRelatedMethodDescription(this);
			if (oldInput != null)
				((SeedsGeneralModel) model).removeChangeListenerRelatedMethodDescription(this);
		}

		public void dispose() {
			((SeedsGeneralModel) model).removeChangeListenerRelatedMethodDescription(this);
		}

		// Returns the callDescriptions of a given method_id
		public Object[] getElements(Object inputElement) {
			String method_id = (String)inputElement;
			return (((SeedsGeneralModel)model).getRelatedMethodDescriptions(method_id)).toArray();

		}

		@Override
		public void addRelatedMethodDescription(RelatedMethodDescription relatedMethodDescription) {
			tableViewerRight.add(relatedMethodDescription);
			
		}

		@Override
		public void removeRelatedDescription(RelatedMethodDescription relatedMethodDescription){
			tableViewerRight.remove(relatedMethodDescription);
			
		}

		@Override
		public void updateRelatedMethodDescription(RelatedMethodDescription relatedMethodDescription){
			tableViewerRight.update(relatedMethodDescription, null);
			
		}

	}
	
}
