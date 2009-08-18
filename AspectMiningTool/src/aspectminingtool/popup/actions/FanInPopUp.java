package aspectminingtool.popup.actions;

import java.util.Iterator;

import aspectminingtool.InferenceEngine.Algorithm;


public class FanInPopUp extends AbstractPerformMiningPopup {

	public FanInPopUp() {

	}

	public Algorithm getAlgorithm() {
		
		/*
		//this.algorithm = new FanInAlgorithm();...
		System.out.println("Fan In");
		for (Iterator<Fact> i = Facts.iterator(); i.hasNext(); ){
			Fact f = i.next();
			ArrayList<String> params = f.getParameters();
			String parameters = "";
			for (Iterator<String> i1 = params.iterator() ; i1.hasNext() ;){
				parameters = parameters + i1.next() + ", ";
			}
			System.out.println(f.getName() + "( "+ parameters + " )");
				
		}
	
		*/
		for (Iterator i = Facts.iterator(); i.hasNext();){
			System.out.println(i.next().toString());
		}
		
		return null;
	}

}
