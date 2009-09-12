package aspectminingtool.views.FanIn;


import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import aspectminingtool.model.Call_Counted;


public class SorterFanInViewCalls extends ViewerSorter {
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
		Call_Counted cc1 = (Call_Counted)e1;
		Call_Counted cc2 = (Call_Counted)e2;
		switch (column) {
	    case 0:
	    	String name1 = cc1.getCaller_id();
	    	String name2 = cc2.getCaller_id();
	    	rc = name1.compareTo(name2);
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