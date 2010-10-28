package info.sargis.gedi.model.seg

import info.sargis.gedi.model.EDIInterchangeMessage
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 28, 2010
 */
class EDISegmentTest {

  EDISegment ediSegment

  @BeforeMethod
  public void setUp() {
    ediSegment = new EDISegment(new EDIInterchangeMessage())
  }

  @Test
  public void testToEDIWithStringTagData() throws Exception {
    ediSegment.with {
      tagName = "SRG"
      tagData = 1
    }

    ediSegment.data {
      "EL01" + [781112, 2356]
    }

    Assert.assertEquals(ediSegment.toEDI(), "SRG:1+EL01+781112:2356'$ediSegment.interchangeMessage.eol");
  }

  @Test
  public void testToEDIWithListTagData() throws Exception {
    ediSegment.with {
      tagName = "SRG"
      tagData = [2, "1"]
    }

    ediSegment.data {
      "EL01" + [781112, 2356]
    }

    Assert.assertEquals(ediSegment.toEDI(), "SRG:2:1+EL01+781112:2356'$ediSegment.interchangeMessage.eol");
  }

}
