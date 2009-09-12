/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.views.Seeds;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aspectminingtool.model.Call_Counted;

import JessIntegrationModel.Method;


/**
 * Class that plays the role of the domain model in the TableViewerExample
 * In real life, this class would access a persistent store of some kind.
 * 
 */

public class ModelSeedsFanIn {

	private final int COUNT = 10;
	private List methods = new ArrayList(COUNT);
	private Set changeListeners = new HashSet();
	private Map<String,List<Call_Counted>> calls = new HashMap<String,List<Call_Counted>>();

	// Combo box choices
	static final String[] OWNERS_ARRAY = { "?", "Nancy", "Larry", "Joe" };
	
	/**
	 * Constructor
	 */
	public ModelSeedsFanIn() {
		super();
	//	this.initData();
		
	}
	
	/*
	 * Initialize the table data.
	 * Create COUNT tasks and add them them to the 
	 * collection of tasks
	 */
	private void initData() {
		MethodDescription task;
		for (int i = 0; i < COUNT; i++) {
			task = new MethodDescription("Task "  + i);
			Method m = new Method("metohd1", "metohd1", "metohd1", "metohd1", "metohd1");
			task.setMethod(m);
			methods.add(task);
		}
	};

	/**
	 * Return the array of owners   
	 */
	public String[] getOwners() {
		return OWNERS_ARRAY;
	}
	
	/**
	 * Return the collection of tasks
	 */
	public List getTasks() {
		return methods;
	}
	
	/**
	 * Add a new task to the collection of tasks
	 */
	public void addTask() {
		MethodDescription task = new MethodDescription("New task");
		methods.add(methods.size(), task);
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((ITaskListViewer) iterator.next()).addTask(task);
	}

	public void addMethodAsASeed(MethodDescription et, String methodId,List<Call_Counted> methodsCalls) {
		
		if (!pertenece(et)){
			methods.add(methods.size(), et);
			this.calls.put(methodId, methodsCalls);
			Iterator iterator = changeListeners.iterator();
			while (iterator.hasNext())
				((ITaskListViewer) iterator.next()).addTask(et);
			
		}
		
	}
	
	private boolean pertenece(MethodDescription et) {
		for (Iterator i = methods.iterator(); i.hasNext(); ){
			String id = ((MethodDescription)i.next()).getMethod().getId();
			if (id.equals(et.getMethod().getId()))
				return true;
		}
		return false;
	}

	/**
	 * @param task
	 */
	public void removeTask(MethodDescription task) {
		methods.remove(task);
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((ITaskListViewer) iterator.next()).removeTask(task);
	}

	/**
	 * @param task
	 */
	public void taskChanged(MethodDescription task) {
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((ITaskListViewer) iterator.next()).updateTask(task);
		for (Iterator i= methods.iterator() ; i.hasNext() ;){
			MethodDescription et = (MethodDescription)i.next();

		}
	}

	/**
	 * @param viewer
	 */
	public void removeChangeListener(ITaskListViewer viewer) {
		changeListeners.remove(viewer);
	}

	/**
	 * @param viewer
	 */
	public void addChangeListener(ITaskListViewer viewer) {
		changeListeners.add(viewer);
	}

	public Map<String, List<Call_Counted>> getCalls() {
		return calls;
	}
	
	public List<Call_Counted> getCalls(String methodId){
		
		return calls.get(methodId);
		
	}

	public void setCalls(Map<String, List<Call_Counted>> calls) {
		this.calls = calls;
	}

}
