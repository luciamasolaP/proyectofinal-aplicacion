package aspectminingtool.views.FlowGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import aspectminingtool.JessIntegrationModel.FlowGraph.FlowGraphModel;
import JessIntegrationModel.Method;

import aspectminingtool.model.Call_Counted;

public class FlowGraphContentProviderOBCalls implements IStructuredContentProvider {

	private List<Method> calls = null;
	
//	@Override
//	public Object[] getChildren(Object parentElement) {
//		
//		if (parentElement instanceof Fan_in_Result){
//			String key = ((Fan_in_Result)parentElement).getMetodo().getId();
//			ArrayList<Call_Counted> hijos = (ArrayList<Call_Counted>) fanInModel.getCalls().get(key);
//			if (hijos != null)
//				return hijos.toArray();
//			
//		}
//		
//		return null;
//	}
//
//	@Override
//	public Object getParent(Object element) {
//		
//		if (element instanceof Call_Counted){ 
//			String cc = ((Call_Counted)element).getCalle_id();
//			for (Iterator<Fan_in_Result> i = fanInModel.getResultadoFanIn().iterator(); i.hasNext() ;){
//				Fan_in_Result parent = i.next(); 
//				if (parent.getMetodo().getId().equals(cc))
//						return parent;
//			}
//		}
//		
//		return null;
//	}
//
//	@Override
//	public boolean hasChildren(Object element) {
//		Object[] obj = getChildren(element);
//
//	    // Return whether the parent has children
//	    return obj == null ? false : obj.length > 0;
//	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		calls = (List<Method>)inputElement;
		return calls.toArray(); 	

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

}
