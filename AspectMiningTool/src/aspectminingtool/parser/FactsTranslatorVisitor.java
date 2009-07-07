package aspectminingtool.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import aspectminingtool.data.Fact;

public class FactsTranslatorVisitor extends ASTVisitor {

/*
 * method(idMethod,methodName) idMethod = packageName-className-methodName
 * returnType(idMethod,idReturn)
 * class(idClass,className) idClass = packageName-className
 * define-in(idMethod,idClass)
 * interface(id,interfaceName) id = packageName-interfaceName
 * abstract(id,abtractName) id = packageName + abstractName
 * 
 * call(idCaller,idMethod)
 * extends(idClaseHija,idClasePadre) // extends(idInterfaceHija,idInterfacePadre)
 * implements(idClaseHija,idInteracePadre)
 * 
 * 
 */
	private String actualMethod;
	private String actualPackage;
	private String actualClass;
	private ArrayList<Fact> facts;
	
	/**
	 * Initializes the class
	 */
	public FactsTranslatorVisitor(){
		super();
		this.actualClass = "";
		this.actualMethod = "";
		this.actualPackage = "";
		this.facts = new ArrayList<Fact>();
	}
	
	/**
	 * Returns all the facts collected from the CompilationUnit being visited
	 * @return facts
	 * 				ArrayList with the facts
	 */
	public ArrayList<Fact> getFacts(){
		return facts;
	}
	
	/**
	 *  Visits a package node and obtains the name of the package where the node belongs
	 *  
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(PackageDeclaration node) {

		this.actualPackage = node.getName().getFullyQualifiedName();
		return true;

	}
	
	
	
	/**
	 *  Visits a TypeDeclaration AST node to obtain information about the declaration of the node (type, extends, etc)
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(TypeDeclaration node) {
		
		this.getCompilationType(node);
		
		this.getExtendingClasses(node);
		
		this.getImplementedInterfaces(node);
				
		return true;
		
	}
	
	/**
	 *  Visits a MethodDeclaration AST node. It looks for the class and package that the method belongs, and it obtains the return type.
	 *  @param node  
	 *  			the node to visit
	 */
	
	public boolean visit(MethodDeclaration node) {
		
		String moduleName = node.getName().getFullyQualifiedName();
		
		String treturn = null;
		IMethodBinding binding = node.resolveBinding();
		String params = "";
		if (binding!=null) { 
			
			ITypeBinding[] parameters = binding.getParameterTypes(); 
			for (int i=0;i<parameters.length;i++)
				params += parameters[i].getQualifiedName() + "-";
			
			
			if (!node.isConstructor()) {
				ITypeBinding ireturn = binding.getReturnType();
				treturn = ireturn.getQualifiedName();
			}
		} else {
			if (!node.isConstructor()) {
				treturn = node.getReturnType2().toString();
			}
		}
		//method(claveModulo,nombreModulo) claveModulo = nombrePaquete-nombreClase-nombreModulo-TipoDeParametros
		Fact fact1 = new Fact("method");
		this.actualMethod = actualClass + "-" + moduleName + "-" + params;
		fact1.addParameter(this.actualMethod);

		Fact fact2 = new Fact("name");
		fact2.addParameter(this.actualMethod);
		fact2.addParameter(moduleName);
		
		//define-in(claveModulo,claveClaseContenedora)
		Fact fact3 = new Fact("define-in");
		fact3.addParameter(this.actualMethod);
		fact3.addParameter(this.actualClass);
		
		//returnType(claveModulo,tipoRetorno)
		Fact fact4 = new Fact("returnType");
		fact4.addParameter(this.actualMethod);
		fact4.addParameter(treturn);
		
		facts.add(fact1);
		facts.add(fact2);
		facts.add(fact3);
		facts.add(fact4);
		
		return true;
	}
	
	
	/**
	 *  Visits de SuperMethodInvocation node 
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(SuperMethodInvocation node) {
		
		IMethodBinding nodeBinding = node.resolveMethodBinding();
		this.getCalls(node.getName().getFullyQualifiedName(),nodeBinding);
				
		return true;	
	}
	
	/**
	 *  Visits de MethodInvocation node (the invocations of a method from another method).
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(MethodInvocation node) {
		
		
		IMethodBinding nodeBinding = node.resolveMethodBinding();
		this.getCalls(node.getName().getFullyQualifiedName(),nodeBinding);		
		return true;	
	}
	
	/**
	 * Gets the fact of a call between two methods
	 * 
	 * @param methodName
	 * 					the name of the method 
	 * @param nodeBinding
	 * 					binding of the method
	 * 
	 */
	private void getCalls(String methodName, IMethodBinding nodeBinding){
		
		if (nodeBinding != null ) {
			//String methodName = node.getName().getFullyQualifiedName();
			String className = nodeBinding.getDeclaringClass().getName(); //clase que declara el método llamado
			String packageName = nodeBinding.getDeclaringClass().getPackage().getName(); // paquete que declara el método llamado
			
			String params = "";
			ITypeBinding[] parameters = nodeBinding.getMethodDeclaration().getParameterTypes();
			for (int i=0;i<parameters.length;i++)
				params += parameters[i].getQualifiedName() + "-";
			
			String calledMethod = packageName + "-" + className+ "-" + methodName + "-" + params; 
			//call(claveModuloLlamador,claveModuloLlamado)
			Fact fact = new Fact("call");
			fact.addParameter(this.actualMethod);
			fact.addParameter(calledMethod);
			
			facts.add(fact);

		}
		
	}
	
	
	/**
	 * Adds facts to the facts array identifying the type of the node (interface,abstract or class)
	 * 
	 * @param 
	 * 			AST node being visite
	 * 
	 */
	private void getCompilationType(TypeDeclaration node){
	
		String className = node.getName().getFullyQualifiedName();
		this.actualClass = this.actualPackage + "-" + className;
		Fact fact = new Fact();
		
		if (node.isInterface()) {
			//interface(key) key = InterfacePackage+"-"+InterfaceName
			fact.setName("interface");
			
		} else { 
			List lism = node.modifiers();
			Iterator i = lism.iterator();
			boolean clase_abstract = false;
			while (i.hasNext()) {
				IExtendedModifier ie = (IExtendedModifier)i.next();
				if (ie.isModifier()) { 
					if (((Modifier)ie).isAbstract()) {
						clase_abstract = true;
						//abstract(key) key = packageName+className
						fact.setName("abstract");
					}
				}
			}
			if (!clase_abstract) {
				//class(key) key = packageName+className
				fact.setName("class");
			}
		}
		
		fact.addParameter(this.actualClass);
		
		Fact fact1 = new Fact("name");
		fact1.addParameter(this.actualClass);
		fact1.addParameter(className);
		
		facts.add(fact);
		facts.add(fact1);
		
	}
	
	
	
