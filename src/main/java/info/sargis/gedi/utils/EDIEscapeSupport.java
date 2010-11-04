package info.sargis.gedi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 3, 2010
 */
public class EDIEscapeSupport {

    private static final Pattern pattern = Pattern.compile("(:|\\+|\\.|\\?|')");

    public static String escape(String ediData) {
        Matcher matcher = pattern.matcher(ediData);
        return matcher.replaceAll("?$1");
    }

    public static String unescape(String ediData) {
        return ediData;
    }

}
