package aspectminingtool.Algorithms.Sinergia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.Algorithms.FanInAlgorithm;
import aspectminingtool.Algorithms.FlowGraphAlgorithm;
import aspectminingtool.Algorithms.UniqueMethodsAlgorithm;
import aspectminingtool.InferenceEngine.InferenceEngine;
import aspectminingtool.InferenceEngine.JessInferenceEngine;
import aspectminingtool.JessIntegrationModel.Sinergia.FanInMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.InsideFirstExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.InsideLastExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.OutsideAfterExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.OutsideBeforeExecutionMetric;
import aspectminingtool.JessIntegrationModel.Sinergia.SinergiaResultsModel;
import aspectminingtool.JessIntegrationModel.Sinergia.UniqueMethodsMetric;
import aspectminingtool.model.AspectMiningModel;
import aspectminingtool.parser.JessFactsVisitor;
import aspectminingtool.parser.ProjectInspector;
import aspectminingtool.views.Sinergia.ViewPartSinergia;

public class SinergiaAnalysis implements IRunnableWithProgress{

	private IJavaProject javaProject;
	private List Facts;
	private InferenceEngine inferenceEngine;
	private ProjectModel pm;
	private IResultsModel model;
	List restricciones;
	
	public void assertRestricciones(List restricciones){
		this.restricciones =  restricciones;
	}
	
	public SinergiaAnalysis(IJavaProject javaProject){
		super();
		this.javaProject = javaProject;
	}
	
