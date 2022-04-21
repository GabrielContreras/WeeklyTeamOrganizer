package com.mooglecake.mapper;

import com.mooglecake.Character;
import com.mooglecake.TimeSlot;
import com.mooglecake.service.SheetParser;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterMapper {
    List<Character> charactersList = new ArrayList<>();


    public List<Character> getCharactersList() throws GeneralSecurityException, IOException {
        SheetParser sheetParser = new SheetParser();
        List<List<Object>> rangeList = sheetParser.parseSheets();

        List<List<String>> stringParse = new ArrayList<>();
        List<String> tempStringList = new ArrayList<>();

        for(List<Object> listObject : rangeList){
            tempStringList = new ArrayList<>();
            for(Object object : listObject){
                tempStringList.add((String) object);
            }
            stringParse.add(tempStringList);
        }

        //For timeslot generation
        String dayOfWeek = null;
        List<TimeSlot> timeSlots = new ArrayList<>();

        for(String dayCell : stringParse.get(0)){
            if(!dayCell.isEmpty()){
                dayOfWeek = dayCell;
            }
            for(String timeCell : stringParse.get(1)){
                timeSlots.add(new TimeSlot(dayOfWeek,timeCell));
            }
        }


        return null;
    }

}
