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
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import aspectminingtool.data.Fact;

public class FactsTranslator extends ASTVisitor {

	private String actualMethod;
	private String actualPackage;
	private String actualClass;
	private ArrayList<Fact> facts;
	
	/**
	 * Initialize the class
	 */
	public FactsTranslator(){
		super();
		this.actualClass = "";
		this.actualMethod = "";
		this.actualPackage = "";
		this.facts = new ArrayList<Fact>();
	}
	
	/**
	 * This method returns all the facts collected from the class being visited
	 * @return facts
	 * 				ArrayList with facts
	 */
	public ArrayList<Fact> getFacts(){
		return facts;
	}
	
	/**
	 *  Visita un Paquete.
	 *  Obtiene el paquete al qué pertenece la clase.
	 *  En esta parte se debe hacer el filtrado por paquete. Si corresponde a un paquete excluido devolver false
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(PackageDeclaration node) {

		//if no excluyo el paquete {
		this.actualPackage = node.getName().getFullyQualifiedName();
		//paquete(nombrePaquete)
		Fact fact = new Fact("package");
		fact.addParameter(node.getName().getFullyQualifiedName());
		facts.add(fact);
		return true;
	//}
	// return false;

	}
	
	
	
	/**
	 *  Visita una Clase.
	 *  Setea el tipo (interface o clase abstracta), junto con las clases que extiende y las interfaces que implementa
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(TypeDeclaration node) {
		
		this.getCompilationType(node);
		
		this.getExtendingClases(node);
		
		this.getImplementedInterfaces(node);
				
		return true;
		
	}
	
	/**
	 *  Visita una Método. Setea el tipo, la clase a la que pertence, el paquete y el parámetro que devuelve.
	 *  @param node  
	 *  			the node to visit
	 */
	
	public boolean visit(MethodDeclaration node) {
		
		//definir que parámetros devuelve, si es void u otro y si es constructos
		
		this.actualMethod = node.getName().getFullyQualifiedName();
		
		String treturn = null;
		IMethodBinding binding = node.resolveBinding();
		// 
		if (binding!=null) { //trato de resolver el binding
			
			if (!node.isConstructor()) {
				ITypeBinding ireturn = binding.getReturnType();
				treturn = ireturn.getQualifiedName();
			}
		} else {
			if (!node.isConstructor()) {
				treturn = node.getReturnType2().toString();
			}
		}
		//modulo(claveModulo,nombreModulo) claveModulo = nombrePaquete.nombreClase.nombreModulo
		Fact fact1 = new Fact("module");
		String moduleName = actualPackage + "-" +actualMethod + "-" + actualClass;
		fact1.addParameter(moduleName);
		fact1.addParameter(actualMethod);

		//retorno(claveModulo,tipoRetorno)
		Fact fact2 = new Fact("returnType");
		fact2.addParameter(moduleName);
		fact2.addParameter(treturn);
		
		facts.add(fact1);
		facts.add(fact2);
		
		return true;
	}
	
	
	/**
	 *  Visita las invocaciones de un método. Setea el método llamador con this.metodoActual, el metodo invocado junto con la clase y paquete a la que pertencen
	 *  @param node  
	 *  			the node to visit
	 */
	public boolean visit(MethodInvocation node) {
		
		if (node.resolveMethodBinding() != null ) {
			String methodName = node.getName().getFullyQualifiedName();
			String className = node.resolveMethodBinding().getDeclaringClass().getName(); //clase que declara el método llamado
			String packageName = node.resolveMethodBinding().getDeclaringClass().getPackage().getName(); // paquete que declara el método llamado
			String projectName = node.resolveMethodBinding().getJavaElement().getJavaProject().getElementName();
			// String treturn = node.resolveMethodBinding().getMethodDeclaration().getReturnType().getQualifiedName();//typo que devuelve el método

			String calledMethod = packageName + "-" + className+ "-" + methodName; 
			String callerMethod = this.actualPackage + "-" + this.actualClass + "-" + this.actualMethod;
			//llamada(claveModuloLlamador,claveModuloLlamado)
			Fact fact = new Fact("call");
			fact.addParameter(callerMethod);
			fact.addParameter(calledMethod);
			facts.add(fact);
		}
		
		return true;	
	}
	
	/**
	 * devuelve en un arreglo el tipo de unidad de compilación que se está visitando (inteface,clase asbtracta o clase)
	 * 
	 * @paran node AST being visite
	 * @return v array containing the fact that represents the type of the node
	 */
	private void getCompilationType(TypeDeclaration node){
	
		this.actualClass = node.getName().getFullyQualifiedName();
		Fact fact = new Fact();
		
		//determina si es una interface
		if (node.isInterface()) {
			//interface(claveInterface,nombre) claveInterface = nombreInterface+nombrePaquete
			fact.setName("interface");
			String interfaceId = this.actualPackage + "-" + this.actualClass; 
			fact.addParameter(interfaceId);
			fact.addParameter(this.actualClass);
			
		} else { //determina si la clase es abstracta.
			List lism = node.modifiers();
			Iterator i = lism.iterator();
			boolean clase_abstract = false;
			while (i.hasNext()) {
				IExtendedModifier ie = (IExtendedModifier)i.next();
				if (ie.isModifier()) { 
					if (((Modifier)ie).isAbstract()) {
						clase_abstract = true;
						//abstract(claveClase,nombre) claveClase = nombreClase+nombrePaquete
						fact.setName("abstract");
						String abstractId = this.actualPackage + "-" + this.actualClass;
						fact.addParameter(abstractId);
						fact.addParameter(this.actualClass);

					}
				}
			}
			//si no es interface ni clase abstracta es una clase
			if (!clase_abstract) {
				//class(claveClase,nombre) claveClase = nombreClase+nombrePaquete
				fact.setName("class");
				String className = this.actualPackage + "-" + this.actualClass;
				fact.addParameter(className);
				fact.addParameter(this.actualClass);
			}
		}
		
		facts.add(fact);
		
	}
	
	
	
	/**
	 * devuelve en un arreglo el tipo de unidad de compilación que se está visitando (inteface,clase asbtracta o clase)
	 * 
	 * @paran node AST being visite
	 * @return v array containing the facts that represents the super clase
	 */
	private void getExtendingClases(TypeDeclaration node){
		
		Fact fact = new Fact();
		
		// determina las clases que extiende
		Type type = node.getSuperclassType(); // devuelve la clase a lo que extiende
		if ((type != null) && type.isSimpleType()){
			String pack = "";
			ITypeBinding bindingType = type.resolveBinding();
			
			if (bindingType != null)
				pack = bindingType.getPackage().getName(); //paquete al que pertenece la clase.
			
			//extends(claveClaseHija,claveClaseMadre) claveClaseHija = nombreClase + nombrePaquete
			fact.setName("extends");
			String extendedClass = pack + "-" +((SimpleType)type).getName();
			String c = this.actualPackage + "-" + this.actualClass;
			fact.addParameter(c);
			fact.addParameter(extendedClass);
			facts.add(fact);

		}
		
	}
	
	/**
	 * devuelve en un arreglo el tipo de unidad de compilación que se está visitando (inteface,clase asbtracta o clase)
	 * 
	 * @paran node AST being visite
	 * @return v array containing the interfaces of the Compilation Unit being analized
	 */
	private void getImplementedInterfaces(TypeDeclaration node){
		
		// determina interfaces que implementa.
		List listaInterfaces = node.superInterfaceTypes();
		if (listaInterfaces != null) {

			String actualInterface = this.actualPackage + "-" + this.actualClass;			
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
					fact.addParameter(actualInterface);
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
	
