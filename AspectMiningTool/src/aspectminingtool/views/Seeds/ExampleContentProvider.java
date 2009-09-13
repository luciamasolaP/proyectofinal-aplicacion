package aspectminingtool.views.Seeds;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class ExampleContentProvider implements IStructuredContentProvider, MethodDesccriptionListViewer {
	
	private ModelSeedsFanIn taskList;
	private TableViewer tableViewer;
	
	public ExampleContentProvider(ModelSeedsFanIn taskList, TableViewer tableViewer){
		this.taskList = taskList;
		this.tableViewer = tableViewer;
	}
	
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		if (newInput != null)
			((ModelSeedsFanIn) newInput).addChangeListener(this);
		if (oldInput != null)
			((ModelSeedsFanIn) oldInput).removeChangeListener(this);
	}

	public void dispose() {
		taskList.removeChangeListener(this);
	}

	// Return the tasks as an array of Objects
	public Object[] getElements(Object parent) {
		return taskList.getTasks().toArray();
	}

	/* (non-Javadoc)
	 * @see ITaskListViewer#addTask(ExampleTask)
	 */
	public void addTask(MethodDescription task) {
		tableViewer.add(task);
	}

	/* (non-Javadoc)
	 * @see ITaskListViewer#removeTask(ExampleTask)
	 */
	public void removeTask(MethodDescription task) {
		tableViewer.remove(task);			
	}

	/* (non-Javadoc)
	 * @see ITaskListViewer#updateTask(ExampleTask)
	 */
	public void updateTask(MethodDescription task) {
		tableViewer.update(task, null);	
		for (Iterator i= taskList.getTasks().iterator() ; i.hasNext() ;){
			MethodDescription et = (MethodDescription)i.next();
			System.out.println("task: "+ et.getDescription());
		}
	}
}