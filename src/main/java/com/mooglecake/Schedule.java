package com.mooglecake;

import com.mooglecake.enums.Job;
import com.mooglecake.enums.SubRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    private List<Team> scheduledTeams = new ArrayList<>();
    private List<TimeSlot> availableTimeSlots = new ArrayList<>();
    private final List<TimeSlot> totalTimeSlots;
    private final List<Character> characterRoster;
    private boolean softChecking;

    public Schedule(List<Character> characterRoster, List<TimeSlot> totalTimeSlots, boolean softChecking) {
        this.characterRoster = characterRoster;
        this.totalTimeSlots = totalTimeSlots;
        this.softChecking = softChecking;
        generateAvailableTimeSlots();
        for(Character character : this.characterRoster){
            character.addAvailableViableTimeSlots(this.availableTimeSlots);
        }
        for(TimeSlot timeSlot : this.availableTimeSlots){
            generateTeam(this.characterRoster, timeSlot, false);
        }
    }

    public List<Team> getScheduledTeams() {
        return scheduledTeams;
    }

    private void generateAvailableTimeSlots(){
        for(TimeSlot slot : totalTimeSlots){
            if(isTimeSlotViable(this.characterRoster, slot)){
                availableTimeSlots.add(slot);
            }
        }
    }

    private boolean isTimeSlotViable(List<Character> characterRoster, TimeSlot timeSlot){
        return generateTeam(characterRoster,timeSlot, true);
    }

    private Boolean isPartyViable(Map<SubRole,Integer> roleCount){
        if( (roleCount.get(SubRole.CASTER) + roleCount.get(SubRole.RANGED) + roleCount.get(SubRole.MELEE)) < 4){
            return false;
        } else if (roleCount.get(SubRole.PURE) < 1){
            return false;
        } else if (roleCount.get(SubRole.SHIELD) < 1){
            return false;
        } else return roleCount.get(SubRole.TANK) >= 2;
    }

    public Boolean generateTeam(List<Character> characterRoster, TimeSlot timeSlot, Boolean validationOnly){
        Map<SubRole,Integer> roleMapCount = new HashMap<>();
        Map<SubRole,Boolean> characterRoleCheck = new HashMap<>();
        List<Character> tempCharacterRoster = new ArrayList<>();
        Team teamValidator = new Team();

        //count of how many roles each person qualifies for
        List<List<Character>> listOfCharacterRoleCount = new ArrayList<>();
        for(int i = 0; i < SubRole.values().length; i++){
            listOfCharacterRoleCount.add(i, new ArrayList<>());
        }

        int characterRoleCount;

        //populate maps for sub role counts
        for(SubRole subRole : SubRole.values()){
            roleMapCount.put(subRole, 0);
            characterRoleCheck.put(subRole, false);
        }

        //creates temporary list of characters that are available for tested time slot
        for(Character character : characterRoster){
            if(character.getAvailableTimeSlots().contains(timeSlot)) {
                tempCharacterRoster.add(character);
            }
        }

        //fails if not enough characters for full party
        if(tempCharacterRoster.size() < 8)
            return false;

        //lists number of characters that can play each sub role
        for(Character character : tempCharacterRoster){
            characterRoleCheck.replaceAll((k,v) -> v = false);
            characterRoleCount = 0;
            for(Job job : character.getJobList()){
                SubRole subRole = SubRole.getJobSubRole(job);
                if(characterRoleCheck.get(subRole).equals(false)){
                    characterRoleCheck.replace(subRole, true);
                    roleMapCount.merge(subRole, 1, Integer::sum);
                    characterRoleCount++;
                }
            }
            listOfCharacterRoleCount.get(characterRoleCount - 1).add(character);
        }

        //checks if the minimum amount of roles is available for each role
        if(!isPartyViable(roleMapCount))
            return false;

        //sort roles by # of potential candidates
        List<Map.Entry<SubRole,Integer>> sortedSubRoleList = new ArrayList<>(roleMapCount.entrySet());
        sortedSubRoleList.sort(Map.Entry.comparingByValue());

        //Try to add characters to party depending on role without replacement
        for(Map.Entry<SubRole, Integer> subRoleEntry : sortedSubRoleList){
            SubRole subRole = subRoleEntry.getKey();
            for(List<Character> listOfCharacters : listOfCharacterRoleCount){
                for(Character character : listOfCharacters){
                    if(character.isAbleToRunSubRole(subRole)) {
                        teamValidator.addPlayerToTeam(character, character.getSuitableJob(subRole), false, false);
                    }
                }
            }
        }

        if(this.softChecking){
            for(Map.Entry<SubRole, Integer> subRoleEntry : sortedSubRoleList){
                SubRole subRole = subRoleEntry.getKey();
                for(List<Character> listOfCharacters : listOfCharacterRoleCount){
                    for(Character character : listOfCharacters){
                        if(character.isAbleToRunSubRole(subRole)) {
                            teamValidator.addPlayerToTeam(character, character.getSuitableJob(subRole), true, false);
                        }
                    }
                }
            }
        }

        //Run again and replace anyone that has run more often than others if possible
        for(Map.Entry<SubRole, Integer> subRoleEntry : sortedSubRoleList){
            SubRole subRole = subRoleEntry.getKey();
            for(List<Character> listOfCharacters : listOfCharacterRoleCount){
                for(Character character : listOfCharacters){
                    if(character.isAbleToRunSubRole(subRole)) {
                        teamValidator.addPlayerToTeam(character, character.getSuitableJob(subRole), false, true);
                    }
                }
            }
        }


        if(validationOnly){
            return teamValidator.isPartyFull();
        } else {
            if(teamValidator.isPartyFull()){
                for(Character character : teamValidator.getPartyComp().keySet()){
                    character.increaseScheduledRunCount();
                }
                this.scheduledTeams.add(teamValidator);
                teamValidator.setTimeSlot(timeSlot);
                return true;
            }
            return false;
        }
    }

    public List<TimeSlot> getAvailableTimeSlots() {
        return availableTimeSlots;
    }


}
