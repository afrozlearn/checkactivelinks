package TestUtilities;



public class PageActions {

    //Navigates to URL
    public static void navigateToURL() throws Exception {

        GenericUtility.setupBrowser();
        String browserURL=GenericUtility.getPropValue("pageURL");
        GenericUtility.navigatetoURL(browserURL);
    }

    //Verifies active links on webpage
    public static  void verifyActiveLinks()
    {
        GenericUtility.chkActiveLinks();
    }
}
