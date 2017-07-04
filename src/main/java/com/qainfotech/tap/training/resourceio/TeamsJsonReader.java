package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
	JSONParser parser=new JSONParser();
	Object obj;
	List<Individual> individualList=new ArrayList<Individual>();
    List<Individual> active_individuals=new ArrayList<Individual>();
    List<Individual> non_active_individuals=new ArrayList<Individual>();
    static List<Team> teamlist=new ArrayList<Team>();
    List<Individual> activeteamlist=new ArrayList<Individual>();
    JSONArray arr;
    
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     */

    
    public List<Individual> getListOfIndividuals()
    {
	
    	try {
    		individualList.clear();
			obj=parser.parse(new FileReader("C:/Users/prakhersrivastava/git/assignment-resource-io/src/main/resources/db.json"));
			JSONObject jsonobj=(JSONObject)obj;
    		arr=(JSONArray) jsonobj.get("individuals");
		    }
    	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
    	{
        throw new UnsupportedOperationException("Not implemented.");
    	}

	    JSONObject ob;
		Map<String, Object> map;
		for (int i = 0; i < arr.size(); i++) 
		{
			ob = (JSONObject) arr.get(i);
			map = (Map<String, Object>) ob.clone();
			Individual individual = new Individual(map);
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
    	     List<Individual> ind=getListOfIndividuals();
    		 for(int i=0;i<ind.size();i++)
             {
                 if(ind.get(i).getId()==(int)id)
                 {
                	 return(ind.get(i));
                 }
                    
             }

    		throw new ObjectNotFoundException(null,null,null);
    	}
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException
    {
    	individualList.clear();
    	if(this.getListOfIndividuals()==null)
    		this.getListOfIndividuals();

    		 for(int i=0;i<individualList.size();i++)
             {
                 if(individualList.get(i).getName().equals(name))
                 {
                	 return(individualList.get(i));
                 }
                    
             }
    		throw new ObjectNotFoundException(null,null,null);
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
                	 non_active_individuals.add(individualList.get(i));
                 }
                    
             }
//    		 System.out.println(non_active_individuals);

    		 return (List<Individual>)non_active_individuals;
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
                	 active_individuals.add(individualList.get(i));
                 }
                    
             }

    		 return (List<Individual>)active_individuals;
    	}
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     * @throws ObjectNotFoundException 
     */
    public List<Team> getListOfTeams() throws ObjectNotFoundException
    {  	
    	
			try {
				teamlist.clear();
				obj=parser.parse(new FileReader("C:/Users/prakhersrivastava/git/assignment-resource-io/src/main/resources/db.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jsonobj=(JSONObject)obj;
    		arr=(JSONArray) jsonobj.get("teams");
    		JSONObject ob;
    		Map<String, Object> map;
    		JSONArray ar=new JSONArray();
    		
    		for (int i = 0; i < arr.size(); i++) 
    		{
    			ArrayList<Individual> teamjson=new ArrayList<>();
    			ob = (JSONObject) arr.get(i);
    			//System.out.println(ob);
    			map = (Map<String, Object>) ob.clone();
    			//System.out.println(map);
    			ar=(JSONArray) ob.get("members");
    			TeamsJsonReader reader = new TeamsJsonReader();
    			for(int index=0;index<ar.size();index++)
    			{
    				int j=Integer.parseInt(ar.get(index).toString());
    				teamjson.add(reader.getIndividualById(j));
    			}
    			map.put("members",teamjson);
    			Team team=new Team(map);
    			teamlist.add(team);    		
    		}
    	
    		return teamlist;
    }
   
}