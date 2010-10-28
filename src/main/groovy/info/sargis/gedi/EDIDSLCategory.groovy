package info.sargis.gedi

import info.sargis.gedi.model.una.UNASegment
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

  private static final UNASegment DEFAULT_UNA = new UNASegment();

  private static UNASegment currentUnaConfig

  public static String dataElemSeparator
  public static String compDataSeparator
  public static String decimalNotation

  static {
    configEDIDSLSupportCategory(DEFAULT_UNA)
  }

  /**
   * Configuring EDIDSLCategory for UNA segment, class is not thread safe
   * @param una
   */
  public static void configEDIDSLSupportCategory(UNASegment una) {
    synchronized (EDIDSLCategory.class) {
      EDIDSLCategory.currentUnaConfig = una

      dataElemSeparator = una.dataElemSeparator
      compDataSeparator = una.compDataSep
      decimalNotation = una.decimalNotation
    }
  }

  static String plus(List self, List list) {
    def selfEdi = toEDICompositeElement(self)
    def listEdi = toEDICompositeElement(list)
    "${selfEdi}${dataElemSeparator}${listEdi}"
  }

  static String plus(List self, String str) {
    def selfEdi = toEDICompositeElement(self)
    "${selfEdi}${dataElemSeparator}${str}"
  }

  static String plus(List self, Number number) {
    def selfEdi = toEDICompositeElement(self)
    "${selfEdi}${dataElemSeparator}${formatNumber(number, decimalNotation as char)}"
  }


  static String plus(Number self, Number number) {
    "${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${number}"
  }

  static String plus(Number self, String str) {
    "${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${str}"
  }

  static String plus(Number self, List list) {
    def listEdi = toEDICompositeElement(list)
    "${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${listEdi}"
  }


  static String plus(String self, String str) {
    "${self}${dataElemSeparator}${str}"
  }

  static String plus(String self, List list) {
    def listEdi = toEDICompositeElement(list)
    "${self}${dataElemSeparator}${listEdi}"
  }

  static String plus(String self, Number number) {
    "${self}${dataElemSeparator}${formatNumber(number, decimalNotation as char)}"
  }



  private static String toEDICompositeElement(List list) {
    list.join(compDataSeparator)
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
