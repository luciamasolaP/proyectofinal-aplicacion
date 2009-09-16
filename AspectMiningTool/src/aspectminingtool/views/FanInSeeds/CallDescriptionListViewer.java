package aspectminingtool.views.FanInSeeds;

import aspectminingtool.JessIntegrationModel.Seeds.CallDescription;

/**
 * This interface must be implemented by the classes that want to be listening
 * the calls Descriptions in the ModelSeedsFanIN. The ModelSeedsFanIn.java
 * notifies this interface when a callDescription changes.
 * 
 * @author maria
 * 
 */

public interface CallDescriptionListViewer {

	/**
	 * Update the view to reflect the fact that a callDescription was added to
	 * the task list
	 * 
	 * @param callDescription
	 */
	public void addCallDescription(CallDescription callDescription);

	/**
	 * Update the view to reflect the fact that a callDescription was removed
	 * from the task list
	 * 
	 * @param callDescription
	 */
	public void removeCallDescription(CallDescription callDescription);

	/**
	 * Update the view to reflect the fact that one of the callDescription was
	 * modified
	 * 
	 * @param callDescription
	 */
	public void updateCallDEscription(CallDescription callDescription);
}
