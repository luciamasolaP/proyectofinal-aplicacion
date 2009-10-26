package aspectminingtool.views.SeedsGeneral;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import aspectminingtool.JessIntegrationModel.GeneralSeeds.SeedDescription;


public class SorterSeedsDescriptionView extends ViewerSorter {

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
			SeedDescription seedDesc1 = (SeedDescription)e1;
			SeedDescription seedDesc2 = (SeedDescription)e2;
		    switch (column) {
		    case 0:
		    	String name1 = seedDesc1.getMethod().getName();
		    	String name2 = seedDesc2.getMethod().getName();
		    	rc = name1.compareTo(name2);
		      break;
		    case 1:
		    	String alg1 =seedDesc1.getAlgoritmo();
		    	String alg2 =seedDesc2.getAlgoritmo();
		    	rc = alg1.compareTo(alg2);
			  break;
		    case 2:
		    	String desc1 =seedDesc1.getDescription();
		    	String desc2 =seedDesc2.getDescription();
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
