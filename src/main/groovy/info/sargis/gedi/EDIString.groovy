package info.sargis.gedi

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 4, 2010
 */
class EDIString {

  @Delegate String ediString

  def EDIString(ediString) {
    this.ediString = ediString;
  }

  boolean equals(o) {
    if (this.is(o)) return true;

    if (getClass() != o.class) return false;

    EDIString ediString1 = (EDIString) o;

    if (ediString != ediString1.ediString) return false;

    return true;
  }

  int hashCode() {
    return (ediString != null ? ediString.hashCode() : 0);
  }

  def String toString() {
    return ediString;
  }

}
