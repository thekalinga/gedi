package info.sargis.gedi.builder

import info.sargis.gedi.EDIBuilderException
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 26, 2010
 */
class EDIBuilderTest {

  EDIBuilder edi

  @BeforeMethod
  public void setUp() {
    edi = new EDIBuilder()
  }

  @Test
  public void testToEDI() throws Exception {

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
        } // END OF UNH

        UNH {
          data {
            "002" + ["CLSVAL", 1, 1000, "MN"]
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

          ITM {
            data {
              1256.23 + 20 + ["DESC LINE 1", "DESC LINE 2"]
            }
          }
          TAX([1, 1]) {
            data {
              "DTY" + 128.23
            }
          }
          TAX([1, 2]) {
            data {
              "CUD" + 500.00
            }
          }

        } // END OF UNH

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
      UNT+3+001'
      UNH+002+CLSVAL:1:1000:MN'
      XS0+XXX::233:DD+12222.33+3666'
      XS1+3444'
      ITM+1256.23+20+DESC LINE 1:DESC LINE 2'
      TAX:1:1+DTY+128.23'
      TAX:1:2+CUD+500'
      UNT+5+002'
      UNE+2+CTRLREF'
      UNZ+1+1013115727000'
      '''

    Assert.assertEquals(sw.toString(), expectedEDI.stripIndent())
  }

  @Test
  public void testToEDIWithIteration() throws Exception {

    edi.UNB {
      data {
        ["UNOB", 1] + ["gslg071", "ZZ"] + ["gcms003", "ZZ"] + [101013, 1129] + 1013115727000 + "" + "CLSVAL"
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

        ITM {
          data {
            1256.23 + 20 + ["DESC LINE 1", "DESC LINE 2"]
          }
        }

        for (index in 1..2) {

          TAX([1, index]) {
            data {
              "CUD" + 500.00
            }
          }

        }

      } // END OF UNH

    }

    StringWriter sw = new StringWriter()
    edi.build(sw)

    def expectedEDI = '''\
      UNA:+.? '
      UNB+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'
      UNH+001+CLSVAL:1:1000:MN'
      XS0+XXX::233:DD+12222.33+3666'
      ITM+1256.23+20+DESC LINE 1:DESC LINE 2'
      TAX:1:1+CUD+500'
      TAX:1:2+CUD+500'
      UNT+4+001'
      UNZ+1+1013115727000'
      '''

    Assert.assertEquals(sw.toString(), expectedEDI.stripIndent())
  }

  @Test(expectedExceptions = EDIBuilderException.class)
  public void testToEDIMixingUngAndUnhSegments() throws Exception {

    edi.UNB {
      data {
        ["UNOB", 1] + ["gslg071", "ZZ"] + ["gcms003", "ZZ"] + [101013, 1129] + 1013115727000 + "" + "CLSVAL"
      }

      UNG {
        data {
          "FCVRGR" + "sargis" + "valuewebb" + [101028, 1451] + "CTRLREF"
        }
      }

      UNH {
        data {
          "001" + ["CLSVAL", 1, 1000, "MN"]
        }
      }
    }

    StringWriter sw = new StringWriter()
    edi.build(sw)
  }

  @Test(expectedExceptions = EDIBuilderException.class)
  public void testToEDIMixingUnhAndUngSegments() throws Exception {

    edi.UNB {
      data {
        ["UNOB", 1] + ["gslg071", "ZZ"] + ["gcms003", "ZZ"] + [101013, 1129] + 1013115727000 + "" + "CLSVAL"
      }

      UNH {
        data {
          "001" + ["CLSVAL", 1, 1000, "MN"]
        }
      }

      UNG {
        data {
          "FCVRGR" + "sargis" + "valuewebb" + [101028, 1451] + "CTRLREF"
        }
      }

    }

    StringWriter sw = new StringWriter()
    edi.build(sw)
  }

}
