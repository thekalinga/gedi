package info.sargis.gedi.parser;

import info.sargis.gedi.model.una.UNASegment;

import java.util.Arrays;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 3, 2010
 */
public class SegmentTagBuilder {

    private final EDIRegexSupport ediRegexSupport = EDIRegexSupport.INSTANCE;
    private final UNASegment unaSegment;

    public SegmentTagBuilder(UNASegment unaSegment) {
        this.unaSegment = unaSegment;
    }

    public SegmentTag buildTag(String segmentTagAsString) {
        String compositeDataElemSplitPattern = ediRegexSupport.createCompositeDataElemSplitPattern(unaSegment);
        String[] tagElems = segmentTagAsString.split(compositeDataElemSplitPattern);

        if (tagElems.length == 1) {
            return new SegmentTag(tagElems[0]);
        } else { // mean greater than 1
            SegmentTag segmentTag = new SegmentTag(tagElems[0]);
            segmentTag.addValues(Arrays.copyOfRange(tagElems, 1, tagElems.length)); // copy the rest of elements
            return segmentTag;
        }
    }

}
