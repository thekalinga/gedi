package info.sargis.gedi

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 26, 2010
 */
class EDIDSLCategory {

  static String plus(List self, List list) {
    def selfEdi = toEDICompositeElement(self)
    def listEdi = toEDICompositeElement(list)
    "$selfEdi+$listEdi"
  }

  static String plus(List self, String str) {
    def selfEdi = toEDICompositeElement(self)
    "$selfEdi+$str"
  }

  static String plus(List self, Number number) {
    def selfEdi = toEDICompositeElement(self)
    "$selfEdi+$number"
  }


  static String plus(Number self, Number number) {
    "$self+$number"
  }

  static String plus(Number self, String str) {
    "$self+$str"
  }

  static String plus(Number self, List list) {
    def listEdi = toEDICompositeElement(list)
    "$self+$listEdi"
  }


  static String plus(String self, String str) {
    "$self+$str"
  }

  static String plus(String self, Number number) {
    "$self+$number"
  }

  static String plus(String self, List list) {
    def listEdi = toEDICompositeElement(list)
    "$self+$listEdi"
  }

  static String toEDICompositeElement(List list) {
    list.join(":")
  }

}
