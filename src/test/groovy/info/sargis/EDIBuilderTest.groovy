package info.sargis

import org.testng.Assert
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class EDIBuilderTest {

  @Test
  public void testDefaultUNASegment() throws Exception {

    StringWriter sw = new StringWriter()
    EDIBuilder ediBuilder = new EDIBuilder(sw).withDefaultUna()
    ediBuilder.writeUNA(sw)

    Assert.assertEquals(sw.toString(), "UNA:+.? '");
  }

  @Test
  public void testEDIBuilderWithUNASegment() throws Exception {

    StringWriter sw = new StringWriter()
    EDIBuilder ediBuilder = new EDIBuilder(sw).withUna(
            cdes: "|", des: "-", decn: ",", ri: "!", rs: " ", st: "^"
    )
    ediBuilder.writeUNA(sw)

    Assert.assertEquals(sw.toString(), "UNA|-,! ^");
  }


}
