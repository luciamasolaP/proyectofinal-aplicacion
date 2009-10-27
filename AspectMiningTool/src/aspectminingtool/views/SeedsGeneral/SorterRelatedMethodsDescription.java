package aspectminingtool.views.SeedsGeneral;


import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.RelatedMethodDescription;
import aspectminingtool.util.MethodFormater;


public class SorterRelatedMethodsDescription extends ViewerSorter {
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
		RelatedMethodDescription rmd1 = (RelatedMethodDescription)e1;
		RelatedMethodDescription rmd2 = (RelatedMethodDescription)e2;
		String l1 = "";
		String l2 = "";
		switch (column) {
	    case 0:
	    	l1 = rmd1.getSelected();
	    	l2 = rmd2.getSelected();
	      break;
	    case 1:
	    	 l1 = MethodFormater.formatMethodIdToString(rmd1.getRelatedMethod());
	    	 l2 = MethodFormater.formatMethodIdToString(rmd2.getRelatedMethod());
	    	break;
	    case 2:
	    	l1 = rmd1.getDescription();
	    	l2 = rmd2.getDescription();
	    	break;
	    
	    }
		rc = l1.compareTo(l2);   
		if (direction == DESCENDING)
		      rc = -rc;
	    return rc;
	}

	@Override
	public void sort(Viewer viewer, Object[] elements) {
		super.sort(viewer, elements);
	}

}