package info.sargis.gedi

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

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

  static String toString(Number self) {
    return formatNumber(self, '.' as char)
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

  static String formatNumber(Number self, char decSeparator) {
    NumberFormat nf = NumberFormat.getInstance()
    if (nf instanceof DecimalFormat) {
      nf.maximumFractionDigits = 20
      nf.groupingUsed = false

      DecimalFormatSymbols newSymbols = nf.decimalFormatSymbols
      newSymbols.decimalSeparator = decSeparator

      nf.decimalFormatSymbols = newSymbols
    }

    return nf.format(self)
  }

}
