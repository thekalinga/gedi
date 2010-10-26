package info.sargis.gedi.model

import org.testng.Assert
import org.testng.annotations.Test
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 26, 2010
 */
class UserSegmentTest {
  @Test
  public void testToEDI() throws Exception {
    def tag = "GCC"
    UserSegment segment = new UserSegment(tagName: tag)
    Assert.assertEquals(segment.toEDI(), "${tag}${EOL}");
  }
}
