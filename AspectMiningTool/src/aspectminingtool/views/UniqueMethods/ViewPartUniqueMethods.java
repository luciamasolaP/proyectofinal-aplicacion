package aspectminingtool.views.UniqueMethods;


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
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
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
import JessIntegrationModel.ProjectModel;
import aspectminingtool.JessIntegrationModel.UniqueMethods.Call_Counted;
import aspectminingtool.JessIntegrationModel.UniqueMethods.UniqueMethodsModel;
import aspectminingtool.JessIntegrationModel.UniqueMethods.UniqueMethods_Result;
import aspectminingtool.views.ViewFilterProject;


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
public class ViewPartUniqueMethods extends ViewPart implements ViewFilterProject {
	public static final String ID_VIEW = "aspectminingtool.views.UniqueMethods.ViewPartUniqueMethods"; //$NON-NLS-1$
	private SashForm sashForm1;
	private TableColumn tableCallsColumn1;
	private Table callsTable;
	private TableViewer callsTableViewer;
	private Composite composite2;
	private TableViewer methodsTableViewer;
	private Table methodsTable = null;
	private Action openItemActionMethodsTable, openItemActionCallsTable, selectAsSeedAction, selectAllActionMethodsTable, selectAllActionCallsTable;

	private IResultsModel model;
	Composite composite1;

