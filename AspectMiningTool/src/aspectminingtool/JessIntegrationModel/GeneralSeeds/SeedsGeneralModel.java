package aspectminingtool.JessIntegrationModel.GeneralSeeds;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;


/**
 * Class that plays the role of the domain model in the TableViewerExample
 * 
 */

public class SeedsGeneralModel implements IResultsModel{

	private List seedDescriptionList = new ArrayList();
	private List changeListenersSeedDescription = new ArrayList();
	
	private Map<SeedDescription,List<RelatedMethodDescription>> relatedMethodDescriptionList = new HashMap<SeedDescription,List<RelatedMethodDescription>>();
	private List changeListenersRelatedMethodDescription = new ArrayList();
	
	ProjectModel projectModel = null;

	
	/**
	 * Constructor
	 */
	public SeedsGeneralModel() {
		super();
	//	this.initData();
		
	}
	

	/**
	 * Return the collection of methodDescriptions
	 */
	public List getSeedDescriptions() {
		return seedDescriptionList;
	}
	
	/**
	 * Return the collection of methodDescriptions
	 */
	public List<RelatedMethodDescription> getRelatedMethodDescriptions(SeedDescription seedDescription) {
		return relatedMethodDescriptionList.get(seedDescription);
	}
	
	/**
	 * Add a new task to the collection of methodsDescriptions and tells all the listener to add the new methodDescription
	 */
	public void addTask() {
		SeedDescription seedDescription = new SeedDescription();
		seedDescriptionList.add(seedDescriptionList.size(), seedDescription);
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedDescriptionListViewer) iterator.next()).addSeedDescription(seedDescription);
	}

	/**
	 * Adds a new SeedDescription to the model and tells all the listener to add the new SeedDescription
	 * @param et
	 * @param methodId
	 * @param methodsCalls
	 */
	public void addMethodAsASeed(SeedDescription et, String methodId,List<RelatedMethodDescription> methodsCalls) {
		
		if (!pertenece(et)){
			seedDescriptionList.add(seedDescriptionList.size(), et);
			if (methodsCalls != null)
				this.relatedMethodDescriptionList.put(et, methodsCalls);
			Iterator iterator = changeListenersSeedDescription.iterator();
			while (iterator.hasNext())
				((SeedDescriptionListViewer) iterator.next()).addSeedDescription(et);
			
		}
		
	}
	
	private boolean pertenece(SeedDescription et) {
		for (Iterator i = seedDescriptionList.iterator(); i.hasNext(); ){
			SeedDescription seed = (SeedDescription)i.next();
			String id = seed.getMethod().getId();
			String algorithm = seed.getAlgoritmo(); 
			if (id.equals(et.getMethod().getId()) && algorithm.equals(et.getAlgoritmo()))
				return true;
		}
		return false;
	}

	/**
	 * @param seedDescription
	 */
	public void removeMethodDescription(SeedDescription seedDescription) {
		seedDescriptionList.remove(seedDescription);
		relatedMethodDescriptionList.remove(seedDescription.getMethod().getId());
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedDescriptionListViewer) iterator.next()).removeSeedDescription(seedDescription);
		//debería iterar sobre los listener de las calls para avisar al modelo de removerlo
	}

	/**
	 * @param methodDescription
	 */
	public void seedDescriptionChanged(SeedDescription methodDescription) {
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedDescriptionListViewer) iterator.next()).updateSeedDescription(methodDescription);
//		for (Iterator i= methodsDescriptionList.iterator() ; i.hasNext() ;){
//			MethodDescription et = (MethodDescription)i.next();
//
//		}
	}

	/**
	 * @param relatedMethodDescription
	 */
	public void relatedMethodDescriptionChanged(RelatedMethodDescription relatedMethodDescription) {
		Iterator iterator = changeListenersRelatedMethodDescription.iterator();
		while (iterator.hasNext())
			((RelatedMethodDescriptionListViewer) iterator.next()).updateRelatedMethodDescription(relatedMethodDescription);
//		for (Iterator i= callDescription.iterator() ; i.hasNext() ;){
//			CallDescription et = (CallDescription)i.next();
//
//		}
	}
	

	/**
	 * @param viewer
	 */
	public void removeChangeListenerSeedDescription(SeedDescriptionListViewer viewer) {
		changeListenersSeedDescription.remove(viewer);
	}

	/**
	 * @param viewer
	 */
	public void addChangeListenerSeedDescription(SeedDescriptionListViewer viewer) {
		changeListenersSeedDescription.add(viewer);
	}

	
	/**
	 * It removes a listener of the type RelatedMethodDescriptionListViewer
	 * @param viewer
	 */
	public void removeChangeListenerRelatedMethodDescription(RelatedMethodDescriptionListViewer viewer) {
		changeListenersRelatedMethodDescription.remove(viewer);
	}

	/**
	 * It adds a listener of the type CallDescriptionListViewer
	 * @param viewer
	 */
	public void addChangeListenerRelatedMethodDescription(RelatedMethodDescriptionListViewer viewer) {
		changeListenersRelatedMethodDescription.add(viewer);
	}
	
	
	public Map<SeedDescription, List<RelatedMethodDescription>> getRelatedMethods() {
		return relatedMethodDescriptionList;
	}
	
	public List<RelatedMethodDescription> getRelatedMethods(String methodId){
		
		return relatedMethodDescriptionList.get(methodId);
		
	}

	public void setRelatedMethods(Map<SeedDescription, List<RelatedMethodDescription>> calls) {
		this.relatedMethodDescriptionList = calls;
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
				SeedDescription md = methodIterator.next(); 
				Method m = md.getMethod();
				archive.write(md.toString());
				archive.newLine();
				if (relatedMethodDescriptionList.containsKey(md)){
					
					List<RelatedMethodDescription> list = relatedMethodDescriptionList.get(md);
					for (Iterator<RelatedMethodDescription> callIterator=list.iterator();callIterator.hasNext();){
						archive.write("                 " + callIterator.next().toString());
						archive.newLine();
					}
					
				}

				archive.newLine();
					
			}

	        archive.close();
	    }
	    catch (IOException e)    {    }
		
		
	}
	

}