	/**
	 * Adds the facts that defines the abstract class that the node extends.
	 * 
	 * @param 
	 * 			AST node being visited
	 */
	private void getExtendingClasses(TypeDeclaration node){
		
		
		
		Type type = node.getSuperclassType(); 
		if ((type != null) && type.isSimpleType()){
			String pack = "";
			ITypeBinding bindingType = type.resolveBinding();
			
			if (bindingType != null)
				pack = bindingType.getPackage().getName(); 
			
			//extends(claveClaseHija,claveClaseMadre) claveClaseHija = nombrePaquete + nombreClase 
			Fact fact = new Fact("extends");
			String extendedClass = pack + "-" +((SimpleType)type).getName();
			fact.addParameter(this.actualClass);
			fact.addParameter(extendedClass);
			facts.add(fact);

		}
		
	}
	
	/**
	 * Adds the facts that defines the interfaces that the node implements and extends.
	 * 
	 * @param 
	 * 			node AST being visited
	 * 
	 */
	private void getImplementedInterfaces(TypeDeclaration node){
		
		// determina interfaces que implementa.
		List listaInterfaces = node.superInterfaceTypes();
		if (listaInterfaces != null) {

			Iterator ite = listaInterfaces.iterator();
			
			while (ite.hasNext()) {
				
				Type typ = (Type)ite.next();
				if (typ.isSimpleType()) {
					
					
					Fact fact = new Fact();
					String interfacePackage = "";
					if (typ.resolveBinding() != null)
						interfacePackage = typ.resolveBinding().getPackage().getName(); //busco el paquete al que pertenece la inteface.
					
					if (node.isInterface()) // Interface extends Interface
						//extends(claveInterfaceHija,claveInterfacePadre)
						fact.setName("extends");							
					else // Clase o Abstract implements Interface
						//implements(claveClaseHija,claveInterfacePadre)
						fact.setName("implements");
	
					String interfaceId = interfacePackage + "-" +((SimpleType)typ).getName() ;  // nombre completo de la interface : paquete + nonbre
					fact.addParameter(this.actualClass);
					fact.addParameter(interfaceId);

					facts.add(fact);
				}
			}
		}

		
	}
	

	/**
	 * Starts the process.
	 *
	 * @param unit
	 *            the AST root node. Bindings have to have been resolved.
	 */
	public void process(CompilationUnit unit) {
		unit.accept(this);
	}
}
	
