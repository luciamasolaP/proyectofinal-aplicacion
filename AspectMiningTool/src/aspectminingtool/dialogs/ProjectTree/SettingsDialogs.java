package aspectminingtool.dialogs.ProjectTree;

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

import aspectminingtool.views.AbstractView;
import aspectminingtool.views.FanIn.ViewPartFanIn;

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
	private Composite composite6;
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
	private AbstractView viewPartFanIn;

	private IJavaProject project;

	public SettingsDialogs(Shell parent, int style) {
		super(parent,style);
	}
	
	public SettingsDialogs(Shell parent, int style, IJavaProject project, AbstractView viewPartFanIn) {
		super(parent, style);
		this.project = project;
		this.viewPartFanIn = viewPartFanIn;
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
				composite1.setText("Configuración");
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
						Umbral.setText("1");
					}
				}
				{
					filterGetSet = new Button(composite1, SWT.CHECK | SWT.LEFT);
					GridData filterGetSetLData = new GridData();
					filterGetSet.setLayoutData(filterGetSetLData);
					filterGetSet.setText("Filtrar métodos Getters y Setters");
					filterGetSet.setSelection(true);
				}
			}
				{
					composite5 = new Composite(dialogShell, SWT.NONE);
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
								//leer seleccion de árbol
								//leer los checkboxs
								//mandar a hacer filtrados
								((ViewPartFanIn)viewPartFanIn).setUmbralFilter(Umbral.getText());
								((ViewPartFanIn)viewPartFanIn).setGetterSetterFilter(filterGetSet.getSelection());
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

			dialogShell.layout();
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
	
}
