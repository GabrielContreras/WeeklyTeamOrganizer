package com.mooglecake.service;

import com.google.api.client.util.Value;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.mooglecake.authentication.SheetsServiceUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SheetParser {
    private final Sheets sheetsService;
    private final String SPREADSHEET_ID = "1GJ-O4n5LHw_fQ2m0i17CBvHOm137SvvGr6fJfuG19Xw";

    public SheetParser() throws GeneralSecurityException, IOException {
        this.sheetsService = SheetsServiceUtil.getSheetsService();

    }

    public List<List<Object>> parseSheets() throws IOException {
        List<String> ranges = List.of("SingupSheet!A2:P20");
        String range = "SignupSheet!A2:P20";
//        BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
//                .batchGet(SPREADSHEET_ID)
//                .setRanges(ranges)
//                .execute();

        ValueRange test = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID,range)
                .execute();

        return test.getValues();
    }

}
