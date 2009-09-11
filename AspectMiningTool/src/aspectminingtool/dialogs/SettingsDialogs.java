package aspectminingtool.dialogs;

//import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

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
public class SettingsDialogs extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Button okButton;
	private CTabFolder cTabFolder1;
	private CTabItem cTabItem1;
	private Group composite6;
	private Composite composite5;
	private Composite composite3;
	private Button acceptButton;
	static private CheckboxTreeViewer treeViewerLlamadoras;
	private Group groupLeft;
	private Group composite1;
	private CLabel labelUmbral;
	private Composite compositeUmbral;
	private Text Umbral;
	private Button filterGetSet;
	private Group groupCallees;
	private Button cancelButton;
	static private CheckboxTreeViewer treeViewerLlamadas;
	private Button CancelButton;
	private CTabItem cTabItem2;

	private IJavaProject project;

	public SettingsDialogs(Shell parent, int style) {
		super(parent,style);
	}
	
	public SettingsDialogs(Shell parent, int style, IJavaProject project) {
		super(parent, style);
		this.project = project;
	}

	public void open() {

		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM
					| SWT.APPLICATION_MODAL);

			{
				//Register as a resource user - SWTResourceManager will
				//handle the obtaining and disposing of resources
			//	SWTResourceManager.registerResourceUser(dialogShell);
			}
			
			GridLayout dialogShellLayout = new GridLayout();
			dialogShellLayout.makeColumnsEqualWidth = true;
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.setText("Results Filters");
			dialogShell.setDragDetect(false);
			{
				composite1 = new Group(dialogShell, SWT.NONE);
				composite1.setText("Configuracin");
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.numColumns = 2;
				GridData composite1LData = new GridData();
				composite1LData.horizontalAlignment = GridData.FILL;
				composite1LData.grabExcessHorizontalSpace = true;
				composite1LData.heightHint = 37;
				composite1.setLayoutData(composite1LData);
				composite1.setLayout(composite1Layout);
				{
					GridData compositeUmbralLData = new GridData();
					compositeUmbralLData.horizontalAlignment = GridData.FILL;
					compositeUmbralLData.heightHint = 31;
					compositeUmbral = new Composite(composite1, SWT.NONE);
					GridLayout compositeUmbralLayout = new GridLayout();
					compositeUmbralLayout.makeColumnsEqualWidth = true;
					compositeUmbralLayout.numColumns = 2;
					compositeUmbral.setLayout(compositeUmbralLayout);
					compositeUmbral.setLayoutData(compositeUmbralLData);
					{
						labelUmbral = new CLabel(compositeUmbral, SWT.NONE);
						GridData labelUmbralLData = new GridData();
						labelUmbralLData.horizontalIndent = -5;
						labelUmbralLData.widthHint = 47;
						labelUmbralLData.heightHint = 21;
						labelUmbral.setLayoutData(labelUmbralLData);
						labelUmbral.setText("Umbral");
					}
					{
						Umbral = new Text(compositeUmbral, SWT.BORDER);
						GridData UmbralLData = new GridData();
						UmbralLData.widthHint = 24;
						UmbralLData.heightHint = 15;
						Umbral.setLayoutData(UmbralLData);
						Umbral.setText("0");
					}
				}
				{
					filterGetSet = new Button(composite1, SWT.CHECK | SWT.LEFT);
					GridData filterGetSetLData = new GridData();
					filterGetSet.setLayoutData(filterGetSetLData);
					filterGetSet.setText("Filtrar métodos Getters y Setters");
				}
			}
			{
				composite6 = new Group(dialogShell, SWT.NONE);
				composite6.setText("Filtrado");
				GridLayout composite6Layout = new GridLayout();
				composite6Layout.makeColumnsEqualWidth = true;
				GridData composite6LData = new GridData();
				composite6LData.horizontalAlignment = GridData.FILL;
				composite6LData.grabExcessHorizontalSpace = true;
				composite6.setLayoutData(composite6LData);
				composite6.setLayout(composite6Layout);
				{
					composite3 = new Composite(composite6, SWT.NONE);
					FillLayout composite3Layout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
					GridData composite3LData = new GridData();
					composite3LData.horizontalAlignment = GridData.FILL;
					composite3LData.grabExcessHorizontalSpace = true;
					composite3LData.verticalAlignment = GridData.BEGINNING;
					composite3LData.grabExcessVerticalSpace = true;
					composite3LData.heightHint = 318;
					composite3.setLayoutData(composite3LData);
					composite3.setLayout(composite3Layout);
					{
						cTabFolder1 = new CTabFolder(composite3, SWT.NONE);
						{
							cTabItem1 = new CTabItem(cTabFolder1, SWT.NONE);
							cTabItem1.setText("Filter Callers");
							{
								groupLeft = new Group(cTabFolder1, SWT.NONE);
								groupLeft.setText("Clases Llamadoras a Excluir");
								
								{
									
									treeViewerLlamadoras = new CheckboxTreeViewer(groupLeft, SWT.NONE | SWT.BORDER);

									treeViewerLlamadoras
									.setContentProvider(new ProjectTreeContentProvider(
											project));
									treeViewerLlamadoras
									.setLabelProvider(new ProjectTreeLabelProvider());
									GridData treeViewerLlamadorasLData = new GridData();
									treeViewerLlamadorasLData.horizontalAlignment = GridData.FILL;
									treeViewerLlamadorasLData.grabExcessHorizontalSpace = true;
									treeViewerLlamadorasLData.verticalAlignment = GridData.FILL;
									treeViewerLlamadorasLData.grabExcessVerticalSpace = true;
									treeViewerLlamadoras.getControl().setLayoutData(treeViewerLlamadorasLData);
									treeViewerLlamadoras.setInput("root");
									treeViewerLlamadoras
									.addCheckStateListener(new ICheckStateListener() {
										
										@Override
										public void checkStateChanged(
												CheckStateChangedEvent event) {
											
											treeViewerLlamadoras
											.setSubtreeChecked(
													event
													.getElement(),
													event
													.getChecked());
											ProjectTreeContentProvider projectTreeContentProvider = (ProjectTreeContentProvider) treeViewerLlamadoras
											.getContentProvider();
											checkPath(event.getElement(),
													treeViewerLlamadoras,
													projectTreeContentProvider);
											
										}
										
									});
									
								}
								cTabItem1.setControl(groupLeft);
								GridLayout composite1Layout = new GridLayout();
								composite1Layout.makeColumnsEqualWidth = true;
								groupLeft.setLayout(composite1Layout);
								//composite1.setBackground(SWTResourceManager.getColor(255, 255, 255));

							}
							
							
						}
						{
							cTabItem2 = new CTabItem(cTabFolder1, SWT.NONE);
							cTabItem2.setText("Filters Callees");
							{
								groupCallees = new Group(cTabFolder1, SWT.NONE);
								GridLayout groupCalleesLayout = new GridLayout();
								groupCalleesLayout.makeColumnsEqualWidth = true;
								groupCallees.setLayout(groupCalleesLayout);
								cTabItem2.setControl(groupCallees);
								groupCallees.setText("Clases Llamadas a Excluir:");
								{
									treeViewerLlamadas = new CheckboxTreeViewer(groupCallees, SWT.NONE | SWT.BORDER);

									treeViewerLlamadas
									.setContentProvider(new ProjectTreeContentProvider(
											project));
									treeViewerLlamadas
									.setLabelProvider(new ProjectTreeLabelProvider());
									GridData treeViewerLlamadasLData = new GridData();
									treeViewerLlamadasLData.horizontalAlignment = GridData.FILL;
									treeViewerLlamadasLData.grabExcessHorizontalSpace = true;
									treeViewerLlamadasLData.verticalAlignment = GridData.FILL;
									treeViewerLlamadasLData.grabExcessVerticalSpace = true;
									treeViewerLlamadas.getControl().setLayoutData(treeViewerLlamadasLData);
									treeViewerLlamadas.setInput("root");
									treeViewerLlamadas
									.addCheckStateListener(new ICheckStateListener() {
										
										@Override
										public void checkStateChanged(
												CheckStateChangedEvent event) {
											
											treeViewerLlamadas
											.setSubtreeChecked(event
													.getElement(),
													event.getChecked());
											ProjectTreeContentProvider projectTreeContentProvider = (ProjectTreeContentProvider) treeViewerLlamadas
											.getContentProvider();
											checkPath(event.getElement(),
													treeViewerLlamadas,
													projectTreeContentProvider);
											
										}
										
									});
								}
							}
						}
						cTabFolder1.setSelection(0);
					}
				}
				{
					composite5 = new Composite(composite6, SWT.NONE);
					GridLayout composite5Layout = new GridLayout();
					composite5Layout.numColumns = 2;
					GridData composite5LData = new GridData();
					composite5LData.heightHint = 57;
					composite5LData.horizontalAlignment = GridData.FILL;
					composite5LData.verticalAlignment = GridData.END;
					composite5LData.grabExcessVerticalSpace = true;
					composite5.setLayoutData(composite5LData);
					composite5.setLayout(composite5Layout);
					{
						okButton = new Button(composite5, SWT.PUSH | SWT.CENTER);
						GridData okButtonLData = new GridData();
						okButtonLData.horizontalAlignment = GridData.END;
						okButtonLData.grabExcessHorizontalSpace = true;
						okButtonLData.widthHint = 60;
						okButtonLData.heightHint = 33;
						okButton.setLayoutData(okButtonLData);
						okButton.setText("OK");
						okButton.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent evt) {
								System.out.println("okButton.widgetSelected, event="
										+ evt);
								//leer seleccion de árbol
								//leer los checkboxs
								//mandar a hacer filtrados
								dialogShell.dispose();
							}
						});
					}
					{
						CancelButton = new Button(composite5, SWT.PUSH | SWT.CENTER);
						GridData cancelButtonLData = new GridData();
						cancelButtonLData.horizontalAlignment = GridData.END;
						cancelButtonLData.widthHint = 60;
						cancelButtonLData.heightHint = 33;
						CancelButton.setLayoutData(cancelButtonLData);
						CancelButton.setText("Cancelar");
						CancelButton.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent evt) {
								System.out.println("okButton.widgetSelected, event=" + evt);
								dialogShell.dispose();
							}
						});
					}
				}
			}

			dialogShell.layout();
			dialogShell.setSize(490,500);
			dialogShell.pack();
		//	dialogShell.setLocation(getParent().toDisplay(100, 100));
			dialogShell.open();
			Display display = dialogShell.getDisplay();
			while (!dialogShell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void checkPath(
			Object element,
			CheckboxTreeViewer treeViewerLlamadoras2,
			ProjectTreeContentProvider projectTreeContentProvider) {

		Object parentElement = projectTreeContentProvider.getParent(element);
		if (parentElement == null)
			return;
		int index = 0;
		Object[] children = projectTreeContentProvider
				.getChildren(parentElement);
		boolean check = true;
		while (index < children.length) {
			if (!treeViewerLlamadoras2.getChecked(children[index])) {
				check = false;
				break;
			}
			index++;
		}
		treeViewerLlamadoras2.setChecked(parentElement, check);
		checkPath(parentElement, treeViewerLlamadoras2,
				projectTreeContentProvider);

	}

}
