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
    Assert.assertEquals("10:30:20+20:XX:60", EDIDSLCategory.plus([10, 30, 20], [20, "XX", 60]).toString())
  }

  @Test
  public void testPlusListAndString() throws Exception {
    Assert.assertEquals("10:30:20+SRG", EDIDSLCategory.plus([10, 30, 20], "SRG").toString())
  }

  @Test
  public void testPlusListAndEDIString() throws Exception {
    Assert.assertEquals(
            "10:30:20+SRG", EDIDSLCategory.plus([10, 30, 20], new EDIString("SRG")).toString()
    )
  }

  @Test
  public void testPlusListAndNumber() throws Exception {
    Assert.assertEquals("10:30:20+100", EDIDSLCategory.plus([10, 30, 20], 100).toString())
  }



  @Test
  public void testPlusNumbers() throws Exception {
    Assert.assertEquals("200+1000", EDIDSLCategory.plus(200, 1000).toString())
  }

  @Test
  public void testPlusNumberAndString() throws Exception {
    Assert.assertEquals("5000+SRG", EDIDSLCategory.plus(5000, "SRG").toString())
  }

  @Test
  public void testPlusNumberAndEDIString() throws Exception {
    Assert.assertEquals(
            "5000+SRG", EDIDSLCategory.plus(5000, new EDIString("SRG")).toString()
    )
  }

  @Test
  public void testPlusNumberAndList() throws Exception {
    Assert.assertEquals("100+10::20", EDIDSLCategory.plus(100, [10, "", 20]).toString())
  }



  @Test
  public void testPlusStrings() throws Exception {
    Assert.assertEquals("SRG+XXXSSD", EDIDSLCategory.plus("SRG", "XXXSSD").toString())
  }

  @Test
  public void testPlusStringAndEDIString() throws Exception {
    Assert.assertEquals(
            "SRG+XXXSSD", EDIDSLCategory.plus("SRG", new EDIString("XXXSSD")).toString()
    )
  }

  @Test
  public void testPlusStringAndNumber() throws Exception {
    Assert.assertEquals("SRG+5000", EDIDSLCategory.plus("SRG", 5000).toString())
  }

  @Test
  public void testPlusStringAndList() throws Exception {
    Assert.assertEquals("DDD50PO+10:XX:", EDIDSLCategory.plus("DDD50PO", [10, "XX", ""]).toString())
  }



  @Test
  public void testPlusEDIStrings() throws Exception {
    Assert.assertEquals(
            "SRG+XXXSSD", EDIDSLCategory.plus(new EDIString("SRG"), new EDIString("XXXSSD")).toString())
  }

  @Test
  public void testPlusEDIStringAndString() throws Exception {
    Assert.assertEquals(
            "SRG+XXXSSD", EDIDSLCategory.plus(new EDIString("SRG"), "XXXSSD").toString()
    )
  }

  @Test
  public void testPlusEDIStringAndNumber() throws Exception {
    Assert.assertEquals(
            "SRG+5000", EDIDSLCategory.plus(new EDIString("SRG"), 5000).toString()
    )
  }

  @Test
  public void testPlusEDIStringAndList() throws Exception {
    Assert.assertEquals(
            "DDD50PO+10:XX:", EDIDSLCategory.plus(new EDIString("DDD50PO"), [10, "XX", ""]).toString()
    )
  }



  @Test
  public void testFormatNumber() throws Exception {
    Assert.assertEquals("1000.326", EDIDSLCategory.formatNumber(1000.326, '.' as char))
    Assert.assertEquals("1000,32665489723", EDIDSLCategory.formatNumber(1000.32665489723, ',' as char))

    Assert.assertEquals("2000", EDIDSLCategory.formatNumber(2000.00, '.' as char))
    Assert.assertEquals("2000", EDIDSLCategory.formatNumber(2000, '.' as char))
  }

}
