package aspectminingtool.views.Sinergia.Seeds;


import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.ProjectModel;
import aspectminingtool.util.MethodFormater;


/**
 * Class that plays the role of the domain model in the SinergiaSeedsDescriptionTableViewer
 * 
 */

public class SinergiaDescriptionResultsModel implements IResultsModel{

	private List seedDescriptionList = new ArrayList();
	private List changeListenersSeedDescription = new ArrayList();
	
	private Map<SeedDescription,List<String>> sinergiaSeedsDescription = new HashMap<SeedDescription,List<String>>();
	private List changeListenersRelatedMethodDescription = new ArrayList();
	ProjectModel projectModel = null;

	
	/**
	 * Constructor
	 */
	public SinergiaDescriptionResultsModel() {
		super();
	}
	
	/**
	 * Return the collection of seedsDescription
	 */
	public List getSeedDescriptions() {
		return seedDescriptionList;
	}
	
	/**
	 * Return the collection of seedsDescription
	 */
	public List<String> getAlgorithmResults(SeedDescription seedDescription) {
		return sinergiaSeedsDescription.get(seedDescription);
	}
	
//	/**
//	 * Add a new task to the collection of methodsDescriptions and tells all the listener to add the new methodDescription
//	 */
//	public void addSeedDescription() {
//		SeedDescription seedDescription = new SeedDescription();
//		seedDescriptionList.add(seedDescriptionList.size(), seedDescription);
//		Iterator iterator = changeListenersSeedDescription.iterator();
//		while (iterator.hasNext())
//			((SeedDescriptionListViewer) iterator.next()).addSeedDescription(seedDescription);
//	}

	/**
	 * Adds a new SeedDescription to the model and tells all the listener to add the new SeedDescription
	 * @param seedDescription
	 * @param algorithmResults
	 */
	public void addMethodAsASeed(SeedDescription seedDescription, List<String> algorithmResults) {
		
		if (!pertenece(seedDescription)){
			seedDescriptionList.add(seedDescriptionList.size(), seedDescription);
			if (algorithmResults != null)
				this.sinergiaSeedsDescription.put(seedDescription, algorithmResults);
			Iterator iterator = changeListenersSeedDescription.iterator();
			while (iterator.hasNext())
				((SeedDescriptionListViewer) iterator.next()).addSeedDescription(seedDescription);
		}
		
	}
	
	private boolean pertenece(SeedDescription seedDescription) {
		for (Iterator i = seedDescriptionList.iterator(); i.hasNext(); ){
			SeedDescription seed = (SeedDescription)i.next();
			String id = seed.getMethod();
			String algorithm = seed.getTrust(); 
			if (id.equals(seedDescription.getMethod()))
				return true;
		}
		return false;
	}

	/**
	 * Deletes a SeedDescription from the model and tells all the listener to delete it
	 * @param seedDescription
	 */
	public void removeMethodDescription(SeedDescription seedDescription) {
		seedDescriptionList.remove(seedDescription);
		sinergiaSeedsDescription.remove(seedDescription.getMethod());
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedDescriptionListViewer) iterator.next()).removeSeedDescription(seedDescription);
	}

	/**
	 * Tells the ListViewer to update the seedDescription 
	 * @param seedDescription
	 */
	public void seedDescriptionChanged(SeedDescription seedDescription) {
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedDescriptionListViewer) iterator.next()).updateSeedDescription(seedDescription);
	}

	/**
	 * Removes a SeedDescriptionListViewer of the listeners list
	 * @param viewer
	 */
	public void removeChangeListenerSeedDescription(SeedDescriptionListViewer viewer) {
		changeListenersSeedDescription.remove(viewer);
	}

	/**
	 * Adds a SeedDescriptionListViewer of the listeners list
	 * @param viewer
	 */
	public void addChangeListenerSeedDescription(SeedDescriptionListViewer viewer) {
		changeListenersSeedDescription.add(viewer);
	}
	
	public Map<SeedDescription, List<String>> getAlgorithmResults() {
		return sinergiaSeedsDescription;
	}
	
	public List<String> getSinergiaSeedsDescResults(String methodId){
		
		return sinergiaSeedsDescription.get(methodId);
		
	}

	public void setSinergiaSeedsDescResults(Map<SeedDescription, List<String>> calls) {
		this.sinergiaSeedsDescription = calls;
	}

	@Override
	public String getId() {
		return projectModel.getName();
	}

	@Override
	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}


	@Override
	public void generateArchive(BufferedWriter archive) {
		
		try 
	    {
			for (Iterator<SeedDescription> methodIterator = seedDescriptionList.iterator(); methodIterator.hasNext() ;){
				SeedDescription seedDescr = methodIterator.next(); 
				archive.write(MethodFormater.formatMethodIdToString(seedDescr.getMethod()));
				archive.newLine();
				archive.write("                 Description: " + seedDescr.getDescription());
				archive.newLine();
				if (sinergiaSeedsDescription.containsKey(seedDescr)){
					
					List<String> list = sinergiaSeedsDescription.get(seedDescr);
					archive.write("                 Fan-in: " + list.get(0));
					archive.newLine();
					archive.write("                 Unique Methods: " + list.get(1));
					archive.newLine();
					archive.write("                 Execution Relations: " + list.get(2));
					archive.newLine();
					}
					
				archive.newLine();		
			}
	        archive.close();
	    }
	    catch (IOException e)    {    }
		
	}

}
