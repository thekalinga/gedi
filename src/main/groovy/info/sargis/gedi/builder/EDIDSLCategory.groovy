package info.sargis.gedi.builder

import info.sargis.gedi.builder.model.una.UNASegment
import info.sargis.gedi.utils.EDIEscapeSupport
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

  public static UNASegment currentUnaConfig

  public static String dataElemSeparator
  public static String compDataSeparator
  public static String decimalNotation

  public static EDIEscapeSupport escapeSupport

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

      escapeSupport = new EDIEscapeSupport(una)
    }
  }

  // ------------------------------ Start of list category ------------------------------ //

  static EDIString plus(List self, List list) {
    def selfEdi = toEDICompositeElement(self)
    def listEdi = toEDICompositeElement(list)
    new EDIString("${selfEdi}${dataElemSeparator}${listEdi}")
  }

  static EDIString plus(List self, String str) {
    def selfEdi = toEDICompositeElement(self)
    new EDIString("${selfEdi}${dataElemSeparator}${escapeSupport.escape(str)}")
  }

  static EDIString plus(List self, EDIString str) {
    def selfEdi = toEDICompositeElement(self)
    new EDIString("${selfEdi}${dataElemSeparator}${str}")
  }

  static EDIString plus(List self, Number number) {
    def selfEdi = toEDICompositeElement(self)
    new EDIString("${selfEdi}${dataElemSeparator}${formatNumber(number, decimalNotation as char)}")
  }

  // ------------------------------ End of list category ------------------------------ //

  // ------------------------------ Start of number category ------------------------------ //

  static EDIString plus(Number self, Number number) {
    new EDIString("${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${number}")
  }

  static EDIString plus(Number self, String str) {
    new EDIString("${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${escapeSupport.escape(str)}")
  }

  static EDIString plus(Number self, EDIString str) {
    new EDIString("${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${str}")
  }

  static EDIString plus(Number self, List list) {
    def listEdi = toEDICompositeElement(list)
    new EDIString("${formatNumber(self, decimalNotation as char)}${dataElemSeparator}${listEdi}")
  }

  // ------------------------------ End of number category ------------------------------ //

  // ------------------------------ Start of String category ------------------------------ //

  static EDIString plus(String self, String str) {
    new EDIString("${escapeSupport.escape(self)}${dataElemSeparator}${escapeSupport.escape(str)}")
  }

  static EDIString plus(String self, EDIString str) {
    new EDIString("${escapeSupport.escape(self)}${dataElemSeparator}${str}")
  }

  static EDIString plus(String self, List list) {
    def listEdi = toEDICompositeElement(list)
    new EDIString("${escapeSupport.escape(self)}${dataElemSeparator}${listEdi}")
  }

  static EDIString plus(String self, Number number) {
    new EDIString("${escapeSupport.escape(self)}${dataElemSeparator}${formatNumber(number, decimalNotation as char)}")
  }
  // ------------------------------ End of String category ------------------------------ //

  // ------------------------------ Start of EDIString category ------------------------------ //

  static EDIString plus(EDIString self, EDIString str) {
    new EDIString("${self}${dataElemSeparator}${str}")
  }

  static EDIString plus(EDIString self, String str) {
    new EDIString("${self}${dataElemSeparator}${escapeSupport.escape(str)}")
  }

  static EDIString plus(EDIString self, List list) {
    def listEdi = toEDICompositeElement(list)
    new EDIString("${self}${dataElemSeparator}${listEdi}")
  }

  static EDIString plus(EDIString self, Number number) {
    new EDIString("${self}${dataElemSeparator}${formatNumber(number, decimalNotation as char)}")
  }

  // ------------------------------ End of EDIString category ------------------------------ //

  private static String toEDICompositeElement(List list) {
    def escapedList = list.collect {
      escapeSupport.escape(it.toString())
    }
    escapedList.join(compDataSeparator)
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