	/**
     * 
     */
	public ViewPartUniqueMethods() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		FillLayout parentLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
		parent.setLayout(parentLayout);
		{
			sashForm1 = new SashForm(parent, SWT.NONE);
			sashForm1.setSize(60, 30);
			{
				composite1 = new Composite(sashForm1, SWT.NULL);
				FillLayout composite1Layout = new FillLayout(
						org.eclipse.swt.SWT.HORIZONTAL);
				composite1.setLayout(composite1Layout);
				composite1.setBounds(-483, -25, 461, 81);
				
				methodsTable = new Table(composite1, SWT.BORDER | SWT.MULTI);
				methodsTableViewer = new TableViewer(methodsTable);

				// Set the sorter
				ViewerSorter sorter = new SorterMethodsUniqueMethodsView();
				methodsTableViewer.setSorter(sorter);

				// Set the content and label providers
				methodsTableViewer
						.setContentProvider(new UniqueMethodsContentProvider());
				methodsTableViewer.setLabelProvider(new UniqueMethodsLabelProvider());

				// Set up the table, each column has a listener for the click
				// that calls
				// the sorter and refreshes the tree.
				// Column 1
				final TableColumn tc1 = new TableColumn(methodsTable, SWT.LEFT);
				tc1.setText("Method");
				tc1.setWidth(498);
				tc1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterMethodsUniqueMethodsView) methodsTableViewer
										.getSorter()).doSort(0);
								methodsTableViewer.refresh();
							}
						});

				// Column 2
				TableColumn tc2 = new TableColumn(methodsTable, SWT.LEFT);
				tc2.setText("Unique Methods");
				tc2.setWidth(50);
				tc2
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterMethodsUniqueMethodsView) methodsTableViewer
										.getSorter()).doSort(1);
								methodsTableViewer.refresh();
							}
						});

				// Turn on the header and the lines
				methodsTable.setHeaderVisible(true);
				methodsTable.setLinesVisible(true);

				methodsTableViewer
						.addSelectionChangedListener(new ISelectionChangedListener() {
							public void selectionChanged(
									SelectionChangedEvent event) {
								selectionItem(event);

							}

						});
				
				methodsTableViewer.addDoubleClickListener(new IDoubleClickListener(){

					@Override
					public void doubleClick(DoubleClickEvent event) {
						if (!event.getSelection().isEmpty()) {
							
							if (event.getSelection() instanceof IStructuredSelection) {
								
								UniqueMethods_Result UniqueMethodResult = (UniqueMethods_Result) ((IStructuredSelection) event.getSelection()).getFirstElement();
								openResource(UniqueMethodResult.getMetodo().getClass_id());
							}
						}
						
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
					callsTable = new Table(composite2, SWT.LEFT | SWT.MULTI);
					callsTableViewer = new TableViewer(callsTable);
					
					// Set the sorter
					ViewerSorter sorterCalls = new SorterCallsUniqueMethodsView();
					callsTableViewer.setSorter(sorterCalls);
					
					// Set the content and label providers
					callsTableViewer.setContentProvider(new CallsContentProviderUniqueMethods());
					callsTableViewer.setLabelProvider(new CallsLabelProviderUniqueMethods());
					
					{
						tableCallsColumn1 = new TableColumn(callsTable,
								SWT.NONE);
						tableCallsColumn1.setText("Calls");
						tableCallsColumn1.setWidth(150);
						tableCallsColumn1
						.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
							public void widgetSelected(SelectionEvent event) {
								((SorterCallsUniqueMethodsView) callsTableViewer
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
									openResource(name);
								}
							}
							
						}
						
					});


				}
			}
		}
		
		createActions();
		createContextMenu();
		hookGlobalActions();


	}

	private void hookGlobalActions() {
		IActionBars bars = getViewSite().getActionBars();
		bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllActionMethodsTable);
	
	}

	/*
	 * (non-Javadoc)
	 * 
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
		super.setPartName("Unique Methods Results - " + model.getId());
		methodsTableViewer.setInput(model);
	}

	public IResultsModel getModel() {
		return model;
	}


	private void selectionItem(SelectionChangedEvent event) {

		if (!event.getSelection().isEmpty()) {

			if (event.getSelection() instanceof IStructuredSelection) {
				UniqueMethods_Result metodo = (UniqueMethods_Result) ((IStructuredSelection) event.getSelection()).getFirstElement();
				String key = metodo.getMetodo().getId();
				List<Call_Counted> llamadas = ((UniqueMethodsModel) model).getCalls().get(key);
				callsTableViewer.setInput(llamadas);

			}

		}

	}
	
	private void openResource(String resourceName) {
		
			ProjectModel projectModel = model.getProjectModel();
			IResource resource = projectModel.getAssociatedResource(resourceName);
			if (resource != null){
				IFile fileStore = ResourcesPlugin.getWorkspace().getRoot().getFile(resource.getFullPath());
	            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	            try {
					IDE.openEditor(page, fileStore,true);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
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
         Menu menu = menuMgr.createContextMenu(methodsTableViewer.getControl());
         methodsTableViewer.getControl().setMenu(menu);
         
         // Register menu for extension.
         getSite().registerContextMenu(menuMgr, methodsTableViewer);
         
         
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

	protected void fillContextMenuCallsTableViewer(IMenuManager mgr) {
		mgr.add(openItemActionCallsTable);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(new Separator());
		mgr.add(selectAllActionCallsTable);
		
	}

	protected void fillContextMenuMethodsTableViewer(IMenuManager mgr) {
		mgr.add(openItemActionMethodsTable);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(selectAsSeedAction);
		mgr.add(new Separator());
		mgr.add(selectAllActionMethodsTable);
		
	}
	
	/**
	 * Create the actions.
	 */
	public void createActions() {
		openItemActionMethodsTable = new Action("Open") {
			public void run() { 
				IStructuredSelection sel = (IStructuredSelection)methodsTableViewer.getSelection();
				Iterator iter = sel.iterator();
				while (iter.hasNext()) {
					UniqueMethods_Result uniqueMethodsResult = (UniqueMethods_Result) iter.next();
					String id = uniqueMethodsResult.getMetodo().getClass_id();
					openResource(id);

			}
			}
		};
		
		selectAsSeedAction = new Action("Select As a Seed") {
			public void run() {
				selectAsSeedOperation();
			}
		};

		selectAllActionMethodsTable = new Action("Select All") {
			public void run() {
				selectAll(methodsTableViewer);
			}
		};
		
		openItemActionCallsTable = new Action("Open") {
			public void run() { 
				IStructuredSelection sel = (IStructuredSelection)callsTableViewer.getSelection();
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
		methodsTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)methodsTableViewer.getSelection();
				openItemActionMethodsTable.setEnabled(sel.size() > 0);
				selectAllActionMethodsTable.setEnabled(sel.size() > 0);
				selectAsSeedAction.setEnabled(sel.size() > 0);
			}
		});
		
		callsTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)methodsTableViewer.getSelection();
				selectAllActionCallsTable.setEnabled(sel.size() > 0);
				openItemActionCallsTable.setEnabled(sel.size() > 0);
			}
		});
		
		
	}
	
	protected void selectAll(TableViewer tableViewer) {
		tableViewer.getTable().selectAll();
		
	}

	protected void selectAsSeedOperation() {

		System.out.println("selected as a seed");
		
	}
	
}
