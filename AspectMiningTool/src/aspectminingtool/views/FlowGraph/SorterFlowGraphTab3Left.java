package aspectminingtool.views.FlowGraph;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import aspectminingtool.JessIntegrationModel.MetricMethodResult;

public class SorterFlowGraphTab3Left extends ViewerSorter {

	private static final int ASCENDING = 0;
	  private static final int DESCENDING = 1;
	  private int column;
	  private int direction;
	@Override
	public int category(Object element) {
		// TODO Auto-generated method stub
		return super.category(element);
	}
	 public void doSort(int column) {
		    if (column == this.column) {
		      direction = 1 - direction;
		    } else {
		      this.column = column;
		      direction = ASCENDING;
		    }
		  }
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		int rc = 0;
		MetricMethodResult fir1 = (MetricMethodResult)e1;
		MetricMethodResult fir2 = (MetricMethodResult)e2;
	    switch (column) {
	    case 0:
	    	String name1 = fir1.getMetodo().getName();
	    	String name2 = fir2.getMetodo().getName();
	    	rc = name1.compareTo(name2);
	      break;
	    case 1:
	    	int numero1 = Integer.valueOf(fir1.getMetric());
	    	int numero2 = Integer.valueOf(fir2.getMetric());
	    	Integer int1 = new Integer(numero1);
	    	Integer int2 = new Integer(numero2);
	    	rc = int1.compareTo(int2);
		  break;
	    }		    

		if (direction == DESCENDING)
		      rc = -rc;
	    return rc;
	}

	@Override
	public void sort(Viewer viewer, Object[] elements) {
		super.sort(viewer, elements);
	}
	
}
