package aspectminingtool.commands;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.handlers.HandlerUtil;

import aspectminingtool.views.Seeds.ExampleTask;
import aspectminingtool.views.Seeds.ExampleTaskList;
import aspectminingtool.views.Seeds.ViewPartSeeds;


public class CommandPrintModel extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		ViewPartSeeds view = (ViewPartSeeds)HandlerUtil.getActivePart(event);
		
		if (view.getModel() != null){
			ExampleTaskList model = (ExampleTaskList)view.getModel(); //TODO obtener java project del modelo de la vista
		
			System.out.println("En CommandPrintModel.java");
			Vector tasks = model.getTasks();
			for (Iterator i= tasks.iterator() ; i.hasNext() ;){
				ExampleTask et = (ExampleTask)i.next();
				System.out.println("task: "+ et.getDescription() + ", " + et.getOwner() + ", " + et.getPercentComplete());
			}
		}
		
		return null;
	}

}
