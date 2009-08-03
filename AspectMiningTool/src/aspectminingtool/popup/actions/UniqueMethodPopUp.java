package aspectminingtool.popup.actions;

import java.util.ArrayList;
import java.util.Iterator;

import aspectminingtool.data.Fact;

public class UniqueMethodPopUp extends AbstractPerformMiningPopup {

	public UniqueMethodPopUp() {

	}

	public void createAlgorithm() {

		//this.algorithm = new FanInAlgorithm();...
		System.out.println("Unique Method");
		for (Iterator<Fact> i = Facts.iterator(); i.hasNext(); ){
			Fact f = i.next();
			ArrayList<String> params = f.getParameters();
			String parameters = "";
			for (Iterator<String> i1 = params.iterator() ; i1.hasNext() ;){
				parameters = parameters + i1.next() + ", ";
			}
			System.out.println(f.getName() + "( "+ parameters + " )");
				
		}
		
	}

}
