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

    private final Pattern escapePattern;
    private final String escapeReplaceExpr;

    private final Pattern unescapePattern;
    private final String unescapePatternReplaceExpr;

    public EDIEscapeSupport(UNASegment unaSegment) {
        escapePattern = Pattern.compile("(:|\\+|\\.|\\?|')");
        escapeReplaceExpr = String.format("%s$1", unaSegment.getReleaseIndicator());

        unescapePattern = Pattern.compile(String.format("\\%s(:|\\+|\\.|\\?|')", unaSegment.getReleaseIndicator()));
        unescapePatternReplaceExpr = String.format("$1", unaSegment.getReleaseIndicator());
    }

    public String escape(String ediData) {
        Matcher matcher = escapePattern.matcher(ediData);
        return matcher.replaceAll(escapeReplaceExpr);
    }

    public String unescape(String ediData) {
        Matcher matcher = unescapePattern.matcher(ediData);
        return matcher.replaceAll(unescapePatternReplaceExpr);
    }

}
