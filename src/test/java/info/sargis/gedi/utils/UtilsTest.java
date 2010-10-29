package info.sargis.gedi.utils;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Sargis Harutyunyan
 * Date: 29 oct. 2010
 * Time: 21:01:06
 */
public class UtilsTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testCheckNotNullForNullValue() throws Exception {
        Utils.checkNotNull(null);
    }

    @Test
    public void testCheckNotNullForNotNullValue() throws Exception {
        Utils.checkNotNull(new Object());
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void tearDown() {
    }

}
