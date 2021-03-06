package com.mooglecake;


import com.mooglecake.enums.Job;
import com.mooglecake.enums.Role;
import com.mooglecake.enums.SubRole;

import java.util.*;

public class Character {
    private final String characterName;
    private final List<Job> jobList;
    private List<TimeSlot> availableTimeSlots = new ArrayList<>();
    private List<TimeSlot> availableViableTimeSlots = new ArrayList<>();
    private List<TimeSlot> scheduledTimeSlots = new ArrayList<>();
    private int numberOfScheduledRuns;
    private int numberOfAvailableRuns;
    private boolean scheduledAtLeastOnce;

    public Character(String characterName, ArrayList<Job> jobList, ArrayList<TimeSlot> availableTimeSlots) {
        this.characterName = characterName;
        this.jobList = jobList;
        this.numberOfScheduledRuns = 0;
        this.availableTimeSlots = availableTimeSlots;
        this.scheduledAtLeastOnce = false;
        this.scheduledTimeSlots = new ArrayList<>();
    }

    public void addAvailableViableTimeSlots(List<TimeSlot> availableTimeSlots){
        for(TimeSlot timeSlot : availableTimeSlots){
            if(this.availableTimeSlots.contains(timeSlot)){
                this.availableViableTimeSlots.add(timeSlot);
            }
        }
        numberOfAvailableRuns = availableViableTimeSlots.size();
    }

    public void increaseScheduledRunCount() {
        this.numberOfScheduledRuns += 1;
        if(numberOfScheduledRuns == 1){
            this.scheduledAtLeastOnce = true;
        }
    }

    public void decreaseScheduledRunCount(){
        if(this.numberOfScheduledRuns == 0)
            this.scheduledAtLeastOnce = false;
        else
            this.numberOfScheduledRuns -= 1;
    }

    public void addScheduleTimeSlot(TimeSlot timeSlot) {
        scheduledTimeSlots.add(timeSlot);
    }

    public Boolean isScheduledAtLeastOnce() {
        return scheduledAtLeastOnce;
    }

    public String getCharacterName() {
        return characterName;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public Job getSuitableJob(SubRole subRole){

        for(Job job : jobList){
            if(subRole == SubRole.getJobSubRole(job)){
                return job;
            }
        }
        return null;
    }

    public Boolean isAbleToRunSubRole(SubRole subRole){
        for(Job job : jobList){
            if(SubRole.getJobSubRole(job) == subRole)
                return true;
        }

        return false;
    }

    public int getNumberOfAvailableRuns() {
        return numberOfAvailableRuns;
    }

    public List<TimeSlot> getScheduledTimeSlots() {
        return scheduledTimeSlots;
    }

    public int getNumberOfSchedulesRuns() {
        return numberOfScheduledRuns;
    }

    public List<TimeSlot> getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    public List<TimeSlot> getAvailableViableTimeSlots() {
        return availableViableTimeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        return Objects.equals(characterName, character.characterName);
    }

    @Override
    public int hashCode() {
        return characterName != null ? characterName.hashCode() : 0;
    }
}
