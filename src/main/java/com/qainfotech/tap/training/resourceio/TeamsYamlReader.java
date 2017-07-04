package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader{
    
    /**
     * get a list of individual objects from db yaml file
     * 
     * @return 
     */
	Yaml yaml = new Yaml();
    List<Individual> individualList = new ArrayList<>();
    Map<String,ArrayList> subvalues;
    List<Individual> activeList=new ArrayList<Individual>();
    List<Individual> nonActiveList=new ArrayList<Individual>();
    List<Team> teamList = new ArrayList<>();
    public List<Individual> getListOfIndividuals()
    {
    	 try{
    		 individualList.clear();
    	     subvalues = (Map<String, ArrayList>) yaml.load(new FileInputStream(new File("src/main/resources/db.yaml")));
    	     }
    	 catch (FileNotFoundException e)
    	 {
    	             // TODO Auto-generated catch block
    	  e.printStackTrace();
    	 }
    	         Individual individual;
    	         Map<String , Object> map = new HashMap<>();
    	         ArrayList ind= (ArrayList) subvalues.get("individuals");
    	         for (int index = 0; index < ind.size(); index++)
    	         {

    	             Map individuals = (Map<String, ArrayList>)ind.get(index);
    	             int id = (Integer)individuals.get("id");
    	             map.put("id", id);
    	             String name=(String)individuals.get("name");
    	             map.put("name", name);
    	             boolean active=(boolean)individuals.get("active");
    	             map.put("active", active);
    	             individual=new Individual(map);


    	             individualList.add(individual);

    	         }

    	         return individualList;

    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException
    {
    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
    		this.getListOfIndividuals();

    		 for(int i=0;i<individualList.size();i++)
             {
                 if(individualList.get(i).getId()==(int)id)
                 {
                	 return(individualList.get(i));
                 }
                    
             }

    		throw new ObjectNotFoundException("Individual","id","100");
    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{

    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
    		this.getListOfIndividuals();

    		 for(int i=0;i<individualList.size();i++)
             {
                 if(individualList.get(i).getName().equals((String)name))
                 {
                	 return(individualList.get(i));
                 }
                    
             }

    		throw new ObjectNotFoundException("Individual","Name","Individual By This Name Does Not Exist");
    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals()
    {
    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
    		this.getListOfIndividuals();

    		 for(int i=0;i<individualList.size();i++)
             {
                 if(individualList.get(i).isActive()==false)
                 {
                	 nonActiveList.add(individualList.get(i));
                 }
                    
             }
    		 return (List<Individual>) nonActiveList;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals()
    {
    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
    		this.getListOfIndividuals();

    		 for(int i=0;i<individualList.size();i++)
             {
                 if(individualList.get(i).isActive()==true)
                 {
                	 activeList.add(individualList.get(i));
                 }
                    
             }
    		 return (List<Individual>) activeList;
    }
    
    /**
     * get a list of team objects from db yaml
     * 
     * @return 
     */
    @SuppressWarnings("unchecked")
	public List<Team> getListOfTeams()
    {
    	try{
   		 teamList.clear();
   	     subvalues = (Map<String, ArrayList>) yaml.load(new FileInputStream(new File("src/main/resources/db.yaml")));
   	     }
   	 catch (FileNotFoundException e)
   	 {
   	             // TODO Auto-generated catch block
   	  e.printStackTrace();
   	 }
   	         Team team;
   	         Map<String , Object> map = new HashMap<>();
   	         ArrayList teams= (ArrayList) subvalues.get("teams");
   	         for (int index = 0; index < teams.size(); index++)
   	         {
   	             Map team_yaml = (Map<String, ArrayList>)teams.get(index);
   	             int id = (Integer)team_yaml.get("id");
   	             map.put("id", id);
   	             String name=(String)team_yaml.get("name");
   	             map.put("name", name);
   	             List<Individual> members=(List<Individual>) team_yaml.get("members");
   	             map.put("members", members);
   	             team=new Team(map);
   	             teamList.add(team);

   	         }

   	         return teamList;
    }
}