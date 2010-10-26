package info.sargis.gedi

import org.testng.Assert
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 26, 2010
 */
class EDIBuilderTest {

  @Test
  public void testToEDI() throws Exception {
    StringWriter sw = new StringWriter()
    EDIBuilder edi = new EDIBuilder(sw)

    edi.UNB {
      UNG {
        UNH {
          CS0 {}
          CS1 {}
          CS2 {}
        }
        UNH {
          XS0 {}
          XS1 {}
        }
      }
    }
    edi.build()

    def expectedEDI = '''\
      UNA:+.? '
      UNB
      UNG
      UNH
      CS0
      CS1
      CS2
      UNT+3+UNH0111DUMMY
      UNH
      XS0
      XS1
      UNT+2+UNH0111DUMMY
      UNE+2+UNG0111DUMMY
      UNZ+1+UNB0111DUMMY
      '''

    Assert.assertEquals(sw.toString(), expectedEDI.stripIndent())
  }

}
