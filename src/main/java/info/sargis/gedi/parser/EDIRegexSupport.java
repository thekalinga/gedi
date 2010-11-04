package info.sargis.gedi.parser;

import info.sargis.gedi.builder.model.una.UNASegment;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 2, 2010
 */
public class EDIRegexSupport {

    public static final char[] REGEX_META_CHARS = {'$', '^', '.', '*', '+', '?', '\\', '|', '[', ']', '(', ')', '{', '}'};

    public static final EDIRegexSupport INSTANCE = new EDIRegexSupport();

    public String createSegmentSplitPattern(UNASegment unaSegment) {
        return String.format(
                "(?<!%s)%s",
                escapeMetaCharacters(unaSegment.getReleaseIndicator()),
                escapeMetaCharacters(unaSegment.getSegmentTerminator())
        );
    }

    public String createDataElemSplitPattern(UNASegment unaSegment) {
        return String.format(
                "(?<!%s)%s",
                escapeMetaCharacters(unaSegment.getReleaseIndicator()),
                escapeMetaCharacters(unaSegment.getDataElemSeparator())
        );
    }

    public String createCompositeDataElemSplitPattern(UNASegment unaSegment) {
        return String.format(
                "(?<!%s)%s",
                escapeMetaCharacters(unaSegment.getReleaseIndicator()),
                escapeMetaCharacters(unaSegment.getCompDataSep())
        );
    }

    public String escapeMetaCharacters(char ch) {
        if (isMetaCharacter(ch)) {
            return "\\" + ch;
        }
        return String.valueOf(ch);
    }

    private boolean isMetaCharacter(char ch) {
        for (char metaChar : REGEX_META_CHARS) {
            if (metaChar == ch) {
                return true;
            }
        }
        return false;
    }

}
