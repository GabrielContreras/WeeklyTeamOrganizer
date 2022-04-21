package com.mooglecake;

import com.mooglecake.enums.Job;

import java.sql.Array;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<TimeSlot> timeSlots = new ArrayList<>();
//        timeSlots.add(new TimeSlot(DayOfWeek.TUESDAY, 18));
//        timeSlots.add(new TimeSlot(DayOfWeek.WEDNESDAY, 17));
        timeSlots.add(new TimeSlot("Wednesday", "19"));
//        timeSlots.add(new TimeSlot(DayOfWeek.THURSDAY, 17));
//        timeSlots.add(new TimeSlot(DayOfWeek.THURSDAY, 19));
//        timeSlots.add(new TimeSlot(DayOfWeek.FRIDAY, 14));
//        timeSlots.add(new TimeSlot(DayOfWeek.FRIDAY, 17));
        timeSlots.add(new TimeSlot("Friday", "19"));
//        timeSlots.add(new TimeSlot(DayOfWeek.SATURDAY, 9));
//        timeSlots.add(new TimeSlot(DayOfWeek.SATURDAY, 13));
        timeSlots.add(new TimeSlot("Saturday", "19"));
        timeSlots.add(new TimeSlot("Monday", "19"));
//        timeSlots.add(new TimeSlot(DayOfWeek.SUNDAY, 9));
//        timeSlots.add(new TimeSlot(DayOfWeek.SUNDAY, 13));
//        timeSlots.add(new TimeSlot(DayOfWeek.SUNDAY, 19));

        List<Character> characterRoster = new ArrayList<>();
        characterRoster.add(new Character("Althalaus",
                new ArrayList<Job>(Arrays.asList(Job.RDM)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Aurelia",
                new ArrayList<Job>(Arrays.asList(Job.AST, Job.BRD, Job.RPR)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Elsten",
                new ArrayList<Job>(Arrays.asList(Job.PLD)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Grym",
                new ArrayList<Job>(Arrays.asList(Job.SCH)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Kuma",
                new ArrayList<Job>(Arrays.asList(Job.SCH)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Lena",
                new ArrayList<Job>(Arrays.asList(Job.GNB,Job.BRD)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Mijin",
                new ArrayList<Job>(Arrays.asList(Job.MNK,Job.RDM)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(3),timeSlots.get(1)))));

//        characterRoster.add(new Character("Squishy",
//                new ArrayList<Job>(Arrays.asList(Job.WAR)),
//                new ArrayList<TimeSlot>(Arrays.asList(null))));

        characterRoster.add(new Character("Taei",
                new ArrayList<Job>(Arrays.asList(Job.DRK)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Wolf",
                new ArrayList<Job>(Arrays.asList(Job.WHM)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(3),timeSlots.get(0),timeSlots.get(1)))));

        characterRoster.add(new Character("Xeno",
                new ArrayList<Job>(Arrays.asList(Job.NIN)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(3),timeSlots.get(0)))));

        characterRoster.add(new Character("Yuma",
                new ArrayList<Job>(Arrays.asList(Job.BLM)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

        characterRoster.add(new Character("Zan",
                new ArrayList<Job>(Arrays.asList(Job.RPR)),
                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));

//        characterRoster.add(new Character("Zelfie",
//                new ArrayList<Job>(Arrays.asList(Job.NIN)),
//                new ArrayList<TimeSlot>(Arrays.asList(timeSlots.get(0),timeSlots.get(1),timeSlots.get(2),timeSlots.get(3)))));


        Schedule scheduleForWeek = new Schedule(characterRoster,timeSlots, true);

        List<Team> teamSchedule = scheduleForWeek.getScheduledTeams();

        List<TimeSlot> availableTimeSlots = scheduleForWeek.getAvailableTimeSlots();

//        System.out.println("Available Time Slots: ");
//        for(TimeSlot timeSlot : availableTimeSlots){
//            System.out.println("Day: " + timeSlot.getWeekday() + ", Hour (PST): " + timeSlot.getHourOfDay());
//        }

        System.out.println("Scheduled Teams");
        for(Team teams : scheduleForWeek.getScheduledTeams()){
            System.out.println(teams.getTimeSlot().getWeekday() + "  Hour: " + teams.getTimeSlot().getHourOfDay());
            for(Character character : teams.getPartyComp().keySet()){
                System.out.println(character.getCharacterName() + "   Role: " + teams.getPartyComp().get(character));
            }
            System.out.println();
        }

        System.out.println("Schedule Run Balance | Possible Run Days");
        for(Character character : characterRoster){
            System.out.println(character.getCharacterName() + ": " + character.getNumberOfSchedulesRuns() + " | " +
                    character.getNumberOfAvailableRuns());
        }

    }

}