	public void OpenView(){
		
	    ViewPartSinergia view;
		try {
			view = (ViewPartSinergia) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(ViewPartSinergia.ID_VIEW );
			view.setModel(model);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void ejecutar() throws InvocationTargetException, InterruptedException{
		calculateFacts();
		Shell shell = new Shell();
		new ProgressMonitorDialog(shell).run(true, true, this);
		getResults();
		OpenView();
	}
	
	public void calculateFacts(){

		pm = AspectMiningModel.createProjectModel(javaProject);
		
		ProjectInspector projectInspector = new ProjectInspector(javaProject,new JessFactsVisitor());
		
		Shell shell = new Shell();
		 try {
			 
			 new ProgressMonitorDialog(shell).run(true, true, projectInspector);
	       
			 Facts = projectInspector.getFacts();
					
		
		 } catch (InvocationTargetException e) {
	          MessageDialog.openError(shell, "Error", e.getMessage());
	        } catch (InterruptedException e) {
	          MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
	        }
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
			inferenceEngine = AspectMiningModel.getInferenceEngine();
				
			monitor.beginTask("Executing Sinergia Analysis...", IProgressMonitor.UNKNOWN);
	 		 
			 inferenceEngine.setAlgorithm(new FanInAlgorithm());
			 inferenceEngine.execute(Facts);
			 List SinergiaFacts = getFanInMetric();
			 inferenceEngine.clearEngine();
			 
			 if (monitor.isCanceled())
					throw new InterruptedException("Sinergia Analysis was cancelled");
			
			 inferenceEngine.setAlgorithm(new UniqueMethodsAlgorithm());
			 inferenceEngine.execute(Facts);
			 SinergiaFacts.addAll(getUniqueMethodsMetric());
			 inferenceEngine.clearEngine();
			 
			 if (monitor.isCanceled())
					throw new InterruptedException("Sinergia Analysis was cancelled");
			
			 inferenceEngine.setAlgorithm(new FlowGraphAlgorithm());
			 inferenceEngine.execute(Facts);
			 Facts.clear();
			 SinergiaFacts.addAll(getFlowGraphMetric());
			 inferenceEngine.clearEngine();
			 
			 if (monitor.isCanceled())
					throw new InterruptedException("Sinergia Analysis was cancelled");
		 
			 inferenceEngine.setAlgorithm(new SinergiaAlgorithm());
			 inferenceEngine.assertFacts(restricciones);
			 inferenceEngine.execute(SinergiaFacts);
			 
				try 
			    {
			        BufferedWriter outfile1 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\Seeds.txt"));
			        

			        		    		

							Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
							QueryResult result;
							try {
								result = jessEngine.runQueryStar("getSeeds", new ValueVector().add(""));
								 while (result.next()) {
									 outfile1.write("Metodo: "+ result.getString("method") + "            Trust: " + result.getString("trust"));
									outfile1.newLine();   
							        }
							} catch (JessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					        outfile1.close();
					}

			    catch (IOException e)    {    }
			 
			 SinergiaFacts.clear();
 
			 monitor.done();
      //  return Status.OK_STATUS;
		
	}
	
	protected void showResults(IResultsModel results2){
		
		try 
	    {
	        BufferedWriter outfile1 = new BufferedWriter(new FileWriter("C:\\Users\\maria\\Desktop\\Seeds.txt"));
	        

	           		results2.generateArchive(outfile1);

//					Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
//					QueryResult result;
//					try {
//						result = jessEngine.runQueryStar("getSeeds", new ValueVector().add(""));
//						 while (result.next()) {
//							 outfile1.write("Metodo: "+ result.getString("method") + "            Trust: " + result.getString("trust"));
//							outfile1.newLine();   
//					        }
//					} catch (JessException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//			        outfile1.close();
			}

	    catch (IOException e)    {    }
		
	    
	}

	protected void getResults(){
		model = new SinergiaResultsModel(pm,inferenceEngine);		
	}


	protected List getFanInMetric(){
		List fanInMetrics = new ArrayList();
		Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("FinalfanIn", new ValueVector().add(""));
			 while (result.next()) {
				 FanInMetric ff = new FanInMetric(result.getString("metodo"), result.getString("m"));
				 fanInMetrics.add(ff);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fanInMetrics;
	}
	
	private List getUniqueMethodsMetric() {
		List uniqueMethodsMetrics = new ArrayList();
		Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("UniqueMethods", new ValueVector().add(""));
			 while (result.next()) {
				 UniqueMethodsMetric ff = new UniqueMethodsMetric(result.getString("mi"), result.getString("m"));
				    uniqueMethodsMetrics.add(ff);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uniqueMethodsMetrics;
	}
	
	private List getFlowGraphMetric() {
		
		List FlowGraphMetrics = new ArrayList();
		Rete jessEngine = ((JessInferenceEngine) inferenceEngine).getEngine();
		
		//OutsideBeforeExecutionMetrics
		QueryResult result;
		try {
			result = jessEngine.runQueryStar("get_OutsideBeforeExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 OutsideBeforeExecutionMetric flowGraphMetric = new OutsideBeforeExecutionMetric(result.getString("method"), result.getString("metric"));
				 FlowGraphMetrics.add(flowGraphMetric);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//OutsideAfterExecutionMetrics
		try {
			result = jessEngine.runQueryStar("get_OutsideAfterExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 OutsideAfterExecutionMetric flowGraphMetric = new OutsideAfterExecutionMetric(result.getString("method"), result.getString("metric"));
				 FlowGraphMetrics.add(flowGraphMetric);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//InsideFirstExecutionMetrics
		try {
			result = jessEngine.runQueryStar("get_InsideFirstExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 InsideFirstExecutionMetric flowGraphMetric = new InsideFirstExecutionMetric(result.getString("method"), result.getString("metric"));
				 FlowGraphMetrics.add(flowGraphMetric);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//InsideLastExecutionMetrics
		try {
			result = jessEngine.runQueryStar("get_InsideLastExecution_Metric", new ValueVector().add(""));
			 while (result.next()) {
				 InsideLastExecutionMetric flowGraphMetric = new InsideLastExecutionMetric(result.getString("method"), String.valueOf(result.getInt("metric")));
				 FlowGraphMetrics.add(flowGraphMetric);
		        }
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return FlowGraphMetrics;
	}

}
