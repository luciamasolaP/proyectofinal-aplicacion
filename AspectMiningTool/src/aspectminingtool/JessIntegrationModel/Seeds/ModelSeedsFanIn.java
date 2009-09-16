/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.JessIntegrationModel.Seeds;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.views.Seeds.CallDescriptionListViewer;
import aspectminingtool.views.Seeds.MethodDescriptionListViewer;


/**
 * Class that plays the role of the domain model in the TableViewerExample
 * In real life, this class would access a persistent store of some kind.
 * 
 */

public class ModelSeedsFanIn implements IResultsModel{

	private List methodsDescriptionList = new ArrayList();
	private List changeListenersMethodDescription = new ArrayList();
	
	private Map<String,List<CallDescription>> callDescriptionList = new HashMap<String,List<CallDescription>>();
	private List changeListenersCallsDescription = new ArrayList();
	
	ProjectModel projectModel = null;

	
	/**
	 * Constructor
	 */
	public ModelSeedsFanIn() {
		super();
	//	this.initData();
		
	}
	

	/**
	 * Return the collection of methodDescriptions
	 */
	public List getMethodsDescriptions() {
		return methodsDescriptionList;
	}
	
	/**
	 * Return the collection of methodDescriptions
	 */
	public List<CallDescription> getCallsDescriptions(String method_id) {
		return callDescriptionList.get(method_id);
	}
	
	/**
	 * Add a new task to the collection of methodsDescriptions and tells all the listener to add the new methodDescription
	 */
	public void addTask() {
		MethodDescription methodDescription = new MethodDescription();
		methodsDescriptionList.add(methodsDescriptionList.size(), methodDescription);
		Iterator iterator = changeListenersMethodDescription.iterator();
		while (iterator.hasNext())
			((MethodDescriptionListViewer) iterator.next()).addMethodDescription(methodDescription);
	}

	public void addMethodAsASeed(MethodDescription et, String methodId,List<CallDescription> methodsCalls) {
		
		if (!pertenece(et)){
			methodsDescriptionList.add(methodsDescriptionList.size(), et);
			if (methodsCalls != null)
				this.callDescriptionList.put(methodId, methodsCalls);
			Iterator iterator = changeListenersMethodDescription.iterator();
			while (iterator.hasNext())
				((MethodDescriptionListViewer) iterator.next()).addMethodDescription(et);
			
		}
		
	}
	
	private boolean pertenece(MethodDescription et) {
		for (Iterator i = methodsDescriptionList.iterator(); i.hasNext(); ){
			String id = ((MethodDescription)i.next()).getMethod().getId();
			if (id.equals(et.getMethod().getId()))
				return true;
		}
		return false;
	}

	/**
	 * @param methodDescription
	 */
	public void removeMethodDescription(MethodDescription methodDescription) {
		methodsDescriptionList.remove(methodDescription);
		callDescriptionList.remove(methodDescription.getMethod().getId());
		Iterator iterator = changeListenersMethodDescription.iterator();
		while (iterator.hasNext())
			((MethodDescriptionListViewer) iterator.next()).removeMethodDescription(methodDescription);
		//debería iterar sobre los listener de las calls para avisar al modelo de removerlo
	}

	/**
	 * @param methodDescription
	 */
	public void methodDescriptionChanged(MethodDescription methodDescription) {
		Iterator iterator = changeListenersMethodDescription.iterator();
		while (iterator.hasNext())
			((MethodDescriptionListViewer) iterator.next()).updateMethodDEscription(methodDescription);
//		for (Iterator i= methodsDescriptionList.iterator() ; i.hasNext() ;){
//			MethodDescription et = (MethodDescription)i.next();
//
//		}
	}

	/**
	 * @param callDescription
	 */
	public void CallDescriptionChanged(CallDescription callDescription) {
		Iterator iterator = changeListenersCallsDescription.iterator();
		while (iterator.hasNext())
			((CallDescriptionListViewer) iterator.next()).updateCallDEscription(callDescription);
//		for (Iterator i= callDescription.iterator() ; i.hasNext() ;){
//			CallDescription et = (CallDescription)i.next();
//
//		}
	}
	

	/**
	 * @param viewer
	 */
	public void removeChangeListenerMethodDescription(MethodDescriptionListViewer viewer) {
		changeListenersMethodDescription.remove(viewer);
	}

	/**
	 * @param viewer
	 */
	public void addChangeListenerMethodDescription(MethodDescriptionListViewer viewer) {
		changeListenersMethodDescription.add(viewer);
	}

	
	/**
	 * It removes a listener of the type CallDescriptionListViewer
	 * @param viewer
	 */
	public void removeChangeListenerCallDescription(CallDescriptionListViewer viewer) {
		changeListenersCallsDescription.remove(viewer);
	}

	/**
	 * It adds a listener of the type CallDescriptionListViewer
	 * @param viewer
	 */
	public void addChangeListenerCallDescription(CallDescriptionListViewer viewer) {
		changeListenersCallsDescription.add(viewer);
	}
	
	
	public Map<String, List<CallDescription>> getCalls() {
		return callDescriptionList;
	}
	
	public List<CallDescription> getCalls(String methodId){
		
		return callDescriptionList.get(methodId);
		
	}

	public void setCalls(Map<String, List<CallDescription>> calls) {
		this.callDescriptionList = calls;
	}

	@Override
	public String getId() {
		return projectModel.getName();
	}

	@Override
	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}


	@Override
	public void generateArchive(BufferedWriter archive) {
		// TODO Auto-generated method stub
		
	}
	

}
