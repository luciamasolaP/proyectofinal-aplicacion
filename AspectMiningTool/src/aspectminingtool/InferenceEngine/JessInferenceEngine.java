package aspectminingtool.InferenceEngine;


import java.util.Iterator;
import java.util.List;

import jess.JessException;
import jess.Rete;
import JessIntegrationModel.Interface;
import aspectminingtool.Activator;



public class JessInferenceEngine extends InferenceEngine {

	Rete engine;
	
	public JessInferenceEngine(){
		this.engine = new Rete(new Interface(null, null));
	}
	
	public Rete getEngine(){
		return engine;
	}
	
	@Override
	protected void confirgureAlgorithm(){
		
		String archive = algorithm.getArchive();	
		String PlugInPath = Activator.getInstallLocation();
		String finalArchivePath = PlugInPath + archive;
		
		try {
			engine.batch(finalArchivePath);
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void executeAlgorithm() {
		try {
			engine.run();
		} catch (JessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}

	@Override
	protected void persistFacts(List facts) {
		try {
			for (Iterator i = facts.iterator() ; i.hasNext() ; ){
				Object o = i.next();
				if (!algorithm.filerFact(o))
					engine.add(o);
			}
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void clearEngine(){
		try {
			engine.clear();
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
