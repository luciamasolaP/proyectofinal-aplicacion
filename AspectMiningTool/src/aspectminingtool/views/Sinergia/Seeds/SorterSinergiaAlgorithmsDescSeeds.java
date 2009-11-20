package aspectminingtool.views.Sinergia.Seeds;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class SorterSinergiaAlgorithmsDescSeeds extends ViewerSorter {
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

			List<String> l1 = (List<String>)e1;
			List<String> l2 = (List<String>)e2;
	    	String alg1 = l1.get(column);
	    	String alg2 = l2.get(column);
	    	rc = alg1.compareTo(alg2);

		    
		if (direction == DESCENDING)
		      rc = -rc;
	    return rc;
	}

	@Override
	public void sort(Viewer viewer, Object[] elements) {
		super.sort(viewer, elements);
	}

}