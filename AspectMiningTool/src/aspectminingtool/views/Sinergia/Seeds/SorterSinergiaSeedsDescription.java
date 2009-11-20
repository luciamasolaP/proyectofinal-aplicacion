package aspectminingtool.views.Sinergia.Seeds;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class SorterSinergiaSeedsDescription extends ViewerSorter {
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
		SeedDescription cc1 = (SeedDescription)e1;
		SeedDescription cc2 = (SeedDescription)e2;
		switch (column) {
	    case 0:
	    	String method = cc1.getSearchData();
	    	String method2 = cc2.getSearchData();
	    	rc = method.compareTo(method2);
    		break;
	    case 1:
	    	Double trust1 = Double.valueOf(cc1.getTrust());
	    	Double trust2 = Double.valueOf(cc2.getTrust());
	    	rc = trust1.compareTo(trust2);
	    	break;
	    case 2:
	    	String desc1 = cc1.getDescription();
	    	String desc2 = cc2.getDescription();
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