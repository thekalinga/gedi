package info.sargis.gedi.model

import org.testng.Assert
import org.testng.annotations.Test
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 22, 2010
 */
class UNASegmentTest {

  @Test
  public void testDefaultUNASegment() throws Exception {
    Assert.assertEquals(new UNASegment().toEDI(), "UNA:+.? '${EOL}");
  }

  @Test
  public void testCustomUNASegment() throws Exception {
    UNASegment una = new UNASegment(cdes: "|", des: "-", decn: ",", ri: "!", rs: " ", st: "^")
    Assert.assertEquals(una.toEDI(), "UNA|-,! ^${EOL}");
  }

}
