package aspectminingtool.views.Seeds;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class ExampleContentProvider implements IStructuredContentProvider, ITaskListViewer {
	
	private ExampleTaskList taskList;
	private TableViewer tableViewer;
	
	public ExampleContentProvider(ExampleTaskList taskList, TableViewer tableViewer){
		this.taskList = taskList;
		this.tableViewer = tableViewer;
	}
	
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		if (newInput != null)
			((ExampleTaskList) newInput).addChangeListener(this);
		if (oldInput != null)
			((ExampleTaskList) oldInput).removeChangeListener(this);
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
	public void addTask(ExampleTask task) {
		tableViewer.add(task);
	}

	/* (non-Javadoc)
	 * @see ITaskListViewer#removeTask(ExampleTask)
	 */
	public void removeTask(ExampleTask task) {
		tableViewer.remove(task);			
	}

	/* (non-Javadoc)
	 * @see ITaskListViewer#updateTask(ExampleTask)
	 */
	public void updateTask(ExampleTask task) {
		tableViewer.update(task, null);	
		System.out.println("en ExampleContentProvider.java");
		for (Iterator i= taskList.getTasks().iterator() ; i.hasNext() ;){
			ExampleTask et = (ExampleTask)i.next();
			System.out.println("task: "+ et.getDescription() + ", " + et.getOwner() + ", " + et.getPercentComplete());
		}
	}
}