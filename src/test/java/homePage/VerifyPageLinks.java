package homePage;

import TestUtilities.PageActions;
import org.testng.annotations.Test;


public class VerifyPageLinks {

    //This test verifies page links
    @Test
    public void chkPageLinksActive() throws Exception {

        PageActions.navigateToURL();

        PageActions.verifyActiveLinks();

    }
}
