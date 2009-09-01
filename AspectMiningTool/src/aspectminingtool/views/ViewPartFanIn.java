package aspectminingtool.views;

import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import JessIntegrationModel.IResultsModel;

public class ViewPartFanIn extends ViewPart {
    public static final String ID_VIEW =
        "aspectminingtool.views.ViewPartFanIn"; //$NON-NLS-1$
    
    private TableTreeViewer ttv;

    Composite composite1;

	private IResultsModel model;
    
    /**
     * 
     */
    public ViewPartFanIn() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        composite1 = new Composite(parent, SWT.NULL);
        composite1.setLayout(new GridLayout(4, false));
        
        this.createContents();
    }

    /* (non-Javadoc)
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
    
    public void setModel(IResultsModel model){
//    	actualizar la vista
    	this.model = model; 
    	super.setPartName("Fan in Results - "+ model.getId());
    	ttv.setInput(model);
    }
    
    public IResultsModel getModel() {
		return model;
	}

	public void createContents(){
    	
    	ttv = new TableTreeViewer(composite1);
        ttv.getTableTree().setLayoutData(new GridData(GridData.FILL_BOTH));

        // Set the content and label providers
        ttv.setContentProvider(new FanInContentProvider());
        ttv.setLabelProvider(new FanInLabelProvider());
      //  ttv.setInput(new PlayerTableModel());

        // Set up the table
        Table table = ttv.getTableTree().getTable();
        new TableColumn(table, SWT.LEFT).setText("Method");
        new TableColumn(table, SWT.LEFT).setText("Fan in");
        

        // Expand everything
        ttv.expandAll();

        // Pack the columns
        for (int i = 0, n = table.getColumnCount(); i < n; i++) {
          table.getColumn(i).pack();
        }

        // Turn on the header and the lines
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Pack the window
        composite1.pack();

        // Scroll to top
//        ttv.reveal(ttv.getElementAt(0));

      //  return ttv.getTableTree();
    	
    }

	
    
}
