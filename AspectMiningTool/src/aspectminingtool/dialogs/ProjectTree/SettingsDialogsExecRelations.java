package aspectminingtool.dialogs.ProjectTree;

//import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import aspectminingtool.views.AbstractMultipleFilterView;

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
public class SettingsDialogsExecRelations extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private Button okButton;
	private CTabFolder cTabFolder1;
	private CTabItem cTabItem1;
	private Composite composite6;
	private Composite composite5;
	private Group composite3;
	private Button acceptButton;
	static private CheckboxTreeViewer treeViewerLlamadoras;
	private Group groupLeft;
	private CLabel LabelThreshold2;
	private Group composite4;
	private Group composite2;
	private Group composite1;
	private CLabel labelUmbral;
	private Composite compositeUmbral;
	private Text Umbral;
	private Button filterGetSet;
	private Group groupCallees;
	private Button cancelButton;
	static private CheckboxTreeViewer treeViewerLlamadas;
	private Text textThreshold2;
	private Text textThreshold3;
	private Button buttonGetSet4;
	private Text textThreshold4;
	private CLabel cLabelThreshold4;
	private Composite composite8;
	private Button buttonGetSet3;
	private CLabel cLabelThreshold2;
	private Composite composite7;
	private Button buttonGetSet2;
	private Button CancelButton;
	private CTabItem cTabItem2;
	private AbstractMultipleFilterView viewPart;



	public SettingsDialogsExecRelations(Shell parent, int style) {
		super(parent,style);
	}
	
	public SettingsDialogsExecRelations(Shell parent, int style, AbstractMultipleFilterView viewPart) {
		super(parent, style);
		this.viewPart = viewPart;
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
				composite1.setText("Before Execution Configuration");
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
						labelUmbral.setText("Threshold");
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
					filterGetSet.setText("Filter out Getters and Setters");
					filterGetSet.setSelection(true);
				}
			}
			{
				GridData composite2LData = new GridData();
				composite2LData.horizontalAlignment = GridData.FILL;
				composite2LData.grabExcessHorizontalSpace = true;
				composite2 = new Group(dialogShell, SWT.NONE);
				composite2.setText("After Execution Configuration");
				GridLayout composite2Layout = new GridLayout();
				composite2Layout.numColumns = 2;
				composite2.setLayout(composite2Layout);
				composite2.setLayoutData(composite2LData);
				{
					GridData composite6LData = new GridData();
					composite6 = new Composite(composite2, SWT.NONE);
					GridLayout composite6Layout = new GridLayout();
					composite6Layout.makeColumnsEqualWidth = true;
					composite6Layout.numColumns = 2;
					composite6.setLayout(composite6Layout);
					composite6.setLayoutData(composite6LData);
					{
						LabelThreshold2 = new CLabel(composite6, SWT.NONE);
						GridData LabelThreshold2LData = new GridData();
						LabelThreshold2.setLayoutData(LabelThreshold2LData);
						LabelThreshold2.setText("Threshold");
					}
					{
						textThreshold2 = new Text(composite6, SWT.BORDER);
						GridData textThreshold2LData = new GridData();
						textThreshold2LData.heightHint = 15;
						textThreshold2LData.widthHint = 24;
						textThreshold2.setLayoutData(textThreshold2LData);
						textThreshold2.setText("1");
					}
				}
				{
					buttonGetSet2 = new Button(composite2, SWT.CHECK | SWT.LEFT);
					buttonGetSet2.setSelection(true);
					GridData buttonGetSet2LData = new GridData();
					buttonGetSet2.setLayoutData(buttonGetSet2LData);
					buttonGetSet2.setText("Filter out Getters and Setters");
				}
			}
			{
				GridData composite4LData = new GridData();
				composite4LData.horizontalAlignment = GridData.FILL;
				composite4LData.grabExcessHorizontalSpace = true;
				composite4 = new Group(dialogShell, SWT.NONE);
				composite4.setText("Inside First Configuration");
				GridLayout composite4Layout = new GridLayout();
				composite4Layout.numColumns = 2;
				composite4.setLayout(composite4Layout);
				composite4.setLayoutData(composite4LData);
				{
					GridData composite7LData = new GridData();
					composite7 = new Composite(composite4, SWT.NONE);
					GridLayout composite7Layout = new GridLayout();
					composite7Layout.makeColumnsEqualWidth = true;
					composite7Layout.numColumns = 2;
					composite7.setLayout(composite7Layout);
					composite7.setLayoutData(composite7LData);
					{
						cLabelThreshold2 = new CLabel(composite7, SWT.NONE);
						GridData cLabelThreshold2LData = new GridData();
						cLabelThreshold2.setLayoutData(cLabelThreshold2LData);
						cLabelThreshold2.setText("Threshold");
					}
					{
						textThreshold3 = new Text(composite7, SWT.BORDER);
						GridData textThreshold3LData = new GridData();
						textThreshold3LData.heightHint = 15;
						textThreshold3LData.widthHint = 24;
						textThreshold3.setLayoutData(textThreshold3LData);
						textThreshold3.setText("1");
					}
				}
				{
					buttonGetSet3 = new Button(composite4, SWT.CHECK | SWT.LEFT);
					buttonGetSet3.setSelection(true);
					GridData buttonGetSet3LData = new GridData();
					buttonGetSet3.setLayoutData(buttonGetSet3LData);
					buttonGetSet3.setText("Filter out Getters and Setters");
				}
			}
			{
				GridData composite3LData = new GridData();
				composite3LData.horizontalAlignment = GridData.FILL;
				composite3LData.grabExcessHorizontalSpace = true;
				composite3LData.grabExcessVerticalSpace = true;
				composite3 = new Group(dialogShell, SWT.NONE);
				composite3.setText("Inside Last Configuration");
				GridLayout composite3Layout = new GridLayout();
				composite3Layout.numColumns = 2;
				composite3.setLayout(composite3Layout);
				composite3.setLayoutData(composite3LData);
				{
					GridData composite8LData = new GridData();
					composite8 = new Composite(composite3, SWT.NONE);
					GridLayout composite8Layout = new GridLayout();
					composite8Layout.makeColumnsEqualWidth = true;
					composite8Layout.numColumns = 2;
					composite8.setLayout(composite8Layout);
					composite8.setLayoutData(composite8LData);
					{
						cLabelThreshold4 = new CLabel(composite8, SWT.NONE);
						GridData cLabelThreshold4LData = new GridData();
						cLabelThreshold4.setLayoutData(cLabelThreshold4LData);
						cLabelThreshold4.setText("Threshold");
					}
					{
						textThreshold4 = new Text(composite8, SWT.BORDER);
						GridData textThreshold4LData = new GridData();
						textThreshold4LData.heightHint = 15;
						textThreshold4LData.widthHint = 24;
						textThreshold4.setLayoutData(textThreshold4LData);
						textThreshold4.setText("1");
					}
				}
				{
					buttonGetSet4 = new Button(composite3, SWT.CHECK | SWT.LEFT);
					buttonGetSet4.setSelection(true);
					GridData buttonGetSet4LData = new GridData();
					buttonGetSet4.setLayoutData(buttonGetSet4LData);
					buttonGetSet4.setText("Filter out Getters and Setters");
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
								((AbstractMultipleFilterView)viewPart).setUmbralFilter(Umbral.getText());
								((AbstractMultipleFilterView)viewPart).setGetterSetterFilter(filterGetSet.getSelection());
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
