import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.mooglecake.authentication.SheetsServiceUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetsLiveTest {

        private static Sheets sheetsService;
        private static String SPREADSHEET_ID = "1GJ-O4n5LHw_fQ2m0i17CBvHOm137SvvGr6fJfuG19Xw";// ...

        @BeforeClass
        public static void setup() throws GeneralSecurityException, IOException {
            sheetsService = SheetsServiceUtil.getSheetsService();
        }

        @Test
        public void testReadingCells() throws IOException {
                List<String> ranges = Arrays.asList("A5","B19");
                BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                        .batchGet(SPREADSHEET_ID)
                        .setRanges(ranges)
                        .execute();

                ValueRange cell = readResult.getValueRanges().get(0);
                System.out.println(cell.getValues().get(0).get(0));

        }

}
