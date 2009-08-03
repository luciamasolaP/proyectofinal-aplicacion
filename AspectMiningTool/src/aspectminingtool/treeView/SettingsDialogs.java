package aspectminingtool.treeView;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SettingsDialogs extends org.eclipse.swt.widgets.Dialog {

	private Shell dialogShell;
	private CLabel selectLabel;
	private Button cancelButton;
	private Button acceptButton;
	private IJavaProject project;
	private static CheckboxTreeViewer treeViewer1;

	
	public SettingsDialogs(Shell parent, int style, IJavaProject project) {
		super(parent, style);
		this.project = project;
	}

	public void open() {
		try {
			Shell parent = getParent();
			dialogShell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			dialogShell.setText("Filter");

			dialogShell.setLayout(new FormLayout());
			{
				treeViewer1 = new CheckboxTreeViewer(dialogShell, SWT.NONE);
				FormData treeViewer1LData = new FormData();
				treeViewer1LData.width = 298;
				treeViewer1LData.height = 240;
				treeViewer1LData.left =  new FormAttachment(0, 1000, 17);
				treeViewer1LData.top =  new FormAttachment(0, 1000, 39);
				treeViewer1.getControl().setLayoutData(treeViewer1LData);
				treeViewer1.setContentProvider(new ProjectTreeContentProvider(project));
				treeViewer1.setLabelProvider(new ProjectTreeLabelProvider());
				treeViewer1.setInput("root");
			}
			{
				acceptButton = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				acceptButton.setText("Aceptar");
				FormData acceptButtonLData = new FormData(64, 25);
				acceptButtonLData.width = 64;
				acceptButtonLData.height = 25;
				acceptButtonLData.bottom =  new FormAttachment(1000, 1000, -7);
				acceptButtonLData.right =  new FormAttachment(1000, 1000, -84);
				acceptButton.setLayoutData(acceptButtonLData);
				acceptButton.setSize(64, 25);
				acceptButton.setBounds(245, 253, 53, 25);
				acceptButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						acceptButtonWidgetSelected(evt);
					}
				});
			}
			{
				cancelButton = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
				cancelButton.setText("Cancelar");
				FormData cancelButtonLData = new FormData();
				cancelButtonLData.width = 64;
				cancelButtonLData.height = 25;
				cancelButtonLData.bottom =  new FormAttachment(1000, 1000, -7);
				cancelButtonLData.right =  new FormAttachment(1000, 1000, -14);
				cancelButton.setLayoutData(cancelButtonLData);
				cancelButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						cancelButtonWidgetSelected(evt);
					}
				});
			}
			{
				selectLabel = new CLabel(dialogShell, SWT.NONE);
				selectLabel.setText("Seleccione las clases a excluir del análisis:");
				FormData selectLabelLData = new FormData();
				selectLabelLData.width = 222;
				selectLabelLData.height = 21;
				selectLabelLData.left =  new FormAttachment(0, 1000, 12);
				selectLabelLData.top =  new FormAttachment(0, 1000, 12);
				selectLabel.setLayoutData(selectLabelLData);
			}

			treeViewer1.addCheckStateListener(new ICheckStateListener() {
			      public void checkStateChanged(CheckStateChangedEvent event) {

			    	 treeViewer1.setSubtreeChecked(event.getElement(), event.getChecked());
			 		 ProjectTreeContentProvider projectTreeContentProvider = (ProjectTreeContentProvider) treeViewer1.getContentProvider();
			         checkPath(event.getElement(),treeViewer1,projectTreeContentProvider);
			          
			      }
			    });
			
			dialogShell.layout();
			dialogShell.pack();	
			dialogShell.setSize(432, 418);
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
	
	static void checkPath(Object element, CheckboxTreeViewer treeViewer1, ProjectTreeContentProvider projectTreeContentProvider) {
	    

        Object parentElement = projectTreeContentProvider.getParent(element);
        if (parentElement == null) return;
        int index = 0;
        Object[] children = projectTreeContentProvider.getChildren(parentElement);
        boolean check = true;
        while (index < children.length){
       	 if (!treeViewer1.getChecked(children[index])){
       		check = false;
       		break;
       	 }
       	 index++;
        }
        treeViewer1.setChecked(parentElement, check);
        checkPath(parentElement,treeViewer1,projectTreeContentProvider);
        
	}
	
	/**
	 * Fills the Filter with the user selections
	 * @param evt
	 */
	private void acceptButtonWidgetSelected(SelectionEvent evt) {

		Object[] checkedElements = treeViewer1.getCheckedElements();
		for (int i=0 ; i < checkedElements.length ; i++)
			
			System.out.println("elemento: "+ checkedElements[i]);
		
	
		
	}
	/**
	 * Close the Dialog
	 * @param evt
	 */
	private void cancelButtonWidgetSelected(SelectionEvent evt) {
		dialogShell.close();
		
	}

}
