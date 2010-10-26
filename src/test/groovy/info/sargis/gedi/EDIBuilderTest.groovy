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
          CS0 {
            data {
              ["SSS", "SARG", ""] + 12222 + 3666
            }
          }
          CS1 {
            data {
              ["", "NNN"] + 12222 + 3666
            }
          }
          CS2 {
            data {
              366 + 12222 + ["", "", "", "", "REF454"]
            }
          }
        }

        UNH {
          XS0 {
            data {
              ["XXX", "", 233, "DD"] + 12222 + 3666
            }
          }
          XS1 {
            data {
              3444
            }
          }
        }

      }
    }
    edi.build()

    def expectedEDI = '''\
      UNA:+.? '
      UNB
      UNG
      UNH
      CS0+SSS:SARG:+12222+3666'
      CS1+:NNN+12222+3666'
      CS2+366+12222+::::REF454'
      UNT+3+UNH0111DUMMY
      UNH
      XS0+XXX::233:DD+12222+3666'
      XS1+3444'
      UNT+2+UNH0111DUMMY
      UNE+2+UNG0111DUMMY
      UNZ+1+UNB0111DUMMY
      '''

    Assert.assertEquals(sw.toString(), expectedEDI.stripIndent())
  }

}
