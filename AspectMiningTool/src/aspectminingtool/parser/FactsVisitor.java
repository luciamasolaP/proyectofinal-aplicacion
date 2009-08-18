package aspectminingtool.parser;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

public abstract class FactsVisitor extends ASTVisitor{
	
	public abstract ArrayList getFacts();
	public abstract void process(CompilationUnit unit);
	
	
}
