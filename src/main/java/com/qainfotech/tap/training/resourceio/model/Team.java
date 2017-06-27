package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    List<Individual> activeteam=new ArrayList<Individual>();
    
    public Team(Map<String, Object> teamMap){
    	this.name=(String)teamMap.get("name");
		this.id=Integer.parseInt((String)teamMap.get("id").toString());
		this.members=(List<Individual>)teamMap.get("members");
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
     activeteam=ob.get_active_team();
     return activeteam;
     
    }
        
    /**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers(){
        throw new UnsupportedOperationException("Not implemented.");
    }
}
