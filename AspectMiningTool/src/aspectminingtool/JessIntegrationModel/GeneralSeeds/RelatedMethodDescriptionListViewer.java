package aspectminingtool.JessIntegrationModel.GeneralSeeds;


/**
 * This interface must be implemented by the classes that want to be listening
 * the calls Descriptions in the ModelSeedsFanIN. The ModelSeedsFanIn.java
 * notifies this interface when a callDescription changes.
 * 
 * @author maria
 * 
 */

public interface RelatedMethodDescriptionListViewer {

	/**
	 * Update the view to reflect the fact that a callDescription was added to
	 * the task list
	 * 
	 * @param relatedMethodDescription
	 */
	public void addRelatedMethodDescription(RelatedMethodDescription relatedMethodDescription);

	/**
	 * Update the view to reflect the fact that a callDescription was removed
	 * from the task list
	 * 
	 * @param callDescription
	 */
	public void removeRelatedDescription(RelatedMethodDescription relatedMethodDescription);

	/**
	 * Update the view to reflect the fact that one of the callDescription was
	 * modified
	 * 
	 * @param callDescription
	 */
	public void updateRelatedMethodDescription(RelatedMethodDescription relatedMethodDescription);
}
