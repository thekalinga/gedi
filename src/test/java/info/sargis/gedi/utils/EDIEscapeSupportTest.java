package info.sargis.gedi.utils;

import info.sargis.gedi.builder.model.una.UNASegment;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 4, 2010
 */
public class EDIEscapeSupportTest {

    private EDIEscapeSupport ediEscapeSupport;

    @BeforeMethod
    public void setUp() {
        ediEscapeSupport = new EDIEscapeSupport(new UNASegment());
    }

    @Test
    public void testEscape() throws Exception {
        String data = "SS+XXX:TT65?55-1135CC'RR+TTT+XX+2333";
        Assert.assertEquals(ediEscapeSupport.escape(data), "SS?+XXX?:TT65??55-1135CC?'RR?+TTT?+XX?+2333");

    }

    @Test
    public void testUnescape() throws Exception {
        String escapedData = "SS?+XXX?:TT65??55-1135CC?'RR?+TTT?+XX?+2333";
        Assert.assertEquals(ediEscapeSupport.unescape(escapedData), "SS+XXX:TT65?55-1135CC'RR+TTT+XX+2333");
    }

    @Test
    public void testEscapeAndUnescape() throws Exception {
        String data = "SS+XXX:TT65?55-1135CC'RR+TTT+XX+2333";
        Assert.assertEquals(ediEscapeSupport.unescape(ediEscapeSupport.escape(data)), data);
    }

    @AfterMethod
    public void tearDown() {
    }

}
