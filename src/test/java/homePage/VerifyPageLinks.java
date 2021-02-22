package homePage;

import TestUtilities.PageActions;
import org.testng.annotations.Test;


public class VerifyPageLinks {

    @Test
    public void chkPageLinksActive() throws Exception {

        PageActions.navigateToURL();

        PageActions.verifyActiveLinks();

    }
}
