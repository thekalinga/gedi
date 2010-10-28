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
    EDIBuilder edi = new EDIBuilder()

    edi.UNB {
      data {
        ["UNOB", 1] + ["gslg071", "ZZ"] + ["gcms003", "ZZ"] + [101013, 1129] + 1013115727000 + "" + "CLSVAL"
      }

      UNG {
        data {
          "FCVRGR" + "sargis" + "valuewebb" + [101028, 1451] + "CTRLREF"
        }

        UNH {
          data {
            "001" + ["CLSVAL", 1, 972, "MN"]
          }

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
          data {
            "001" + ["CLSVAL", 1, 1000, "MN"]
          }

          XS0 {
            data {
              ["XXX", "", 233, "DD"] + 12222.33 + 3666
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

    StringWriter sw = new StringWriter()
    edi.build(sw)

    def expectedEDI = '''\
      UNA:+.? '
      UNB+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'
      UNG+FCVRGR+sargis+valuewebb+101028:1451+CTRLREF'
      UNH+001+CLSVAL:1:972:MN'
      CS0+SSS:SARG:+12222+3666'
      CS1+:NNN+12222+3666'
      CS2+366+12222+::::REF454'
      UNT+3+UNH0111DUMMY'
      UNH+001+CLSVAL:1:1000:MN'
      XS0+XXX::233:DD+12222.33+3666'
      XS1+3444'
      UNT+2+UNH0111DUMMY'
      UNE+2+UNG0111DUMMY'
      UNZ+1+UNB0111DUMMY'
      '''

    Assert.assertEquals(sw.toString(), expectedEDI.stripIndent())
  }

}
