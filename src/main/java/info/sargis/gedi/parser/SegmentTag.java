package info.sargis.gedi.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 3, 2010
 */
public class SegmentTag {

    private final String segmentCode;
    private final List<String> nestedElements = new ArrayList<String>();

    public SegmentTag(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public boolean addValue(String value) {
        return nestedElements.add(value);
    }

    public boolean addValues(String[] values) {
        return nestedElements.addAll(Arrays.asList(values));
    }

    public List<String> getNestedElements() {
        return Collections.unmodifiableList(nestedElements);
    }
}
