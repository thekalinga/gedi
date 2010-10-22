package info.sargis.edi

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
  public void testEDIBuilderWithDefaultUNA() throws Exception {
    EDIBuilder edi = new EDIBuilder()
    Writer writer = new StringWriter()

    edi.writer = writer;
    edi.writeDefaultUna();
    Assert.assertEquals(writer.toString(), EDIBuilder.DEFAULT_UNA.toSegment());
  }

  @Test
  public void testEDIBuilderWithCustomUNA() throws Exception {
    EDIBuilder edi = new EDIBuilder()
    Writer writer = new StringWriter()

    edi.writer = writer;
    edi.writeUna(
            cdes: "|", des: "-", decn: ",", ri: "!", rs: " ", st: "^"
    );
    Assert.assertEquals(writer.toString(), "UNA|-,! ^");
  }

  @Test
  public void testWriteUNB() throws Exception {
    EDIBuilder edi = new EDIBuilder()
    Writer writer = new StringWriter()

    edi.writer = writer;
    edi.UNB {
      cde("UNOB": 1)
      cde("gslg071": "ZZ")
      cde("gcms003": "ZZ")
      cde(101013: 1129)
      sde(1013115727000)
      sde()
      sde("CLSVAL")
    }
    System.out.println(writer.toString());
    Assert.assertEquals(writer.toString(), "UNB+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'");
  }

  @Test
  public void testSetText() throws Exception {

    EDIBuilder edi = new EDIBuilder(new StringWriter())

    StringBuilder sb = new StringBuilder()
    edi.setText(sb, [10, 20, "name", 40])
    Assert.assertEquals(sb.toString(), "10:20:name:40");

    sb = new StringBuilder()
    edi.setText(sb, [10, 50, "", 40])
    Assert.assertEquals(sb.toString(), "10:50::40");

    sb = new StringBuilder()
    edi.setText(sb, [10, "", "", 40])
    Assert.assertEquals(sb.toString(), "10:::40");
  }


}
