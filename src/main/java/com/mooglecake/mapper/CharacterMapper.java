package com.mooglecake.mapper;

import com.mooglecake.Character;
import com.mooglecake.service.SheetParser;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {
    List<Character> charactersList = new ArrayList<>();


    public List<Character> getCharactersList() throws GeneralSecurityException, IOException {
        SheetParser sheetParser = new SheetParser();
        List<List<Object>> rangeList = sheetParser.parseSheets();

        List<List<String>> stringParse = new ArrayList<>();
        List<String> tempStringList = new ArrayList<>();

        for(List<Object> listObject : rangeList){
            for(Object object : listObject){
                tempStringList.add((String) object);
            }
            stringParse.add(tempStringList);
        }

        return null;
    }

}
