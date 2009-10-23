package aspectminingtool.views.RedirectorFinder;


import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;

public class SorterRedFinderClasses extends ViewerSorter {
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
		RedirectorFinderResults rfr1 = (RedirectorFinderResults)e1;
		RedirectorFinderResults rfr2 = (RedirectorFinderResults)e2;
		    switch (column) {
		    case 0:
		    	String name1 = rfr1.getClaseLlamadora();
		    	String name2 = rfr2.getClaseLlamadora();
		    	rc = name1.compareTo(name2);
		      break;
		    case 1:
		    	String name3 = rfr1.getClaseLlamada();
		    	String name4 = rfr2.getClaseLlamada();
		    	rc = name3.compareTo(name4);
			  break;
		    case 2:
		    	int numero1 =Integer.parseInt(rfr1.getCantidadRedireccionados());
		    	int numero2 =Integer.parseInt(rfr2.getCantidadRedireccionados());
		    	Integer int1 = new Integer(numero1);
		    	Integer int2 = new Integer(numero2);
		    	rc = int1.compareTo(int2);
		      break;
		    case 3:
		    	Double d1 = rfr1.getPercent();
		    	Double d2 = rfr2.getPercent();
		    	rc = d1.compareTo(d2);
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