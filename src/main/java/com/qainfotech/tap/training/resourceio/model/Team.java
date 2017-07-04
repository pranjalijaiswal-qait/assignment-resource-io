package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
    
    private final String name;
    private final Integer id;
    private final List<Individual> members;
    TeamsJsonReader ob=new TeamsJsonReader();
    
    public Team(Map<String, Object> teamMap){
    	this.name=(String)teamMap.get("name");
		this.id=Integer.parseInt((String)teamMap.get("id").toString());
		this.members=(List<Individual>) teamMap.get("members");
		
    }
    
    /**
     * get team name
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * get team id
     * 
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /** 
     * get list of individuals that are members of this team
     * 
     * @return 
     */
    public List<Individual> getMembers(){
        return members;
    }
    
    /**
     * get a list of individuals that are members of this team and are also active
     * 
     * @return 
     */
    public List<Individual> getActiveMembers()
    {
    	List<Individual> activeList=new ArrayList<Individual>();
    	 Iterator<Individual> itr=this.members.iterator();
          while(itr.hasNext()){

              Individual individual=itr.next();
              if(individual.isActive())
              {
                  activeList.add(individual);
              }

          }
           return activeList;
     
    }
        
    /**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers()
    {
     List<Individual> nonactiveList=new ArrayList<Individual>();
  	  Iterator<Individual> itr=this.members.iterator();
  	  Individual individual;
        while(itr.hasNext())
        {
        	individual=itr.next();
            if(!(individual.isActive()))
            {
                nonactiveList.add(individual);
            }

        }
         return nonactiveList;
    }
}
