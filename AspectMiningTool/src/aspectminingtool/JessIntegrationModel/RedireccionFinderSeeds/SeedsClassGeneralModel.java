/**
 * (c) Copyright Mirasol Op'nWorks Inc. 2002, 2003. 
 * http://www.opnworks.com
 * Created on Apr 2, 2003 by lgauthier@opnworks.com
 * 
 */

package aspectminingtool.JessIntegrationModel.RedireccionFinderSeeds;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import aspectminingtool.JessIntegrationModel.RedireccionFinder.RedirectorFinderResults;

import JessIntegrationModel.IResultsModel;
import JessIntegrationModel.Method;
import JessIntegrationModel.ProjectModel;

/**
 * Class that plays the role of the domain model in the TableViewerExample
 * In real life, this class would access a persistent store of some kind.
 * 
 */

public class SeedsClassGeneralModel implements IResultsModel{

	private List seedDescriptionList = new ArrayList();
	private List changeListenersSeedDescription = new ArrayList();
	
	private Map<SeedClassDescription,List<RelatedCallCountedDescription>> relatedMethodDescriptionList = new HashMap<SeedClassDescription,List<RelatedCallCountedDescription>>();
	private List changeListenersRelatedMethodDescription = new ArrayList();
	
	ProjectModel projectModel = null;

	
	/**
	 * Constructor
	 */
	public SeedsClassGeneralModel() {
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
	public List<RelatedCallCountedDescription> getRelatedMethodDescriptions(SeedClassDescription seedDescription) {
		return relatedMethodDescriptionList.get(seedDescription);
	}
	
	/**
	 * Add a new task to the collection of methodsDescriptions and tells all the listener to add the new methodDescription
	 */
	public void addTask() {
		SeedClassDescription seedDescription = new SeedClassDescription();
		seedDescriptionList.add(seedDescriptionList.size(), seedDescription);
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedClassDescriptionListViewer) iterator.next()).addSeedDescription(seedDescription);
	}

	public void addMethodAsASeed(SeedClassDescription et, List<RelatedCallCountedDescription> methodsCalls) {
		
		if (!pertenece(et)){
			seedDescriptionList.add(seedDescriptionList.size(), et);
			if (methodsCalls != null)
				this.relatedMethodDescriptionList.put(et, methodsCalls);
			Iterator iterator = changeListenersSeedDescription.iterator();
			while (iterator.hasNext())
				((SeedClassDescriptionListViewer) iterator.next()).addSeedDescription(et);
			
		}
		
	}
	
	private boolean pertenece(SeedClassDescription et) {
		for (Iterator i = seedDescriptionList.iterator(); i.hasNext(); ){
			SeedClassDescription seed = (SeedClassDescription)i.next();
			RedirectorFinderResults redirResul = seed.getRedirResul();
			String algorithm = seed.getAlgoritmo(); 
			if (redirResul.equals(et.getRedirResul()) && algorithm.equals(et.getAlgoritmo()))
				return true;
		}
		return false;
	}

	/**
	 * @param seedClassDescription
	 */
	public void removeMethodDescription(SeedClassDescription seedClassDescription) {
		seedDescriptionList.remove(seedClassDescription);
		relatedMethodDescriptionList.remove(seedClassDescription);
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedClassDescriptionListViewer) iterator.next()).removeSeedDescription(seedClassDescription);
		//debería iterar sobre los listener de las calls para avisar al modelo de removerlo
	}

	/**
	 * @param methodDescription
	 */
	public void seedDescriptionChanged(SeedClassDescription methodDescription) {
		Iterator iterator = changeListenersSeedDescription.iterator();
		while (iterator.hasNext())
			((SeedClassDescriptionListViewer) iterator.next()).updateSeedDescription(methodDescription);
//		for (Iterator i= methodsDescriptionList.iterator() ; i.hasNext() ;){
//			MethodDescription et = (MethodDescription)i.next();
//
//		}
	}

	/**
	 * @param relatedMethodDescription
	 */
	public void relatedMethodDescriptionChanged(RelatedCallCountedDescription relatedMethodDescription) {
		Iterator iterator = changeListenersRelatedMethodDescription.iterator();
		while (iterator.hasNext())
			((RelatedCallCountedDescriptionListViewer) iterator.next()).updateRelatedMethodDescription(relatedMethodDescription);
//		for (Iterator i= callDescription.iterator() ; i.hasNext() ;){
//			CallDescription et = (CallDescription)i.next();
//
//		}
	}
	

	/**
	 * @param viewer
	 */
	public void removeChangeListenerSeedDescription(SeedClassDescriptionListViewer viewer) {
		changeListenersSeedDescription.remove(viewer);
	}

	/**
	 * @param viewer
	 */
	public void addChangeListenerSeedDescription(SeedClassDescriptionListViewer viewer) {
		changeListenersSeedDescription.add(viewer);
	}

	
	/**
	 * It removes a listener of the type CallDescriptionListViewer
	 * @param viewer
	 */
	public void removeChangeListenerRelatedMethodDescription(RelatedCallCountedDescriptionListViewer viewer) {
		changeListenersRelatedMethodDescription.remove(viewer);
	}

	/**
	 * It adds a listener of the type CallDescriptionListViewer
	 * @param viewer
	 */
	public void addChangeListenerRelatedMethodDescription(RelatedCallCountedDescriptionListViewer viewer) {
		changeListenersRelatedMethodDescription.add(viewer);
	}
	
	
	public Map<SeedClassDescription, List<RelatedCallCountedDescription>> getRelatedMethods() {
		return relatedMethodDescriptionList;
	}
	
	public List<RelatedCallCountedDescription> getRelatedMethods(String methodId){
		
		return relatedMethodDescriptionList.get(methodId);
		
	}

	public void setRelatedMethods(Map<SeedClassDescription, List<RelatedCallCountedDescription>> calls) {
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
			for (Iterator<SeedClassDescription> methodIterator = seedDescriptionList.iterator(); methodIterator.hasNext() ;){
				SeedClassDescription md = methodIterator.next(); 
				archive.write(md.toString());
				archive.newLine();
				if (relatedMethodDescriptionList.containsKey(md)){
					
					List<RelatedCallCountedDescription> list = relatedMethodDescriptionList.get(md);
					for (Iterator<RelatedCallCountedDescription> callIterator=list.iterator();callIterator.hasNext();){
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
