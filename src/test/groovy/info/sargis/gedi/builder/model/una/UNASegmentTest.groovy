package info.sargis.gedi.builder.model.una

import org.testng.Assert
import org.testng.annotations.Test

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
    UNASegment una = new UNASegment()
    Assert.assertEquals(una.toEDI(), "UNA:+.? '");
  }

  @Test
  public void testCustomUNASegment() throws Exception {
    UNASegment una = new UNASegment(
            compDataSep: "|", dataElemSeparator: "-", decimalNotation: ",",
            releaseIndicator: "!", reserved: " ", segmentTerminator: "^"
    )

    Assert.assertEquals(una.toEDI(), "UNA|-,! ^");
  }

}
