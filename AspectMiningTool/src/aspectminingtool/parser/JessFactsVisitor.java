package aspectminingtool.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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

import JessIntegrationModel.*;
import JessIntegrationModel.Class;




/**
	
 * FactsTranslatorVisitor is an extension of ASTVisitor. It defines how to visit the AST nodes that are useful for the application, as well 
 * as what to do with the information from each type. The class creates facts representing information obtained from each AST Node. It creates
 * the following fact:
 * 
 * Fact: method - Parameters: idMethod,methodName. 
 * Fact: returnType - Parameters:  Parameters: idMethod,idReturn
 * Fact: class - Parameters: idClass,className 
 * Fact: define-in - Parameters: idMethod,idClass
 * Fact: interface - Parameters: id,interfaceName 
 * Fact: abstract - Parameters: idAbstract,abtractName  
 * Fact: call - Parameters: idCaller,idMethod
 * Fact: Inherits - Parameters: idClaseHija,idClasePadre // idInterfaceHija,idInterfacePadre
 * Fact: implements - Parameters: idClaseHija,idInteracePadre
 *  
 *  idMethod = packageName-className-methodName
 *  idClass = packageName-className
 *  id = packageName-interfaceName
 *  idAbstract = packageName + abstractName
 *  
 * @author maria
 *
 */
public class JessFactsVisitor extends FactsVisitor {

	private String actualMethod;
	private String actualPackage;
	private String actualClass;
	private ArrayList facts;

	
	/**
	 * Initializes the class
	 */
	public JessFactsVisitor(){
		super();
		this.actualClass = "";
		this.actualMethod = "";
		this.actualPackage = "";
		this.facts = new ArrayList();
	}
	
	/**
	 * Returns all the facts collected from the CompilationUnit being visited
	 * @return facts
	 * 				ArrayList with the facts
	 */
	public ArrayList getFacts(){
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
		//method(claveModulo,nombreModulo,tipoRetorn) claveModulo = nombrePaquete-nombreClase-nombreModulo-TipoDeParametros
		
		this.actualMethod = actualClass + "//" + moduleName + "///" + params;
		Method m = new Method(this.actualMethod,moduleName,treturn,this.actualClass,params);
				
		//define-in(claveModulo,claveClaseContenedora)
		//Define_In define_In = new Define_In(this.actualMethod,this.actualClass); 
				
		facts.add(m);
		//facts.add(define_In);

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
			
			String calledMethod = packageName + "/" + className+ "//" + methodName + "///" + params; 
			//call(claveModuloLlamador,claveModuloLlamado)

			Call c = new Call(this.actualMethod,calledMethod);
			facts.add(c);

		}
		
	}
	
	
	/**
	 * Adds facts to the facts array identifying the type of the node (interface,abstract or class)
	 * 
	 * @param 
	 * 			AST node being visited
	 * 
	 */
	private void getCompilationType(TypeDeclaration node){
	
		String className = node.getName().getFullyQualifiedName();
		this.actualClass = this.actualPackage + "/" + className;
		Object o = null;
		
		if (node.isInterface()) {
			//interface(key) key = InterfacePackage+"-"+InterfaceName
			o = new Interface(this.actualClass,className);
			
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
						o = new Abstract(this.actualClass,className);
					}
				}
			}
			if (!clase_abstract) {
				//class(key) key = packageName+className
				o = new Class(this.actualClass,className);
			}
		}
	
		facts.add(o);

		
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
			
			//Inherits(claveClaseHija,claveClaseMadre) claveClaseHija = nombrePaquete + nombreClase 

			String extendedClass = pack + "/" +((SimpleType)type).getName();

			Inherits inh = new Inherits(this.actualClass,extendedClass);
			facts.add(inh);

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
					
					Object o = null;
					String interfacePackage = "";
					if (typ.resolveBinding() != null)
						interfacePackage = typ.resolveBinding().getPackage().getName(); //busco el paquete al que pertenece la inteface.
					
					String interfaceId = interfacePackage + "/" +((SimpleType)typ).getName() ;  // nombre completo de la interface : paquete + nonbre
					
					if (node.isInterface()) // Interface extends Interface
						//extends(claveInterfaceHija,claveInterfacePadre)
						//fact.setName("extends");						
						o = new Inherits(this.actualClass,interfaceId);
					else // Clase o Abstract implements Interface
						//implements(claveClaseHija,claveInterfacePadre)
						//fact.setName("implements");
						o = new Implements(this.actualClass,interfaceId);


					facts.add(o);
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
		facts = new ArrayList();
		unit.accept(this);
	}
	
}
	
