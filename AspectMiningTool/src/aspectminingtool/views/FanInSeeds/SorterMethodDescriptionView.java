package aspectminingtool.views.FanInSeeds;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import aspectminingtool.JessIntegrationModel.Seeds.MethodDescription;


public class SorterMethodDescriptionView extends ViewerSorter {

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
			MethodDescription fir1 = (MethodDescription)e1;
			MethodDescription fir2 = (MethodDescription)e2;
		    switch (column) {
		    case 0:
		    	String name1 = fir1.getMethod().getName();
		    	String name2 = fir2.getMethod().getName();
		    	rc = name1.compareTo(name2);
		      break;
		    case 1:
		    	String desc1 =fir1.getDescription();
		    	String desc2 =fir2.getDescription();
		    	rc = desc1.compareTo(desc2);
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
