package aspectminingtool.parser;


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import aspectminingtool.data.Fact;

public class ASTInspectorProject extends AbtractASTInspector {

	public void run(ICompilationUnit lwUnit) {
		CompilationUnit unit = parse(lwUnit);
		FactsTranslator factsTranslator = new FactsTranslator();
		factsTranslator.process(unit);
		ArrayList<Fact> facts = factsTranslator.getFacts();
		//debe haber una clase que haga el formateo de los hechos, dado un hecho haga el formateo para 
		//los hechos especificos del lenguaje
		for (Iterator<Fact> i = facts.iterator(); i.hasNext(); ){
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
