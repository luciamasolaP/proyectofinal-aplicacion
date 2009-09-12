package aspectminingtool.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.handlers.HandlerUtil;

import aspectminingtool.views.Seeds.MethodDescription;
import aspectminingtool.views.Seeds.ModelSeedsFanIn;
import aspectminingtool.views.Seeds.ViewPartSeeds;


public class CommandPrintModel extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		ViewPartSeeds view = (ViewPartSeeds)HandlerUtil.getActivePart(event);
		
		if (view.getModel() != null){
			ModelSeedsFanIn model = (ModelSeedsFanIn)view.getModel(); //TODO obtener java project del modelo de la vista
		
			System.out.println("En CommandPrintModel.java");
			List tasks = model.getTasks();
			for (Iterator i= tasks.iterator() ; i.hasNext() ;){
				MethodDescription et = (MethodDescription)i.next();
				System.out.println("task: "+ et.getDescription());
			}
		}
		
		return null;
	}

}
