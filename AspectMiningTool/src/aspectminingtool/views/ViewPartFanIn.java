package aspectminingtool.views;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.swt.widgets.Table;

import aspectminingtool.JessIntegrationModel.FanIn.FanInModel;
import aspectminingtool.JessIntegrationModel.FanIn.Fan_in_Result;

import JessIntegrationModel.IResultsModel;


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
public class ViewPartFanIn extends ViewPart {
    public static final String ID_VIEW =
        "aspectminingtool.views.ViewPartFanIn"; //$NON-NLS-1$

   
    private TreeViewer ttv;
    private Tree tree = null; 
    private IResultsModel model;
    Composite composite1;
   
	
    
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
        FillLayout composite1Layout = new FillLayout(org.eclipse.swt.SWT.HORIZONTAL);
        composite1.setLayout(composite1Layout);
        tree =  new Tree(composite1, SWT.BORDER);
        
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
    	
    	ttv = new TreeViewer(tree);
      //  ttv.getTableTree().setLayoutData(new GridData(GridData.FILL_BOTH));

        ViewerSorter sorter = null;
        
        
        // Set the content and label providers
        ttv.setContentProvider(new FanInContentProvider());
        ttv.setLabelProvider(new FanInLabelProvider());
      
        // Set up the table
                
        final TreeColumn tc1 = new TreeColumn(tree, SWT.LEFT);
        tc1.setText("Method");
        tc1.setWidth(498);
        
               
//        tc1.addSelectionListener(new SelectionAdapter() {
//          	
//            public void widgetSelected(SelectionEvent e) {
//            	ttv.setSorter(new ColumnSorter(ColumnSorter.METHODS));
//                 }
//            });       
//        
        
        
        TreeColumn tc2 =  new TreeColumn(tree, SWT.LEFT);
        tc2.setText("Fan in");
        tc2.setWidth(50);

       

        // Pack the columns
        for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
        //  tree.getColumn(i).pack();
        }

        // Turn on the header and the lines
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);

        // Pack the window
     //   composite1.pack();
        


    	
    }

	
    
        }

