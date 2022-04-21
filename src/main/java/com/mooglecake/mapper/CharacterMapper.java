package com.mooglecake.mapper;

import com.mooglecake.Character;
import com.mooglecake.TimeSlot;
import com.mooglecake.service.SheetParser;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.DayOfWeek;
import java.util.*;

public class CharacterMapper {
    List<Character> charactersList = new ArrayList<>();


    public List<Character> getCharactersList() throws GeneralSecurityException, IOException {
        SheetParser sheetParser = new SheetParser();
        List<List<Object>> rangeList = sheetParser.parseSheets("SignupSheet!A2:P20");
        List<List<Object>> jobRoster = sheetParser.parseSheets("CharacterJobRoster!A1:B14");

        List<List<String>> stringParse = new ArrayList<>();
        List<List<String>> jobParse = new ArrayList<>();
        List<String> tempStringList;

        for(List<Object> listObject : rangeList){
            tempStringList = new ArrayList<>();
            for(Object object : listObject){
                tempStringList.add((String) object);
            }
            stringParse.add(tempStringList);
        }

        for(List<Object> listObject : jobRoster){
            tempStringList = new ArrayList<>();
            for(Object object : listObject){
                tempStringList.add((String) object);
            }
            jobParse.add(tempStringList);
        }

        //For timeslot generation
        String dayOfWeek = null, hourOfDay = null;
        List<TimeSlot> timeSlots = new ArrayList<>();
        List<String> dayOfWeekList = stringParse.get(0);
        List<String> hourOfDayList = stringParse.get(1);

        for(int i = 2; i < hourOfDayList.size(); i++){
            if(i < dayOfWeekList.size() && !dayOfWeekList.get(i).isEmpty()){
                dayOfWeek = dayOfWeekList.get(i);
            }
            hourOfDay = hourOfDayList.get(i);

            timeSlots.add(new TimeSlot(dayOfWeek,hourOfDay));
        }

        String characterName;
        List<String> jobList = new ArrayList<>();
        for(int i = 3 ; i < stringParse.size(); i++){
            List<String> row = stringParse.get(i);

            if(row.isEmpty())
                continue;

            characterName = row.get(0);

            if(characterName.isEmpty())
                continue;

            for(List<String> jobMap : jobParse){
                if(jobMap.get(0).equals(characterName)){
                    jobList = Arrays.stream(jobMap.get(1).split("\\|")).toList();
                }
            }
        }

        return null;
    }

}
