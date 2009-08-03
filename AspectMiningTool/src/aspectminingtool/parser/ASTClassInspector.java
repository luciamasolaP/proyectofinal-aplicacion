package aspectminingtool.parser;


import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import aspectminingtool.data.Fact;

/**
 * ASTClassInspector obtains the facts representing a Compilation Unit using the FactsTranslatorVisitor and the ASTParser.
 * 
 * @author maria
 *
 */
public class ASTClassInspector {

	/**
	 * This method parses an ICompilationUnit and visits the resulting AST node (CompilationUnit) to obtain the information of the node.
	 * @param lwUnit
	 * 				the compilation being processed.
	 * @return
	 * 				ArrayList<Fact> with the facts representing the compilation unit
	 */
	public ArrayList<Fact> getCompilationFacts(ICompilationUnit lwUnit){
		
		CompilationUnit unit = parse(lwUnit);
		FactsTranslatorVisitor factsTranslator = new FactsTranslatorVisitor();
		factsTranslator.process(unit);
		return factsTranslator.getFacts();
	}
	
	
	/**
	 * Parses source code.
	 *
	 * @param lwUnit
	 *            the Java Model handle for the compilation unit
	 * @return 
	 * 			the root AST node of the parsed source
	 */
	protected CompilationUnit parse(ICompilationUnit lwUnit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(lwUnit); // set source
		parser.setResolveBindings(true); // we need bindings later on
		return (CompilationUnit) parser.createAST(null /* IProgressMonitor */); // parse
	}

}
