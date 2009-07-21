package aspectminingtool.treeView;


import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;





public class SettingsDialogs extends Dialog {
	
	private IJavaProject project;
	
	public SettingsDialogs(Shell parentShell, IJavaProject javaProject) {
		super(parentShell);
		this.project = javaProject; 
	}

	protected void configureShell(Shell shell) {
	    super.configureShell(shell);
	    // Set the title bar text and the size
	    shell.setText("File Tree");
	    shell.setSize(500,500);
	   	 
	  }
	
	
	protected Control createDialogArea(Composite parent) {
		
			Composite composite = (Composite)super.createDialogArea(parent);
//			Composite composite = new Composite(parent, SWT.BORDER);
		    composite.setLayout(new GridLayout(1,false));
		    
		    Composite composite1 = new Composite(composite,SWT.BORDER);
		    composite1.setLayout(new GridLayout(1,false));
		    composite1.setLocation(200, 200);
		    Button boton1 = new Button(composite1,SWT.BORDER_DASH);
		    boton1.setText("Botón 1");
		    

		    // Create the tree viewer to display the file tree
		   // final TreeViewer tv = new TreeViewer(composite);
		    final CheckboxTreeViewer tv = new CheckboxTreeViewer(composite, SWT.BORDER );
		    tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		    tv.setContentProvider(new ProjectTreeContentProvider(project));
		    tv.setLabelProvider(new ProjectTreeLabelProvider());
		    tv.setInput("root"); // pass a non-null that will be ignored

		    // When user checks a checkbox in the tree, check all its children
		    tv.addCheckStateListener(new ICheckStateListener() {
		      public void checkStateChanged(CheckStateChangedEvent event) {

		          // . . . checks or unchekcs all its children
		          tv.setSubtreeChecked(event.getElement(), event.getChecked());
		 		 ProjectTreeContentProvider projectTreeContentProvider = (ProjectTreeContentProvider) tv.getContentProvider();
		          checkPath(event.getElement(),tv,projectTreeContentProvider);
		          
		      }
		    });
		 
		    
		    return composite;
	   }
	
	static void checkPath(Object element, CheckboxTreeViewer tv, ProjectTreeContentProvider projectTreeContentProvider) {
	    

         Object parentElement = projectTreeContentProvider.getParent(element);
         if (parentElement == null) return;
         int index = 0;
         Object[] children = projectTreeContentProvider.getChildren(parentElement);
         boolean check = true;
         while (index < children.length){
        	 if (!tv.getChecked(children[index])){
        		check = false;
        		break;
        	 }
        	 index++;
         }
         tv.setChecked(parentElement, check);
         checkPath(parentElement,tv,projectTreeContentProvider);
         
	}



	

}
