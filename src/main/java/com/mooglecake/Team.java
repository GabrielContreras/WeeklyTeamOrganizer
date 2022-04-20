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
    private TimeSlot timeSlot;

    public Boolean addPlayerToTeam(Character character, Job job, boolean softChecking, boolean replacementAcceptable){
        Character replacementCharacter = null;
        boolean replacementFound = false;
        if(partyComp.containsKey(character))
            return false;

        if(getJobRole(job) == Role.DPS){
            if(dps.size() < 4 && isJobSlotAvailable(job)) {
                if (isSubRoleSlotAvailable(getJobSubRole(job)) || softChecking) { // || (!isSubRoleSlotAvailable(getJobSubRole(job)) && dps.size() == 3 && getJobSubRole(job) == SubRole.MELEE
                    dps.add(character);
                    partyComp.put(character, job);
                    return true;
                }
            } else if(character.getAvailableViableTimeSlots() != null && replacementAcceptable){
                if(!isSubRoleSlotAvailable(getJobSubRole(job)) || dps.size() == 4){
                    int maxScheduled = 0, maxAvailable = 0;

                    //Check for similar jobs to swap if scheduled run count is lower for character attempting to be added
                    for(Character dpsChar : dps){
                        if(job == partyComp.get(dpsChar)){
                            if(dpsChar.getNumberOfSchedulesRuns() > character.getNumberOfSchedulesRuns() ||
                                    (dpsChar.getNumberOfSchedulesRuns() == character.getNumberOfSchedulesRuns() && dpsChar.getNumberOfAvailableRuns() > character.getNumberOfAvailableRuns())){
                                if(maxScheduled < dpsChar.getNumberOfSchedulesRuns() || (maxScheduled == dpsChar.getNumberOfSchedulesRuns() && maxAvailable < dpsChar.getNumberOfAvailableRuns())){
                                    maxScheduled = dpsChar.getNumberOfSchedulesRuns();
                                    maxAvailable = dpsChar.getNumberOfAvailableRuns();
                                    replacementCharacter = dpsChar;
                                }

                                replacementFound = true;
                            }
                        }
                    }
                    if(!replacementFound){
                        for(Character dpsChar : dps) {
                            if (getJobSubRole(job) == getJobSubRole(partyComp.get(dpsChar))) {
                                if (dpsChar.getNumberOfSchedulesRuns() > character.getNumberOfSchedulesRuns() ||
                                        (dpsChar.getNumberOfSchedulesRuns() == character.getNumberOfSchedulesRuns() && dpsChar.getNumberOfAvailableRuns() > character.getNumberOfAvailableRuns())) {
                                    if(maxScheduled < dpsChar.getNumberOfSchedulesRuns() || (maxScheduled == dpsChar.getNumberOfSchedulesRuns() && maxAvailable < dpsChar.getNumberOfAvailableRuns())){
                                        maxScheduled = dpsChar.getNumberOfSchedulesRuns();
                                        maxAvailable = dpsChar.getNumberOfAvailableRuns();
                                        replacementCharacter = dpsChar;
                                    }

                                    replacementFound = true;
                                }
                            }
                        }
                    }
                }
            }
        } else if (getJobRole(job) == Role.TANK){
            if(tanks.size() < 2 && isJobSlotAvailable(job)){
                tanks.add(character);
                partyComp.put(character, job);
                return true;
            } else if(character.getAvailableViableTimeSlots() != null && replacementAcceptable){
                if(!isSubRoleSlotAvailable(getJobSubRole(job)) || tanks.size() == 2){
                    int maxScheduled = 0, maxAvailable = 0;
                    //Check for similar jobs to swap if scheduled run count is lower for character attempting to be added
                    for(Character tank : tanks){
                        if(job == partyComp.get(tank)){
                            if(tank.getNumberOfSchedulesRuns() > character.getNumberOfSchedulesRuns() ||
                                    (tank.getNumberOfSchedulesRuns() == character.getNumberOfSchedulesRuns() && tank.getNumberOfAvailableRuns() > character.getNumberOfAvailableRuns())){
                                if(maxScheduled < tank.getNumberOfSchedulesRuns() || (maxScheduled == tank.getNumberOfSchedulesRuns() && maxAvailable < tank.getNumberOfAvailableRuns())){
                                    maxScheduled = tank.getNumberOfSchedulesRuns();
                                    maxAvailable = tank.getNumberOfAvailableRuns();
                                    replacementCharacter = tank;
                                }

                                replacementFound = true;
                            }
                        }
                    }
                    if(!replacementFound) {
                        for (Character tank : tanks) {
                            if (getJobSubRole(job) == getJobSubRole(partyComp.get(tank))) {
                                if (tank.getNumberOfSchedulesRuns() > character.getNumberOfSchedulesRuns() ||
                                        (tank.getNumberOfSchedulesRuns() == character.getNumberOfSchedulesRuns() && tank.getNumberOfAvailableRuns() > character.getNumberOfAvailableRuns())) {
                                    if(maxScheduled < tank.getNumberOfSchedulesRuns() || (maxScheduled == tank.getNumberOfSchedulesRuns() && maxAvailable < tank.getNumberOfAvailableRuns())){
                                        maxScheduled = tank.getNumberOfSchedulesRuns();
                                        maxAvailable = tank.getNumberOfAvailableRuns();
                                        replacementCharacter = tank;
                                    }

                                    replacementFound = true;
                                }
                            }
                        }
                    }
                }
            }

        } else if (getJobRole(job) == Role.HEALER ){
            if(healers.size() < 2 && isJobSlotAvailable(job) && isSubRoleSlotAvailable(getJobSubRole(job))){
                healers.add(character);
                partyComp.put(character, job);
                return true;
            } else if(character.getAvailableViableTimeSlots() != null && replacementAcceptable){
                if(!isSubRoleSlotAvailable(getJobSubRole(job)) || healers.size() == 2){
                    int maxScheduled = 0, maxAvailable = 0;

                    //Check for similar jobs to swap if scheduled run count is lower for character attempting to be added
                    for(Character healer : healers){
                        if(job == partyComp.get(healer)){
                            if(healer.getNumberOfSchedulesRuns() > character.getNumberOfSchedulesRuns() ||
                                    (healer.getNumberOfSchedulesRuns() == character.getNumberOfSchedulesRuns() && healer.getNumberOfAvailableRuns() > character.getNumberOfAvailableRuns())){
                                if(maxScheduled < healer.getNumberOfSchedulesRuns() || (maxScheduled == healer.getNumberOfSchedulesRuns() && maxAvailable < healer.getNumberOfAvailableRuns())){
                                    maxScheduled = healer.getNumberOfSchedulesRuns();
                                    maxAvailable = healer.getNumberOfAvailableRuns();
                                    replacementCharacter = healer;
                                }

                                replacementFound = true;
                            }
                        }
                    }
                    if(!replacementFound) {
                        for (Character healer : healers) {
                            if (getJobSubRole(job) == getJobSubRole(partyComp.get(healer))) {
                                if (healer.getNumberOfSchedulesRuns() > character.getNumberOfSchedulesRuns() ||
                                        (healer.getNumberOfSchedulesRuns() == character.getNumberOfSchedulesRuns() && healer.getNumberOfAvailableRuns() > character.getNumberOfAvailableRuns())) {
                                    if(maxScheduled < healer.getNumberOfSchedulesRuns() || (maxScheduled == healer.getNumberOfSchedulesRuns() && maxAvailable < healer.getNumberOfAvailableRuns())){
                                        maxScheduled = healer.getNumberOfSchedulesRuns();
                                        maxAvailable = healer.getNumberOfAvailableRuns();
                                        replacementCharacter = healer;
                                    }

                                    replacementFound = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(replacementFound){
            if(healers.contains(replacementCharacter)){
                healers.add(character);
                healers.remove(replacementCharacter);
            } else if (tanks.contains(replacementCharacter)){
                tanks.add(character);
                tanks.remove(replacementCharacter);
            } else if (dps.contains(replacementCharacter)) {
                dps.add(character);
                dps.remove(replacementCharacter);
            }
            partyComp.remove(replacementCharacter);
            partyComp.put(character, job);
        }

        return replacementFound;
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
        int meleeCount = 0, rangedCount = 0;
        for(Job job : partyComp.values()){
            if(getJobSubRole(job).equals(subRole)) {
                if (meleeCount == 0 && (subRole == SubRole.MELEE)) {
                    meleeCount++;
                    continue;
                }
                if (rangedCount == 0 && (subRole == SubRole.CASTER || subRole == SubRole.RANGED)){
                    rangedCount++;
                    continue;
                }
                return false;
            }
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

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
