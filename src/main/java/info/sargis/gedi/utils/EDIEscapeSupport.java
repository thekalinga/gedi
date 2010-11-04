package info.sargis.gedi.utils;

import info.sargis.gedi.model.una.UNASegment;

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

    private final Pattern pattern;
    private final String replaceExpression;

    public EDIEscapeSupport(UNASegment unaSegment) {
        pattern = Pattern.compile("(:|\\+|\\.|\\?|')");
        replaceExpression = String.format("%s$1", unaSegment.getReleaseIndicator());
    }

    public String escape(String ediData) {
        Matcher matcher = pattern.matcher(ediData);
        return matcher.replaceAll(replaceExpression);
    }

    public String unescape(String ediData) {
        return ediData;
    }

}
