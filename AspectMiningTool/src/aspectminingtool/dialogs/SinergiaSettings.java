package aspectminingtool.dialogs;

//import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
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
public class SinergiaSettings extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;

	
	private Group composite1;
	private Composite composite111;
	private Composite composite112;
	private CLabel LabelUmbralFanIn;
	private Text TextUmbralFanIn;
	private CLabel LabelConfianzaFanIn;
	private Text TextConfianzaFanIn;
	
	private Group composite9;
	private Composite composite991;
	private Composite composite992;
	private CLabel LabelUmbralUniqueM;
	private Text TextUmbralUniqueM;
	private Text TextConfianzaUniqueM;
	private CLabel LabelConfianzaUniqueM;
	
	private Group composite6;
	private Composite composite661;
	private Composite composite662;
	private CLabel LabelUmbralFlowGraph;
	private Text TextUmbralFlowGraph;
	private CLabel LabelConfianzaFlowGraph;
	private Text TextConfianzaFlowGraph;
	
	private Group composite0;
	private Composite composite001;

	private CLabel LabelUmbralSinergia;
	private Text TextUmbralSinergia;

	

	
	private Composite composite5;
	private Button okButton;
	private Button CancelButton;

	//private IJavaProject project;

	public SinergiaSettings(Shell parent, int style) {
		super(parent,style);
	}
	
	public SinergiaSettings(Shell parent, int style, IJavaProject project) {
		super(parent, style);
	//	this.project = project;
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
			dialogShell.setText("Sinergia Settings");
			dialogShell.setDragDetect(false);
			dialogShell.setSize(398, 355);
			{
				composite1 = new Group(dialogShell, SWT.NONE);
				composite1.setText("Fan-In Analysis");
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.numColumns = 2;
				composite1Layout.marginLeft = 60;
				composite1Layout.makeColumnsEqualWidth = true;
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
					composite111 = new Composite(composite1, SWT.NONE);
					GridLayout compositeUmbralLayout = new GridLayout();
					compositeUmbralLayout.makeColumnsEqualWidth = true;
					compositeUmbralLayout.numColumns = 2;
					composite111.setLayout(compositeUmbralLayout);
					composite111.setLayoutData(compositeUmbralLData);					
					
					{
						LabelUmbralFanIn = new CLabel(composite111, SWT.NONE);
						GridData labelUmbralLData = new GridData();
						labelUmbralLData.horizontalIndent = -5;
						labelUmbralLData.widthHint = 65;
						labelUmbralLData.heightHint = 21;
						LabelUmbralFanIn.setLayoutData(labelUmbralLData);
						LabelUmbralFanIn.setText("Threshold");
					}
					{
						TextUmbralFanIn = new Text(composite111, SWT.BORDER);
						GridData UmbralLData = new GridData();
						UmbralLData.widthHint = 24;
						UmbralLData.heightHint = 15;
						TextUmbralFanIn.setLayoutData(UmbralLData);
						TextUmbralFanIn.setText("0");
					}
				}
				{
					GridData compositeUmbral2LData = new GridData();
					compositeUmbral2LData.horizontalAlignment = GridData.FILL;
					compositeUmbral2LData.heightHint = 31;
					composite112 = new Composite(composite1, SWT.NONE);
					GridLayout compositeUmbral2Layout = new GridLayout();
					compositeUmbral2Layout.makeColumnsEqualWidth = true;
					compositeUmbral2Layout.numColumns = 2;
					composite112.setLayout(compositeUmbral2Layout);
					composite112.setLayoutData(compositeUmbral2LData);
					{
						LabelConfianzaFanIn = new CLabel(composite112, SWT.NONE);
						GridData labelConfianzaLData = new GridData();
						labelConfianzaLData.horizontalIndent = -5;
						labelConfianzaLData.widthHint = 40;
						labelConfianzaLData.heightHint = 21;
						LabelConfianzaFanIn.setLayoutData(labelConfianzaLData);
						LabelConfianzaFanIn.setText("Trust");
					}
					{
						TextConfianzaFanIn = new Text(composite112, SWT.BORDER);
						GridData UmbralLData = new GridData();
						UmbralLData.widthHint = 24;
						UmbralLData.heightHint = 15;
						TextConfianzaFanIn.setLayoutData(UmbralLData);
						TextConfianzaFanIn.setText("0");
					}
				}

			}
			
			composite9 = new Group(dialogShell, SWT.NONE);
			composite9.setText("Unique Methods Analysis");
			GridLayout composite9Layout = new GridLayout();
			composite9Layout.numColumns = 2;
			composite9Layout.marginLeft = 60;
			GridData composite9LData = new GridData();
			composite9LData.horizontalAlignment = GridData.FILL;
			composite9LData.grabExcessHorizontalSpace = true;
			composite9LData.heightHint = 37;
			composite9.setLayoutData(composite9LData);
			composite9.setLayout(composite9Layout);
			{
				GridData composite2LData = new GridData();
				composite991 = new Composite(composite9, SWT.NONE);
				GridLayout composite2Layout = new GridLayout();
				composite2Layout.makeColumnsEqualWidth = true;
				composite2Layout.numColumns = 2;
				composite991.setLayout(composite2Layout);
				composite991.setLayoutData(composite2LData);
			}

 					{
						LabelUmbralUniqueM = new CLabel(composite991, SWT.NONE);
						GridData LabelUmbralUniqueMLData = new GridData();
						LabelUmbralUniqueMLData.horizontalIndent = -5;
						LabelUmbralUniqueMLData.widthHint = 65;
						LabelUmbralUniqueMLData.heightHint = 21;
						LabelUmbralUniqueM.setLayoutData(LabelUmbralUniqueMLData);
						LabelUmbralUniqueM.setText("Threshold");
					}
					{
						TextUmbralUniqueM = new Text(composite991, SWT.BORDER);
						GridData TextUmbralUniqueMLData = new GridData();
						TextUmbralUniqueMLData.widthHint = 24;
						TextUmbralUniqueMLData.heightHint = 15;
						TextUmbralUniqueM.setLayoutData(TextUmbralUniqueMLData);
						TextUmbralUniqueM.setText("0");
					}
					
				{
					GridData composite12LData = new GridData();
					composite992 = new Composite(composite9, SWT.NONE);
					GridLayout composite12Layout = new GridLayout();
					composite12Layout.makeColumnsEqualWidth = true;
					composite12Layout.numColumns = 2;
					composite992.setLayout(composite12Layout);
					composite992.setLayoutData(composite12LData);
				}

		 					{
								LabelConfianzaUniqueM = new CLabel(composite992, SWT.NONE);
								GridData LabelConfianzaUniqueMLData = new GridData();
								LabelConfianzaUniqueMLData.horizontalIndent = -5;
								LabelConfianzaUniqueMLData.widthHint = 40;
								LabelConfianzaUniqueMLData.heightHint = 21;
								LabelConfianzaUniqueM.setLayoutData(LabelConfianzaUniqueMLData);
								LabelConfianzaUniqueM.setText("Trust");
							}
							{
								TextConfianzaUniqueM = new Text(composite992, SWT.BORDER);
								GridData TextConfianzaUniqueMLData = new GridData();
								TextConfianzaUniqueMLData.widthHint = 24;
								TextConfianzaUniqueMLData.heightHint = 15;
								TextConfianzaUniqueM.setLayoutData(TextConfianzaUniqueMLData);
								TextConfianzaUniqueM.setText("0");
							}
					
			
			{
				composite6 = new Group(dialogShell, SWT.NONE);
				composite6.setText("Flow Graph Analysis");
				GridLayout composite6Layout = new GridLayout();
				composite6Layout.makeColumnsEqualWidth = true;
				composite6Layout.marginLeft = 60;
				composite6Layout.numColumns = 2;
				GridData composite6LData = new GridData();
				composite6LData.horizontalAlignment = GridData.FILL;
				composite6LData.grabExcessHorizontalSpace = true;
				composite6.setLayoutData(composite6LData);
				composite6.setLayout(composite6Layout);
				
 {
				GridData composite3LData = new GridData();
				composite661 = new Composite(composite6, SWT.NONE);
				GridLayout composite3Layout = new GridLayout();
				composite3Layout.makeColumnsEqualWidth = true;
				composite3Layout.numColumns = 2;
				composite661.setLayout(composite3Layout);
				composite661.setLayoutData(composite3LData);
			}

 					{
						LabelUmbralFlowGraph = new CLabel(composite661, SWT.NONE);
						GridData LabelUmbralFlowGraphLData = new GridData();
						LabelUmbralFlowGraphLData.horizontalIndent = -5;
						LabelUmbralFlowGraphLData.widthHint = 65;
						LabelUmbralFlowGraphLData.heightHint = 21;
						LabelUmbralFlowGraph.setLayoutData(LabelUmbralFlowGraphLData);
						LabelUmbralFlowGraph.setText("Threshold");
					}
					{
						
						TextUmbralFlowGraph = new Text(composite661, SWT.BORDER);
						GridData TextUmbralFlowGraphLData = new GridData();
						TextUmbralFlowGraphLData.widthHint = 24;
						TextUmbralFlowGraphLData.heightHint = 15;
						TextUmbralFlowGraph.setLayoutData(TextUmbralFlowGraphLData);
						TextUmbralFlowGraph.setText("0");
					}

				{
					GridData composite13LData = new GridData();
					composite662 = new Composite(composite6, SWT.NONE);
					GridLayout composite3Layout = new GridLayout();
					composite3Layout.makeColumnsEqualWidth = true;
					composite3Layout.numColumns = 2;
					composite662.setLayout(composite3Layout);
					composite662.setLayoutData(composite13LData);
				}

	 					{
							LabelConfianzaFlowGraph = new CLabel(composite662, SWT.NONE);
							GridData LabelConfianzaFlowGraphLData = new GridData();
							LabelConfianzaFlowGraphLData.horizontalIndent = -5;
							LabelConfianzaFlowGraphLData.widthHint = 40;
							LabelConfianzaFlowGraphLData.heightHint = 21;
							LabelConfianzaFlowGraph.setLayoutData(LabelConfianzaFlowGraphLData);
							LabelConfianzaFlowGraph.setText("Trust");
						}
						{
							
							TextConfianzaFlowGraph = new Text(composite662, SWT.BORDER);
							GridData TextUmbralFlowGraphLData = new GridData();
							TextUmbralFlowGraphLData.widthHint = 24;
							TextUmbralFlowGraphLData.heightHint = 15;
							TextConfianzaFlowGraph.setLayoutData(TextUmbralFlowGraphLData);
							TextConfianzaFlowGraph.setText("0");
						}
								
				
				composite0 = new Group(dialogShell, SWT.NONE);
				composite0.setText("Sinergia Analysis");
				GridLayout composite11Layout = new GridLayout();
				composite11Layout.makeColumnsEqualWidth = true;
				composite11Layout.marginLeft = 60;
				composite11Layout.numColumns = 2;
				GridData composite11LData = new GridData();
				composite11LData.horizontalAlignment = GridData.FILL;
				composite11LData.grabExcessHorizontalSpace = true;
				composite0.setLayoutData(composite11LData);
				composite0.setLayout(composite11Layout);
				{
					GridData composite7LData = new GridData();
					composite001 = new Composite(composite0, SWT.NONE);
					GridLayout composite7Layout = new GridLayout();
					composite001.setLayout(composite7Layout);
					composite7Layout.makeColumnsEqualWidth = true;
					composite7Layout.numColumns = 2;
					composite001.setLayoutData(composite7LData);
					
					{
						LabelUmbralSinergia = new CLabel(composite001, SWT.NONE);
						GridData LabelUmbralSinergiaLData = new GridData();
						LabelUmbralSinergiaLData.horizontalIndent = -5;
						LabelUmbralSinergiaLData.widthHint = 65;
						LabelUmbralSinergiaLData.heightHint = 21;
						LabelUmbralSinergia.setLayoutData(LabelUmbralSinergiaLData);
						LabelUmbralSinergia.setText("Threshold");
					}
					{
						
						TextUmbralSinergia = new Text(composite001, SWT.BORDER);
						GridData TextUmbralSinergiaLData = new GridData();
						TextUmbralSinergiaLData.widthHint = 24;
						TextUmbralSinergiaLData.heightHint = 15;
						TextUmbralSinergia.setLayoutData(TextUmbralSinergiaLData);
						TextUmbralSinergia.setText("0");
					}

					
					
				}

			}
			{
				composite5 = new Composite(dialogShell, SWT.NONE);
				GridLayout composite5Layout = new GridLayout();
				composite5Layout.numColumns = 2;
				GridData composite5LData = new GridData();
				composite5LData.horizontalAlignment = GridData.FILL;
				composite5LData.grabExcessHorizontalSpace = true;
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
