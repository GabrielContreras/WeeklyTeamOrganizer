package com.mooglecake.mapper;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;

class CharacterMapperTest {

    @org.junit.jupiter.api.Test
    void getCharactersList() throws GeneralSecurityException, IOException {
        CharacterMapper characterMapper = new CharacterMapper();
        characterMapper.getCharactersList();
    }
}