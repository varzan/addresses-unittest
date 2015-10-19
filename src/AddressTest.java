/**
 * Created by razvan on 19/10/15.
 */
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import org.junit.Test;
import com.google.gdata.client.spreadsheet.SpreadsheetService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    private final String SPREADSHEET_URL = "1b7wO6eGc-cBYevUpM4xfoKF2J8taHWug2p9zu4k2K5U";

    @Test
    public void testAddresses()
    {
        SpreadsheetService ss = new SpreadsheetService("AddressTest");
        /*try {
            ss.setUserCredentials("public", "basic");
        } catch (AuthenticationException e) {
            System.err.println("auth error: " + e);
        }*/
        try {
            String spreadsheetKey = SPREADSHEET_URL;
            URL feedUrl = FeedURLFactory.getDefault().getWorksheetFeedUrl(spreadsheetKey, "public", "basic");
            WorksheetFeed feed = ss.getFeed(feedUrl, WorksheetFeed.class);
            List<WorksheetEntry> worksheets = feed.getEntries();
            WorksheetEntry worksheet = worksheets.get(0);
            URL listFeedUrl = worksheet.getListFeedUrl();
            ListFeed listFeed = ss.getFeed(listFeedUrl, ListFeed.class);
            int i = 0;
            for (ListEntry entry : listFeed.getEntries())
            {
                System.out.println("row " + i);
                for (String tag : entry.getCustomElements().getTags())
                {
                    System.out.println(">>>>" + tag + " : " + entry.getCustomElements().getValue(tag));
                }
                i++;
            }
        } catch(MalformedURLException ex) {
            System.err.println("Exception: " + ex);
        } catch (IOException ex) {
            System.err.println("Exception: " + ex);
        } catch (ServiceException ex) {
            System.err.println("Exception: " + ex);
        }
        assertEquals("The Universe is broken", 2 + 2, 4);
    }
}
