package aspectminingtool.dialogs;

//import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;

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
	private CLabel labelLlamadores;
	private Button FilterGetersSetter;
	private Label Umbral;
	private Text umbralText;
	private Composite composite6;
	private Composite composite5;
	private SashForm sashForm1;
	private Composite composite4;
	private Composite composite3;
	private Button acceptButton;
	static private CheckboxTreeViewer treeViewerLlamadoras;
	private Composite composite1;
	private Button cancelButton;
	static private CheckboxTreeViewer treeViewerLlamadas;
	private CLabel labelLlamados;
	private Composite composite2;
	private Button CancelButton;
	private CTabItem cTabItem2;

	private IJavaProject project;

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
			
			FillLayout dialogShellLayout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
			dialogShell.setLayout(dialogShellLayout);
			dialogShell.setText("Results Filters");
			dialogShell.setDragDetect(false);
			dialogShell.setSize(625, 368);
			{
				composite6 = new Composite(dialogShell, SWT.NONE);
				GridLayout composite6Layout = new GridLayout();
				composite6Layout.makeColumnsEqualWidth = true;
				composite6.setLayout(composite6Layout);
				{
					composite3 = new Composite(composite6, SWT.NONE);
					FillLayout composite3Layout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
					GridData composite3LData = new GridData();
					composite3LData.heightHint = 255;
					composite3LData.horizontalAlignment = GridData.FILL;
					composite3LData.grabExcessHorizontalSpace = true;
					composite3.setLayoutData(composite3LData);
					composite3.setLayout(composite3Layout);
					{
						cTabFolder1 = new CTabFolder(composite3, SWT.NONE);
						{
							cTabItem1 = new CTabItem(cTabFolder1, SWT.NONE);
							cTabItem1.setText("cTabItem1");
							
							{
								sashForm1 = new SashForm(cTabFolder1, SWT.NONE);
								GridLayout sashForm1Layout = new GridLayout();
								sashForm1Layout.makeColumnsEqualWidth = true;
								sashForm1.setLayout(sashForm1Layout);
								sashForm1.setSize(350, 180);
								cTabItem1.setControl(sashForm1);
								{
									composite1 = new Composite(sashForm1, SWT.NONE);
									GridLayout composite1Layout = new GridLayout();
									composite1Layout.makeColumnsEqualWidth = true;
									composite1.setLayout(composite1Layout);
									GridData composite1LData = new GridData();
									composite1.setLayoutData(composite1LData);
									//composite1.setBackground(SWTResourceManager.getColor(255, 255, 255));
									{
										labelLlamadores = new CLabel(composite1, SWT.NONE);
										labelLlamadores
										.setText("Seleccione las clases llamadoras a excluir del análisis:");
									//	labelLlamadores.setBackground(SWTResourceManager.getColor(255, 255, 255));
										

										GridData treeViewer1LData = new GridData();
										treeViewer1LData.horizontalAlignment = GridData.FILL;
										treeViewer1LData.grabExcessVerticalSpace = true;
										treeViewer1LData.verticalAlignment = GridData.FILL;
										treeViewer1LData.grabExcessHorizontalSpace = true;
										treeViewerLlamadoras = new CheckboxTreeViewer(composite1, SWT.NONE);
										treeViewerLlamadoras.getControl().setLayoutData(treeViewer1LData);
										
										treeViewerLlamadoras
										.setContentProvider(new ProjectTreeContentProvider(
												project));
										treeViewerLlamadoras
										.setLabelProvider(new ProjectTreeLabelProvider());
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
									
								}
								{
									composite4 = new Composite(sashForm1, SWT.NONE);
									FormLayout composite4Layout = new FormLayout();
									GridData composite4LData = new GridData();
									composite4.setLayoutData(composite4LData);
									composite4.setLayout(composite4Layout);
									{
										Umbral = new Label(composite4, SWT.NONE);
										FormData UmbralLData = new FormData();
										UmbralLData.width = 61;
										UmbralLData.height = 15;
										UmbralLData.left =  new FormAttachment(0, 1000, 66);
										UmbralLData.top =  new FormAttachment(0, 1000, 87);
										Umbral.setLayoutData(UmbralLData);
										Umbral.setText("Umbral");
									}
								//	composite4.setBackground(SWTResourceManager.getColor(255, 255, 255));
									{
										umbralText = new Text(composite4, SWT.NONE);
										FormData UmbralLData = new FormData();
										UmbralLData.width = 39;
										UmbralLData.height = 15;
										UmbralLData.left =  new FormAttachment(0, 1000, 154);
										UmbralLData.top =  new FormAttachment(0, 1000, 87);
										umbralText.setLayoutData(UmbralLData);
										umbralText.setText("5");
									}
									{
										FilterGetersSetter = new Button(composite4, SWT.CHECK | SWT.LEFT);
										FormData FilterGetersSetterLData = new FormData();
										FilterGetersSetterLData.width = 157;
										FilterGetersSetterLData.height = 32;
										FilterGetersSetterLData.right =  new FormAttachment(755, 1000, 0);
										FilterGetersSetterLData.bottom =  new FormAttachment(326, 1000, 0);
										FilterGetersSetterLData.left =  new FormAttachment(0, 1000, 66);
										FilterGetersSetterLData.top =  new FormAttachment(0, 1000, 43);
										FilterGetersSetter.setLayoutData(FilterGetersSetterLData);
										FilterGetersSetter.setText("Filter Geters and Setter");
										FilterGetersSetter.setBounds(66, 43, 157, 32);
										//FilterGetersSetter.setBackground(SWTResourceManager.getColor(255, 255, 255));
									}
								}
							}
							
						}
						{
							cTabItem2 = new CTabItem(cTabFolder1, SWT.NONE);
							cTabItem2.setText("cTabItem2");
							{
								composite2 = new Composite(cTabFolder1, SWT.NONE);
								GridLayout composite2Layout = new GridLayout();
								composite2Layout.makeColumnsEqualWidth = true;
								composite2.setLayout(composite2Layout);
								cTabItem2.setControl(composite2);
							//	composite2.setBackground(SWTResourceManager.getColor(255, 255, 255));

								{
									labelLlamados = new CLabel(composite2, SWT.NONE);
									GridData cLabel1LData = new GridData();
									labelLlamados.setLayoutData(cLabel1LData);
									labelLlamados
									.setText("Seleccione las clases a ser llamadas a excluir del análisis:");
									//labelLlamados.setBackground(SWTResourceManager.getColor(255, 255, 255));
								}
								{
									GridData checkboxTreeViewer1LData = new GridData();
									checkboxTreeViewer1LData.verticalAlignment = GridData.FILL;
									checkboxTreeViewer1LData.horizontalAlignment = GridData.FILL;
									checkboxTreeViewer1LData.grabExcessHorizontalSpace = true;
									checkboxTreeViewer1LData.grabExcessVerticalSpace = true;
									treeViewerLlamadas = new CheckboxTreeViewer(
											composite2, SWT.NONE);
									treeViewerLlamadas.getControl().setLayoutData(checkboxTreeViewer1LData);
									
									treeViewerLlamadas
									.setContentProvider(new ProjectTreeContentProvider(
											project));
									treeViewerLlamadas
									.setLabelProvider(new ProjectTreeLabelProvider());
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
					FormLayout composite5Layout = new FormLayout();
					GridData composite5LData = new GridData();
					composite5LData.heightHint = 57;
					composite5LData.horizontalAlignment = GridData.FILL;
					composite5LData.grabExcessHorizontalSpace = true;
					composite5.setLayoutData(composite5LData);
					composite5.setLayout(composite5Layout);
					{
						CancelButton = new Button(composite5, SWT.PUSH | SWT.CENTER);
						FormData cancelButtonLData = new FormData();
						cancelButtonLData.width = 80;
						cancelButtonLData.height = 29;
						cancelButtonLData.left =  new FormAttachment(0, 1000, 508);
						cancelButtonLData.right =  new FormAttachment(980, 1000, 0);
						cancelButtonLData.top =  new FormAttachment(289, 1000, 0);
						cancelButtonLData.bottom =  new FormAttachment(798, 1000, 0);
						CancelButton.setLayoutData(cancelButtonLData);
						CancelButton.setText("Cancelar");
						CancelButton.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent evt) {
								System.out.println("okButton.widgetSelected, event=" + evt);
								// TODO add your code for okButton.widgetSelected
								dialogShell.dispose();
							}
						});
					}
					{
						okButton = new Button(composite5, SWT.PUSH | SWT.CENTER);
						FormData okButtonLData = new FormData();
						okButtonLData.width = 55;
						okButtonLData.height = 29;
						okButtonLData.left =  new FormAttachment(735, 1000, 0);
						okButtonLData.right =  new FormAttachment(1000, 1000, -104);
						okButtonLData.top =  new FormAttachment(289, 1000, 0);
						okButtonLData.bottom =  new FormAttachment(798, 1000, 0);
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
				}
			}

			dialogShell.layout();
			dialogShell.pack();
			dialogShell.setLocation(getParent().toDisplay(100, 100));
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
