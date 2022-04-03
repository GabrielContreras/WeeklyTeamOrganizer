package com.mooglecake;

import com.mooglecake.enums.Job;
import com.mooglecake.enums.Role;
import com.mooglecake.enums.SubRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mooglecake.enums.Role.getJobRole;
import static com.mooglecake.enums.SubRole.getJobSubRole;

public class Team {
    private List<Character> tanks = new ArrayList<>();
    private List<Character> healers = new ArrayList<>();
    private List<Character> dps = new ArrayList<>();
    private Map<Character,Job> partyComp = new HashMap<>();
    private Boolean fullParty, halfParty;

    public Boolean addPlayerToTeam(Character character, Job job){
        if(partyComp.containsKey(character))
            return false;

        if(getJobRole(job) == Role.DPS && dps.size() < 4 && isJobSlotAvailable(job)){
            if (isSubRoleSlotAvailable(getJobSubRole(job)) || (!isSubRoleSlotAvailable(getJobSubRole(job)) && dps.size() == 3)) {
                dps.add(character);
                partyComp.put(character, job);
                return true;
            }
        } else if (getJobRole(job) == Role.TANK && tanks.size() < 2 && isJobSlotAvailable(job)){
            tanks.add(character);
            partyComp.put(character, job);
            return true;
        } else if (getJobRole(job) == Role.HEALER && healers.size() < 2 && isJobSlotAvailable(job) && isSubRoleSlotAvailable(getJobSubRole(job))){
            healers.add(character);
            partyComp.put(character, job);
            return true;
        }
        return false;
    }

    public Boolean isPartyFull(){
        return partyComp.size() == 8;
    }

    public void removePlayerFromTeam(Character character){
        partyComp.remove(character);
        tanks.remove(character);
        healers.remove(character);
        dps.remove(character);
    }

    private Boolean isSubRoleSlotAvailable(SubRole subRole){
        for(Job job : partyComp.values()){
            if(getJobSubRole(job).equals(subRole))
                return false;
        }
        return true;
    }

    private Boolean isJobSlotAvailable(Job job){
        for(Job partyJob : partyComp.values()){
            if(partyJob.equals(job))
                return false;
        }
        return true;
    }

    public Map<Character, Job> getPartyComp() {
        return partyComp;
    }
}
