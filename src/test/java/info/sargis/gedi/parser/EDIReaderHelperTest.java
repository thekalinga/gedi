package info.sargis.gedi.parser;

import info.sargis.gedi.builder.model.una.UNASegment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 2, 2010
 */
public class EDIReaderHelperTest {

    private final UNASegment defaultUnaSegment = new UNASegment();

    private EDIRegexSupport ediReaderHelper;
    private UNASegment customUnaSegment;

    @BeforeMethod
    public void setUp() {
        ediReaderHelper = new EDIRegexSupport();
        customUnaSegment = createCustomUnaSegment();
    }

    @Test
    public void testCreateDataElemSplitPattern() throws Exception {
        Assert.assertEquals(ediReaderHelper.createDataElemSplitPattern(defaultUnaSegment), "(?<!\\?)\\+");
        Assert.assertEquals(ediReaderHelper.createDataElemSplitPattern(customUnaSegment), "(?<!\\^)-");
    }

    @Test
    public void testCreateCompositeDataElemSplitPattern() throws Exception {
        Assert.assertEquals(ediReaderHelper.createCompositeDataElemSplitPattern(defaultUnaSegment), "(?<!\\?):");
        Assert.assertEquals(ediReaderHelper.createCompositeDataElemSplitPattern(customUnaSegment), "(?<!\\^)\\|");
    }

    @Test
    public void testCreateSegmentSplitPattern() throws Exception {
        Assert.assertEquals(ediReaderHelper.createSegmentSplitPattern(defaultUnaSegment), "(?<!\\?)'");
        Assert.assertEquals(ediReaderHelper.createSegmentSplitPattern(customUnaSegment), "(?<!\\^)\\+");
    }

    @Test
    public void testEscapeForMetaCharacters() throws Exception {
        char[] metaChars = EDIRegexSupport.REGEX_META_CHARS;
        for (char metaChar : metaChars) {
            Assert.assertEquals(ediReaderHelper.escapeMetaCharacters(metaChar), "\\" + String.valueOf(metaChar));
        }
    }

    @Test
    public void testEscapeForSimpleCharacters() throws Exception {
        Assert.assertEquals(ediReaderHelper.escapeMetaCharacters('x'), String.valueOf('x'));
    }

    private UNASegment createCustomUnaSegment() {
        UNASegment unaSegment = new UNASegment();

        unaSegment.setDataElemSeparator('-');
        unaSegment.setCompDataSep('|');
        unaSegment.setReleaseIndicator('^');
        unaSegment.setSegmentTerminator('+');

        return unaSegment;
    }
}
