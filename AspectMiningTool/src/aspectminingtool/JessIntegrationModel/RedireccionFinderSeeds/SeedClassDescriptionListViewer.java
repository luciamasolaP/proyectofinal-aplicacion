package aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds;


/**
 * This interface must be implemented by the classes that want to be listening
 * the methodsDescription in the ModelSeedsFanIN. The ModelSeedsFanIn.java
 * notifies this interface when a methodDescription changes.
 * 
 * @author maria
 * 
 */

public interface SeedClassDescriptionListViewer {

	/**
	 * Update the view to reflect the fact that a methodDescription was added to
	 * the task list
	 * 
	 * @param seedDescription
	 */
	public void addSeedDescription(SeedClassDescription seedDescription);

	/**
	 * Update the view to reflect the fact that a methodDescription was removed
	 * from the task list
	 * 
	 * @param seedDescription
	 */
	public void removeSeedDescription(SeedClassDescription seedDescription);

	/**
	 * Update the view to reflect the fact that one of the methodDescription was
	 * modified
	 * 
	 * @param seedDescription
	 */
	public void updateSeedDescription(SeedClassDescription seedDescription);
}
