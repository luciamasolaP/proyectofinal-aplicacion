package aspectminingtool.JessIntegrationModel.Seeds;


/**
 * This interface must be implemented by the classes that want to be listening
 * the methodsDescription in the ModelSeedsFanIN. The ModelSeedsFanIn.java
 * notifies this interface when a methodDescription changes.
 * 
 * @author maria
 * 
 */

public interface MethodDescriptionListViewer {

	/**
	 * Update the view to reflect the fact that a methodDescription was added to
	 * the task list
	 * 
	 * @param methodDescription
	 */
	public void addMethodDescription(MethodDescription methodDescription);

	/**
	 * Update the view to reflect the fact that a methodDescription was removed
	 * from the task list
	 * 
	 * @param methodDescription
	 */
	public void removeMethodDescription(MethodDescription methodDescription);

	/**
	 * Update the view to reflect the fact that one of the methodDescription was
	 * modified
	 * 
	 * @param methodDescription
	 */
	public void updateMethodDEscription(MethodDescription methodDescription);
}
