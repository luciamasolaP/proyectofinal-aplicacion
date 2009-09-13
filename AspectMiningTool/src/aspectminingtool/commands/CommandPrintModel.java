package aspectminingtool.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import JessIntegrationModel.IResultsModel;
import aspectminingtool.views.ViewFilterProject;


public class CommandPrintModel extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ViewFilterProject view = (ViewFilterProject)HandlerUtil.getActivePart(event);
		IResultsModel model = view.getModel();
		if (model != null){
		
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			
		    FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		    dialog
		        .setFilterNames(new String[] { "TXT Files", "All Files (*.*)" });
		    dialog.setFilterExtensions(new String[] { "*.txt", "*.*" }); // Windows
		    dialog.setFilterPath("c:\\"); // Windows path
		    dialog.setFileName("results.txt");

		    String filePath = dialog.open();
		    BufferedWriter outfile;
			try {
				outfile = new BufferedWriter(new FileWriter(filePath));
				model.generateArchive(outfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    

		    while (!shell.isDisposed()) {
		      if (!display.readAndDispatch())
		        display.sleep();
		    }
		    display.dispose();
			
		}
		
		return null;
	}

}
