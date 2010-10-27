package info.sargis.gedi

import junit.framework.Assert
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 27, 2010
 */
class EDIDSLCategoryTest {

  @Test
  public void testPlusLists() throws Exception {
    Assert.assertEquals("10:30:20+20:XX:60", EDIDSLCategory.plus([10, 30, 20], [20, "XX", 60]))
  }

  @Test
  public void testPlusListAndString() throws Exception {
    Assert.assertEquals("10:30:20+SRG", EDIDSLCategory.plus([10, 30, 20], "SRG"))
  }

  @Test
  public void testPlusListAndNumber() throws Exception {
    Assert.assertEquals("10:30:20+100", EDIDSLCategory.plus([10, 30, 20], 100))
  }

  @Test
  public void testPlusNumbers() throws Exception {
    Assert.assertEquals("200+1000", EDIDSLCategory.plus(200, 1000))
  }

  @Test
  public void testPlusNumberAndString() throws Exception {
    Assert.assertEquals("5000+SRG", EDIDSLCategory.plus(5000, "SRG"))
  }

  @Test
  public void testPlusNumberAndList() throws Exception {
    Assert.assertEquals("100+10::20", EDIDSLCategory.plus(100, [10, "", 20]))
  }

  @Test
  public void testPlusStrings() throws Exception {
    Assert.assertEquals("SRG+XXXSSD", EDIDSLCategory.plus("SRG", "XXXSSD"))
  }

  @Test
  public void testPlusStringAndNumber() throws Exception {
    Assert.assertEquals("SRG+5000", EDIDSLCategory.plus("SRG", 5000))
  }

  @Test
  public void testPlusStringAndList() throws Exception {
    Assert.assertEquals("DDD50PO+10:XX:", EDIDSLCategory.plus("DDD50PO", [10, "XX", ""]))
  }

  @Test
  public void testFormatNumber() throws Exception {
    Assert.assertEquals("1000.326", EDIDSLCategory.formatNumber(1000.326, '.' as char))
    Assert.assertEquals("1000,32665489723", EDIDSLCategory.formatNumber(1000.32665489723, ',' as char))

    Assert.assertEquals("2000", EDIDSLCategory.formatNumber(2000.00, '.' as char))
    Assert.assertEquals("2000", EDIDSLCategory.formatNumber(2000, '.' as char))
  }

}
